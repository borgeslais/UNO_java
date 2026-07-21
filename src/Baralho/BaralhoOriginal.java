package Baralho;

import Carta.*;

import java.util.ArrayList;

public class BaralhoOriginal {
    public ArrayList<CartaOriginal> cartas;
    Simbolo[] simboloOriginal = Simbolo.values();
    Cor[] cor = Cor.values();
    Acao[] acao = Acao.values();

    public BaralhoOriginal() {
        cartas = new ArrayList<CartaOriginal>();

        /*
         * Adicionando cartas 0 - 9 ao baralho
         */
        for (int i = 0; i < 4; i++) {
            cartas.add(new CartaOriginal(simboloOriginal[0], cor[i]));
            for (int j = 1; j < 10; j++) {
                CartaOriginal carta1 = new CartaOriginal(simboloOriginal[j], cor[i]);
                cartas.add(carta1);
                CartaOriginal carta2 = new CartaOriginal(simboloOriginal[j], cor[i]);
                cartas.add(carta2);
            }
        }

        /*
         * Adicionando cartas de ação (PULAR, INVERTER, MAIS_DOIS) ao baralho - duas cartas por cor
         */
        for (Cor c : Cor.values()) {
            for (int i = 0; i < 3; i++) {
                cartas.add(new CartaOriginal(
                        simboloOriginal[10 + i],
                        c,
                        acao[i]));
                cartas.add(new CartaOriginal(
                        simboloOriginal[10 + i],
                        c,
                        acao[i]));

            }
        }

        /*
         * Adicionando curingas (NOVA_COR, MAIS_QUATRO)
         */
        for (int i = 13; i < 15; i++) {
            for(int j = 0; j < 4; j++){
                cartas.add(new CartaOriginal(acao[i - 10]));
            }
        }
    }


    public void getCarta() {
        for (CartaOriginal carta : cartas) {
            if (carta.getCor() != null) {
                System.out.println(carta.getCor());
            }

            if(!carta.getCuringa()){
                System.out.println(carta.getSimbolo());
            }

            if (carta.getAcao() != null) {
                System.out.println(carta.getAcao());
            }

            System.out.println();
        }

    }
}