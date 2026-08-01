package Baralho;

import Carta.Acao;
import Carta.CartaConvencional;
import Carta.Naipe;
import Carta.Carta;
import Carta.Simbolo;

import java.util.ArrayList;

public class BaralhoConvencional extends Baralho<CartaConvencional> {

    public BaralhoConvencional() {
        super();
        categoria = Naipe.values();
        /*
         * Adicionando cartas 1 - 10 ao baralho
         */
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                CartaConvencional carta = new CartaConvencional(simbolo[j], (Naipe) categoria[i]);
                cartas.add(carta);
            }
        }

        /*
         * Adicionando cartas de ação (PULAR, INVERTER, MAIS_DOIS) ao baralho - duas cartas por naipe
         */
        for (Naipe n : Naipe.values()) {
            for (int i = 0; i < 3; i++) {
                cartas.add(new CartaConvencional(
                        simbolo[10 + i],
                        n,
                        acao[i]));
            }
        }

        /*
         * Adicionando 4 curingas black e 4 curingas red (NOVA_COR, MAIS_QUATRO)
         */
        for(int i = 13; i < 15; i++){
                cartas.add(new CartaConvencional(simbolo[i], acao[i-10]));
        }
    }

    public ArrayList<Naipe> getNaipe() {
        ArrayList<Naipe> naipes = new ArrayList<>();
        for (CartaConvencional carta : cartas) {
            naipes.add(carta.getNaipe());
        }

        return naipes;
    }

    public ArrayList<Simbolo> getSimbolo() {
        ArrayList<Simbolo> simbolos = new ArrayList<>();
        for (CartaConvencional carta : cartas) {
            simbolos.add(carta.getSimbolo());
        }

        return simbolos;
    }

    public void getCarta() {
        for (CartaConvencional carta : cartas) {
            if(carta.getNaipe() != null){
                System.out.println(carta.getNaipe());
            }
            System.out.println(carta.getSimbolo());

            if (carta.getAcao() != null) {
                System.out.println(carta.getAcao());
            }

            System.out.println();
        }
    }
}
