package Baralho;

import Carta.*;

import java.util.ArrayList;

public class BaralhoOriginal extends Baralho<CartaOriginal>{

    public BaralhoOriginal() {
        super();
        categoria = Cor.values();

        /*
         * Adicionando cartas 0 - 9 ao baralho
         */
        for (int i = 0; i < 4; i++) {
            cartas.add(new CartaOriginal(simbolo[0], (Cor) categoria[i]));
            for (int j = 1; j < 10; j++) {
                CartaOriginal carta1 = new CartaOriginal(simbolo[j], (Cor) categoria[i]);
                cartas.add(carta1);
                CartaOriginal carta2 = new CartaOriginal(simbolo[j], (Cor) categoria[i]);
                cartas.add(carta2);
            }
        }

        /*
         * Adicionando cartas de ação (PULAR, INVERTER, MAIS_DOIS) ao baralho - duas cartas por cor
         */
        for (Cor c : Cor.values()) {
            for (int i = 0; i < 3; i++) {
                cartas.add(new CartaOriginal(
                        simbolo[10 + i],
                        c,
                        acao[i]));
                cartas.add(new CartaOriginal(
                        simbolo[10 + i],
                        c,
                        acao[i]));

            }
        }

        /*
         * Adicionando curingas (NOVA_COR, MAIS_QUATRO)
         */
        for (int i = 13; i < 15; i++) {
            for(int j = 0; j < 4; j++){
                cartas.add(new CartaOriginal(simbolo[i], acao[i-10]));
            }
        }
    }

}