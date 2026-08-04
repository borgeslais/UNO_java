package Jogo;

import Baralho.Baralho;
import Baralho.BaralhoConvencional;
import Baralho.BaralhoOriginal;
import Carta.Carta;
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

        // Embaralha
        Collections.shuffle(baralho);

        // Cria mãos para cada jogador
        // 2 Jogadores
        // Maos[0] = [0,1,2,3,4,5,6]
        // Maos[1] = [0,1,2,3,4,5,6]

        List<Jogador> jogadores = new ArrayList<>();
        for (int i = 0; i < nJogadores; i++) {
            jogadores.add(new Jogador());
            for (int j = 0; j < 7; j++) {
                // Inicializa mão com 7 cartas
                jogadores.get(i).add(baralho.getFirst());
                baralho.removeFirst();
            }
        }

        // Cria pilha de compra com restante das cartas
        Carta primeiraCarta = baralho.getFirst();
        baralho.removeFirst();
        pilhaCompra = baralho;

        // Pilha de descarte
        pilhaDescarte = new ArrayList<>();
        pilhaDescarte.add(primeiraCarta);

        cartaComparativa = pilhaDescarte.getLast();
        mostraCartaComparativa();

        int jogadorAtual = 0;
        int direcao = 1;
        boolean jogoAcabou = false;

        // Loop de jogada
        while (!jogoAcabou) {
            Jogador atual = jogadores.get(jogadorAtual);
            System.out.println("\n --- Vez do jogador: " + jogadorAtual + " ---\n");
            mostraMao(atual);

            Carta cartaEscolhida = null;
            boolean jogadaValida = false;

            while (!jogadaValida) {
                cartaEscolhida = atual.joga(pilhaCompra, pilhaDescarte);

                if (cartaEscolhida == null) {
                    // jogador optou por comprar
                    break;
                }
                if (verificaCartas(cartaEscolhida, cartaComparativa)) {
                    jogadaValida = true;
                } else {
                    System.out.println("Carta escolhida não é válida, escolha outra.");
                }
            }

            if (cartaEscolhida != null) {
                pilhaDescarte.add(cartaEscolhida);
                atual.remove(cartaEscolhida);
                cartaComparativa = cartaEscolhida;
                mostraCartaComparativa();

                if (atual.getMao().isEmpty()) {
                    System.out.println("Jogador " + jogadorAtual + " venceu o jogo!");
                    jogoAcabou = true;
                    continue;
                }

                // TODO: efeitos de cartas de ação
            }

            jogadorAtual = (jogadorAtual + direcao + jogadores.size()) % jogadores.size();
        }
    }

    void mostraCartaComparativa(){
        System.out.println("\n --- Carta da Pilha: ---\n");
        mostraCarta(cartaComparativa);
    }

    void mostraCarta(Carta carta){
        String valor;

        if(carta.getIsCuringa()){
            // 3° Caso: carta curinga
            if(modo == Modo.UNO_OFICIAL){
                valor = String.valueOf(carta.getSimbolo().getValor());
            } else {
                valor = String.valueOf(carta.getSimbolo());
            }
            System.out.print(valor + ", " + carta.getAcao());
        } else if(carta.getIsDeAcao()){
            // 2° Caso: carta de ação com símbolo
            if(modo == Modo.UNO_OFICIAL){
                valor = String.valueOf(carta.getValor());
            } else {
                valor = String.valueOf(carta.getSimbolo());
            }
            System.out.print(valor + ", " + carta.getCategoria() + ", " + carta.getAcao());
        } else {
            // 1° Caso: carta tradicional
            if(modo == Modo.UNO_OFICIAL){
                valor = String.valueOf(carta.getValor());
            } else {
                valor = String.valueOf(carta.getSimbolo());
            }
            System.out.print(valor + ", " + carta.getCategoria());
        }
        System.out.println();
    }

    void mostraMao(Jogador jogador){
        List<Carta> cartasJogador = jogador.getMao();
        for (int i = 0; i < cartasJogador.size(); i++) {
            System.out.print((i + 1) + ") ");
            mostraCarta(cartasJogador.get(i));
        }
    }

    boolean verificaCartas(Carta cartaJogador, Carta cartaComparativa){
        return cartaJogador.getCategoria() == cartaComparativa.getCategoria() || cartaJogador.getValor() == cartaComparativa.getValor() || cartaJogador.getSimbolo() == cartaComparativa.getSimbolo() || cartaJogador.getIsCuringa();
    }

    static void reabastecerPilhaCompra(List<Carta> pilhaCompra, List<Carta> pilhaDescarte){
        if (pilhaDescarte.size() <= 1) {
            // nada pra reaproveitar ainda
            return;
        }
        Carta topo = pilhaDescarte.removeLast();
        pilhaCompra.addAll(pilhaDescarte);
        pilhaDescarte.clear();
        pilhaDescarte.add(topo);
        Collections.shuffle(pilhaCompra);
    }
}
