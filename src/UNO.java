import Baralho.Baralho;
import Baralho.BaralhoConvencional;
import Baralho.BaralhoOriginal;
import Carta.Carta;

import java.util.*;

public class UNO {
    Baralho<? extends Carta> conjuntoBaralho;
    List<? extends Carta> baralho;

    public UNO(Modo modo, int jogadores){
        if(modo == Modo.UNO_OFICIAL){
            conjuntoBaralho = new BaralhoOriginal();
            baralho = conjuntoBaralho.getBaralho();
        } else {
            conjuntoBaralho = new BaralhoConvencional();
            baralho = conjuntoBaralho.getBaralho();
        }

        // Embaralha
        Collections.shuffle(baralho);
    }
}
