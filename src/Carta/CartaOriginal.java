package Carta;

public class CartaOriginal extends Carta {
    int simboloOrig;
    Cor cor;

    // 1° Caso: carta tradicional (cor, numero, não é de ação)
    public CartaOriginal(Simbolo simboloOrig, Cor cor){
        this.simboloOrig = simboloOrig.getSimbolo();
        this.cor = cor;
    }

    // 2° Caso: carta de ação com cor (cor, ação, é de ação)
    public CartaOriginal(Simbolo simboloOrig, Cor cor, Acao acao){
        this.simboloOrig = simboloOrig.getSimbolo();
        this.acao = acao;
        this.cor = cor;
        this.eDeAcao = true;
    }

    // 3° Caso: carta curinga (eDeAcao)
    public CartaOriginal( Acao acao){
        this.eCuringa = true;
        this.acao = acao;
    }

    public int getSimbolo(){
        return this.simboloOrig;
    }

    public Cor getCor(){
        return this.cor;
    }

    public Acao getAcao(){
        return this.acao;
    }

    public boolean getCuringa(){
        return this.eCuringa;
    }
}

