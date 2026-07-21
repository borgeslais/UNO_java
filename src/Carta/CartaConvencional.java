package Carta;

public class CartaConvencional extends Carta {
    Simbolo simboloConv;
    Naipe naipe;

    // 1° Caso: carta normal (cor, numero, não é de ação)
    public CartaConvencional(Simbolo simboloConv, Naipe naipe){
        this.simboloConv = simboloConv;
        this.naipe = naipe;
    }

    // 2° Caso: carta de ação com cor (cor, ação, é de ação)
    public CartaConvencional(Simbolo simboloConv, Naipe naipe, Acao acao){
        this.simboloConv = simboloConv;
        this.acao = acao;
        this.naipe = naipe;
        this.eDeAcao = true;
    }

    // 3° Caso: carta curinga (eCuringa, eDeAcao)
    public CartaConvencional(Simbolo simboloConv, boolean eCuringa, Acao acao){
        this.eCuringa = true;
        this.acao = acao;
        this.simboloConv = simboloConv;
    }

    public Naipe getNaipe(){
        return this.naipe;
    }

    public Simbolo getSimbolo(){
        return this.simboloConv;
    }

    public Acao getAcao(){
         return this.acao;
    }

    public boolean getCuringa(){
        return this.eCuringa;
    }

}
