package Carta;

public class CartaConvencional extends Carta {
    Naipe naipe;

    // 1° Caso
    public CartaConvencional(Simbolo simbolo, Naipe naipe){
        super(simbolo, naipe);
        this.naipe = naipe;
    }

    // 2° Caso
    public CartaConvencional(Simbolo simbolo, Naipe naipe, Acao acao){
        super(simbolo, naipe, acao);
        this.naipe = naipe;
    }

    // 3° Caso
    public CartaConvencional(Simbolo simbolo, Acao acao) {
        super(simbolo, acao);
    }

    // Getters
    public Naipe getNaipe() {
        return this.naipe;
    }



}
