package Jogo;

import Carta.Carta;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jogador {
    private final List<Carta> mao = new ArrayList<>();
    private String nome;

    public Jogador(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public List<Carta> getMao() {
        return mao;
    }

    public void remove(Carta carta) {
        mao.remove(carta);
    }

    public void add(Carta carta) {
        mao.add(carta);
    }

    public Carta joga(List<Carta> pilhaCompra, List<Carta> pilhaDescarte) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Digite a letra da carta para jogar, ou -1 para comprar do monte:");
            String entrada = sc.nextLine().trim();

            if (entrada.equals("-1")) {
                if (pilhaCompra.isEmpty()) {
                    UNO.reabastecerPilhaCompra(pilhaCompra, pilhaDescarte);
                }
                if (pilhaCompra.isEmpty()) {
                    System.out.println("Não há mais cartas para comprar.");
                    continue;
                }
                Carta comprada = pilhaCompra.removeFirst();
                mao.add(comprada);
                System.out.println("Você comprou uma carta.");
                return null;
            }

            if (entrada.length() == 1) {
                char letra = Character.toLowerCase(entrada.charAt(0));
                int indice = letra - 'a';
                if (indice >= 0 && indice < mao.size()) {
                    return mao.get(indice);
                }
            }

            System.out.println("Opção inválida, tente novamente.");
        }
    }

}
