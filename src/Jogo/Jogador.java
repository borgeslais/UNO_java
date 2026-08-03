package Jogo;

import Carta.Carta;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jogador {
    private final List<Carta> mao = new ArrayList<>();

    public List<Carta> getMao() {
        return mao;
    }

    public void remove(Carta carta){
        mao.remove(carta);
    }

    public void add(Carta carta) {
        mao.add(carta);
    }

    public Carta joga(){
        int pos = 0;
        for (Carta carta : mao) {
            System.out.println(pos + ") ");
            mostraCarta(carta);
            pos++;
        }

        Scanner sc = new Scanner(System.in);
        int c = sc.nextInt();
        return mao.get(c);
    }

    public void mostraCarta(Carta carta) {
        if (carta.getIsCuringa()) {
            // 3° Caso: carta curinga
            if (modo == Modo.UNO_OFICIAL) {
                System.out.print(carta.getSimbolo().getValor());
            } else {
                System.out.print(carta.getSimbolo());
            }
            System.out.print(carta.getAcao());
        } else if (carta.getIsDeAcao()) {
            // 2° Caso: carta de ação com símbolo
            if (modo == Modo.UNO_OFICIAL) {
                System.out.print(carta.getValor());
            } else {
                System.out.print(carta.getSimbolo());
            }
            System.out.print(carta.getCategoria());
            System.out.print(carta.getAcao());
        } else {
            // 1° Caso: carta tradicional
            if (modo == Modo.UNO_OFICIAL) {
                System.out.print(carta.getValor());
            } else {
                System.out.print(carta.getSimbolo());
            }
            System.out.print(carta.getCategoria());
        }
    }


}
