package Jogo;

import Baralho.Baralho;
import Baralho.BaralhoConvencional;
import Baralho.BaralhoOriginal;
import Carta.Carta;
import java.util.*;

public class UNO {
    Baralho<? extends Carta> conjuntoBaralho;
    List<? extends Carta> baralho;
    Carta cartaComparativa;
    Modo modo;

    public UNO(Modo modo, int nJogadores){
        if (modo == Modo.UNO_OFICIAL){
            conjuntoBaralho = new BaralhoOriginal();
        } else {
            conjuntoBaralho = new BaralhoConvencional();
        }

        this.modo = modo;
        baralho = conjuntoBaralho.getBaralho();

        // Embaralha
        Collections.shuffle(baralho);

        // Cria mãos para cada jogador
        // 2 Jogadores
        // Maos[0] = [0,1,2,3,4,5,6]
        // Maos[1] = [0,1,2,3,4,5,6]

        List<Jogador> jogadores = new ArrayList<>();
        for(int i = 0; i < nJogadores; i++){
            jogadores.add(new Jogador());
            for(int j = 0; j < 7; j++){
                // Inicializa mão com 7 cartas
                jogadores.get(i).add(baralho.getFirst());
                baralho.removeFirst();
            }
        }

        // Cria pilha de compra com restante das cartas
        Carta primeiraCarta = baralho.getFirst();
        List<? extends Carta> pilhaCompra = baralho;
        pilhaCompra.removeFirst();

        // Cria pilha de descarte
        List<Carta> pilhaDescarte = new ArrayList<>();
        pilhaDescarte.add(primeiraCarta);

        cartaComparativa = pilhaDescarte.getLast();
        mostraCartaComparativa();


        for(int i = 0; i < jogadores.size(); i++){
           while(jogadores.get(i).getMao() != null){
                System.out.println("Escolha a carta para jogar ou compre do monte.");
                mostraMao(jogadores.get(i));
                Carta cartaEscolhida = jogadores.get(i).joga();
                while(!verificaCartas(cartaEscolhida, cartaComparativa)){
                    System.out.println("Carta escolhida não é válida, escolha outra carta.");
                    jogadores.get(i).joga();
                }
                pilhaDescarte.add(cartaEscolhida);
                jogadores.get(i).remove(cartaEscolhida);

            }

        }
    }

    void mostraCartaComparativa(){
        mostraCarta(cartaComparativa);
    }

    void mostraCarta(Carta carta){
        if(carta.getIsCuringa()){
            // 3° Caso: carta curinga
            if(modo == Modo.UNO_OFICIAL){
                System.out.println(carta.getSimbolo().getValor());
            } else {
                System.out.println(carta.getSimbolo());
            }
            System.out.println(cartaComparativa.getAcao());
        } else if(carta.getIsDeAcao()){
            // 2° Caso: carta de ação com símbolo
            if(modo == Modo.UNO_OFICIAL){
                System.out.println(carta.getValor());
            } else {
                System.out.println(carta.getSimbolo());
            }
            System.out.println(carta.getCategoria());
            System.out.println(carta.getAcao());
        } else {
            // 1° Caso: carta tradicional
            if(modo == Modo.UNO_OFICIAL){
                System.out.println(carta.getValor());
            } else {
                System.out.println(carta.getSimbolo());
            }
            System.out.println(carta.getCategoria());
        }
    }

    void mostraMao(Jogador jogador){
        List<Carta> cartasJogador = jogador.getMao();
        for(int i = 1; i <= cartasJogador.size(); i++ ){
            for (Carta carta : cartasJogador) {
                System.out.println(0 + "i");
                mostraCarta(carta);
                System.out.println();
            }
        }
    }

    boolean verificaCartas(Carta cartaJogador, Carta cartaComparativa){
        return cartaJogador.getCategoria() == cartaComparativa.getCategoria() || cartaJogador.getValor() == cartaComparativa.getValor() || cartaJogador.getSimbolo() == cartaComparativa.getSimbolo() || cartaJogador.getIsCuringa();
    }
}
