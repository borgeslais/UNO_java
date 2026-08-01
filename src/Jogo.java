import Baralho.BaralhoConvencional;
import Baralho.BaralhoOriginal;

import java.util.Scanner;

public class Jogo {
    int jogadores;

    public Jogo() {
        System.out.println("********   UNO   ********");
        System.out.println("Selecione o modo de jogo: com baralho oficial UNO ou com baralho de cartas convencional:");
        System.out.println("a) Baralho UNO oficial");
        System.out.println("b) Baralho convencional");
        Scanner sc = new Scanner(System.in);
        char modo = Character.toLowerCase(sc.next().charAt(0));
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
        char jogador = Character.toLowerCase(sc.next().charAt(0));
        int jogadorNum = jogador;
        if (modo == 'a') {
            if (jogador >= 'a' && jogador <= 'i') {
                Modo modoOficial = Modo.UNO_OFICIAL;
                UNO unoOficial = new UNO(modoOficial, jogadorNum-95);
            }
        } else if (modo == 'b') {
            if (jogador >= 'a' && jogador <= 'i') {
                Modo modoConvencional = Modo.CONVENCIONAL;
                UNO unoConvencional = new UNO(modoConvencional, jogadorNum-95);
            }
        } else {
            System.out.println("Opção inválida.");
        }
    }
}

