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

    public Carta joga(List<Carta> pilhaCompra, List<Carta> pilhaDescarte) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Digite o número da carta para jogar, ou -1 para comprar do monte:");
            int c = sc.nextInt();

            if (c == -1) {
                if (pilhaCompra.isEmpty()) {
                    UNO.reabastecerPilhaCompra(pilhaCompra, pilhaDescarte);
                }
                if (pilhaCompra.isEmpty()) {
                    // pilha de compra vazia
                    System.out.println("Não há mais cartas para comprar.");
                    continue;
                }

                Carta comprada = pilhaCompra.removeFirst();
                mao.add(comprada);
                System.out.println("Você comprou uma carta.");
                return null;
            } else if (c >= 1 && c < mao.size()) {
                return mao.get(c - 1);
            } else {
                System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

}
