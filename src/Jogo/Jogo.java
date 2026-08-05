package Jogo;

import java.util.Scanner;

public class Jogo {
    public Jogo() {
        System.out.println("********   UNO   ********");
        System.out.println("Selecione o modo de jogo: com baralho oficial UNO ou com baralho de cartas convencional:");
        System.out.println("a) Baralho UNO oficial");
        System.out.println("b) Baralho convencional");
        Scanner sc = new Scanner(System.in);

        // Validação do modo de jogo
        char modo;
        while (true) {
            String input = sc.next().trim();
            if (input.length() == 1) {
                modo = Character.toLowerCase(input.charAt(0));
                if (modo == 'a' || modo == 'b') {
                    break;
                }
            }
            System.out.println("Opção inválida! Digite apenas 'a' para UNO oficial ou 'b' para convencional:");
        }

        System.out.println("Selecione a letra correspondente à quantidade de jogadores:");
        System.out.println("a) 2 jogadores");
        System.out.println("b) 3 jogadores");
        System.out.println("c) 4 jogadores");
        System.out.println("d) 5 jogadores");
        System.out.println("e) 6 jogadores");
        System.out.println("f) 7 jogadores");
        System.out.println("g) 8 jogadores");
        System.out.println("h) 9 jogadores");
        System.out.println("i) 10 jogadores");

        // Validação da quantidade de jogadores
        char jogador;
        while (true) {
            String input = sc.next().trim();
            if (input.length() == 1) {
                jogador = Character.toLowerCase(input.charAt(0));
                if (jogador >= 'a' && jogador <= 'i') {
                    break;
                }
            }
            System.out.println("Opção inválida! Digite apenas UMA letra de 'a' a 'i':");
        }

        int jogadorNum = jogador - 'a' + 2; // Converte 'a' para 2, 'b' para 3, etc.

        if (modo == 'a') {
            Modo modoOficial = Modo.UNO_OFICIAL;
            UNO unoOficial = new UNO(modoOficial, jogadorNum);
        } else { // modo == 'b'
            Modo modoConvencional = Modo.CONVENCIONAL;
            UNO unoConvencional = new UNO(modoConvencional, jogadorNum);
        }
    }
}
