package Carta;

public enum Simbolo {
    AS(0),
    DOIS(1),
    TRES(2),
    QUATRO(3),
    CINCO(4),
    SEIS(5),
    SETE(6),
    OITO(7),
    NOVE(8),
    DEZ(9),
    VALETE(10),
    DAMA(11),
    REI(12),
    CURINGA_BLACK(13),
    CURINGA_RED(14);

    private final int simbolo;

    Simbolo(int simbolo) {
        this.simbolo = simbolo;
    }

    public int getValor() {
        return this.simbolo;
    }
}
