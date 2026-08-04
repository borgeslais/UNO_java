package Baralho;

import Carta.Carta;
import Carta.Acao;
import Carta.Simbolo;
import Carta.CategoriaCarta;
import java.util.ArrayList;

public abstract class Baralho<T extends Carta> {
    public ArrayList<T> cartas;
    Simbolo[] simbolo;
    CategoriaCarta[] categoria;
    Acao[] acao;

    public Baralho(){
        cartas = new ArrayList<>();
        simbolo = Simbolo.values();
        categoria = new CategoriaCarta[4];
        acao = Acao.values();
    }

    // Getter
    public ArrayList<T> getBaralho(){
        return cartas;
    }
}
