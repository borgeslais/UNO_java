package Carta;

public class CartaOriginal extends Carta {
    Cor cor;

    // 1° Caso
    public CartaOriginal(Simbolo simbolo, Cor cor){
        super(simbolo, cor);
        this.cor = cor;
    }

    // 2° Caso
    public CartaOriginal(Simbolo simbolo, Cor cor, Acao acao){
        super(simbolo, cor, acao);
        this.cor = cor;
    }

    // 3° Caso
    public CartaOriginal(Simbolo simbolo, Acao acao) {
        super(simbolo, acao);
    }

    // Getters
    public Cor getCor(){
        return this.cor;
    }

    public void setCategoria(CategoriaCarta categoria) {
        super.setCategoria(categoria);
        this.cor = (Cor) categoria;
    }
}

