package Jogo;

import Baralho.Baralho;
import Baralho.BaralhoConvencional;
import Baralho.BaralhoOriginal;
import Carta.Carta;
import Carta.Cor;

import java.util.*;

public class UNO {
    Baralho<? extends Carta> conjuntoBaralho;
    List<Carta> baralho;
    Carta cartaComparativa;
    Modo modo;
    List<Carta> pilhaCompra;
    List<Carta> pilhaDescarte;

    public UNO(Modo modo, int nJogadores) {
        if (modo == Modo.UNO_OFICIAL) {
            conjuntoBaralho = new BaralhoOriginal();
        } else {
            conjuntoBaralho = new BaralhoConvencional();
        }

        this.modo = modo;
        baralho = new ArrayList<>(conjuntoBaralho.getBaralho());
        Collections.shuffle(baralho);

        List<Jogador> jogadores = new ArrayList<>();
        for (int i = 0; i < nJogadores; i++) {
            jogadores.add(new Jogador());
            for (int j = 0; j < 7; j++) {
                jogadores.get(i).add(baralho.getFirst());
                baralho.removeFirst();
            }
        }

        Carta primeiraCarta = baralho.getFirst();
        baralho.removeFirst();
        // Tratamento para caso primeira carta for curinga/ação
        while (primeiraCarta.getIsDeAcao() || primeiraCarta.getIsCuringa()) {
            baralho.add(primeiraCarta);
            primeiraCarta = baralho.getFirst();
            baralho.removeFirst();
        }

        pilhaCompra = baralho;

        pilhaDescarte = new ArrayList<>();
        pilhaDescarte.add(primeiraCarta);

        cartaComparativa = pilhaDescarte.getLast();
        mostraCartaComparativa();

        int jogadorAtual = 0;
        int direcao = 1;
        boolean jogoAcabou = false;

        while (!jogoAcabou) {
            Jogador atual = jogadores.get(jogadorAtual);
            System.out.println("\n --- Vez do jogador: " + jogadorAtual + " ---\n");
            mostraCartaComparativa();
            mostraMao(atual);

            Carta cartaEscolhida = null;
            boolean jogadaValida = false;

            while (!jogadaValida) {
                cartaEscolhida = atual.joga(pilhaCompra, pilhaDescarte);

                if (cartaEscolhida == null) {
                    break;
                }
                if (verificaCartas(cartaEscolhida, cartaComparativa)) {
                    jogadaValida = true;
                } else {
                    System.out.println("Carta escolhida não é válida, escolha outra.");
                }
            }

            int proximoJogador = (jogadorAtual + direcao + jogadores.size()) % jogadores.size();

            if (cartaEscolhida != null) {
                pilhaDescarte.add(cartaEscolhida);
                atual.remove(cartaEscolhida);

                // Curinga: jogador escolhe a nova cor antes de virar a carta comparativa
                if (cartaEscolhida.getIsCuringa()) {
                    Cor corEscolhida = escolheCor();
                    cartaEscolhida.setCategoria(corEscolhida);
                    System.out.println("Cor escolhida: " + corEscolhida);
                }

                cartaComparativa = cartaEscolhida;
                mostraCartaComparativa();

                if (atual.getMao().isEmpty()) {
                    System.out.println("Jogador " + jogadorAtual + " venceu o jogo!");
                    jogoAcabou = true;
                    continue;
                }

                // Efeitos das cartas de ação / curinga
                if (cartaEscolhida.getIsDeAcao() || cartaEscolhida.getIsCuringa()) {
                    Jogador proximo = jogadores.get(proximoJogador);

                    switch (cartaEscolhida.getAcao()) {
                        case PULAR -> {
                            System.out.println("Jogador " + proximoJogador + " perdeu a vez!");
                            proximoJogador = (proximoJogador + direcao + jogadores.size()) % jogadores.size();
                        }
                        case INVERTER -> {
                            direcao *= -1;
                            System.out.println("Sentido do jogo invertido!");
                            if (jogadores.size() == 2) {
                                // com 2 jogadores, inverter = pular o adversário
                                proximoJogador = (jogadorAtual + direcao + jogadores.size()) % jogadores.size();
                            } else {
                                proximoJogador = (jogadorAtual + direcao + jogadores.size()) % jogadores.size();
                            }
                        }
                        case MAIS_DOIS -> {
                            comprarCartas(proximo, 2);
                            System.out.println("Jogador " + proximoJogador + " comprou 2 cartas e perdeu a vez!");
                            proximoJogador = (proximoJogador + direcao + jogadores.size()) % jogadores.size();
                        }
                        case MAIS_QUATRO -> {
                            comprarCartas(proximo, 4);
                            System.out.println("Jogador " + proximoJogador + " comprou 4 cartas e perdeu a vez!");
                            proximoJogador = (proximoJogador + direcao + jogadores.size()) % jogadores.size();
                        }
                        case NOVA_COR -> {
                            // sem efeito adicional além da troca de cor já aplicada acima
                        }
                    }
                }
            }

            jogadorAtual = proximoJogador;
        }
    }

