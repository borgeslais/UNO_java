package Carta;

import Jogo.Modo;

public abstract class Carta {
    boolean eDeAcao = false;
    Acao acao;
    boolean eCuringa = false;
    Simbolo simbolo;
    CategoriaCarta categoria;

    // Construtores
    // 1° Caso: carta tradicional (cor, numero, não é de ação)
    public Carta(Simbolo simbolo, CategoriaCarta categoria) {
        this.simbolo = simbolo;
        this.categoria = categoria;
    }

    // 2° Caso: carta de ação com cor (cor, ação, é de ação)
    public Carta(Simbolo simbolo, CategoriaCarta categoria, Acao acao) {
        this.simbolo = simbolo;
        this.categoria = categoria;
        this.acao = acao;
        this.eDeAcao = true;
    }

    // 3° Caso: carta curinga (eCuringa, eDeAcao)
    public Carta(Simbolo simbolo, Acao acao) {
        this.simbolo = simbolo;
        this.acao = acao;
        this.eCuringa = true;
    }

    // Getters
    public Simbolo getSimbolo() {
        return this.simbolo;
    }

    public int getValor() {
        return simbolo.getValor();
    }

    public CategoriaCarta getCategoria() {
        return this.categoria;
    }

    public boolean getIsDeAcao() {
        return eDeAcao;
    }

    public Acao getAcao() {
        return acao;
    }

    public boolean getIsCuringa() {
        return eCuringa;
    }


}