    Cor escolheCor() {
        Cor[] cores = Cor.values();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Escolha a nova cor:");
            for (int i = 0; i < cores.length; i++) {
                System.out.println((i + 1) + ") " + cores[i]);
            }
            int c = sc.nextInt();
            if (c >= 1 && c <= cores.length) {
                return cores[c - 1];
            }
            System.out.println("Opção inválida, tente novamente.");
        }
    }

    void comprarCartas(Jogador jogador, int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            if (pilhaCompra.isEmpty()) {
                reabastecerPilhaCompra(pilhaCompra, pilhaDescarte);
            }
            if (pilhaCompra.isEmpty()) {
                System.out.println("Não há mais cartas para comprar.");
                return;
            }
            jogador.add(pilhaCompra.removeFirst());
        }
    }

    void mostraCartaComparativa() {
        System.out.println(" --- Carta da Pilha: ---");
        System.out.print("   ");
        mostraCarta(cartaComparativa);
        System.out.println(" -----------------------");
    }

    void mostraCarta(Carta carta) {
        if (carta.getIsCuringa()) {
            switch (carta.getAcao()) {
                case NOVA_COR -> {
                    if (carta.getCategoria() != null) {
                        System.out.printf("Curinga (Nova Cor), %s%n", carta.getCategoria());
                    } else {
                        System.out.printf("Curinga (Nova Cor)%n");
                    }
                }
                case MAIS_QUATRO -> {
                    if (carta.getCategoria() != null) {
                        System.out.printf("Curinga +4, %s%n", carta.getCategoria());
                    } else {
                        System.out.printf("Curinga +4%n");
                    }
                }
                default -> System.out.printf("Curinga, %s%n", carta.getAcao());
            }
        } else if (carta.getIsDeAcao()) {
            switch (carta.getAcao()) {
                case PULAR -> System.out.printf("Pular, %s%n", carta.getCategoria());
                case INVERTER -> System.out.printf("Inverter, %s%n", carta.getCategoria());
                case MAIS_DOIS -> System.out.printf("+2, %s%n", carta.getCategoria());
                default -> System.out.printf("%s, %s%n", carta.getAcao(), carta.getCategoria());
            }
        } else {
            // Carta tradicional
            String valor;
            if (modo == Modo.UNO_OFICIAL) {
                valor = String.valueOf(carta.getValor());
            } else {
                valor = String.valueOf(carta.getSimbolo());
            }
            System.out.printf("%s, %s%n", valor, carta.getCategoria());
        }
    }

    void mostraMao(Jogador jogador) {
        List<Carta> cartasJogador = jogador.getMao();
        for (int i = 0; i < cartasJogador.size(); i++) {
            System.out.print((i + 1) + ") ");
            mostraCarta(cartasJogador.get(i));
        }
    }

    boolean verificaCartas(Carta cartaJogador, Carta cartaComparativa) {
        return cartaJogador.getCategoria() == cartaComparativa.getCategoria() || cartaJogador.getValor() == cartaComparativa.getValor() || cartaJogador.getSimbolo() == cartaComparativa.getSimbolo() || cartaJogador.getIsCuringa();
    }

    static void reabastecerPilhaCompra(List<Carta> pilhaCompra, List<Carta> pilhaDescarte) {
        if (pilhaDescarte.size() <= 1) {
            return;
        }
        Carta topo = pilhaDescarte.removeLast();
        pilhaCompra.addAll(pilhaDescarte);
        pilhaDescarte.clear();
        pilhaDescarte.add(topo);
        Collections.shuffle(pilhaCompra);
    }
}