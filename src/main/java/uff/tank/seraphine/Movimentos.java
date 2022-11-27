package uff.tank.seraphine;

public class Movimentos extends Identificacao {

    static enum Categoria { // Esse enum diz respeito somente aos movimentos
        FISICO,
        ESPECIAL,
        STATUS
    }

    private static int movesCriados = 0; // O id de um movimento se baseia em quando ele foi criado
    public int forca;
    public Categoria categoria;
    public int precisao;

    public Movimentos(Tipos tipo, int forca, Categoria categoria, int precisao) {

        this.id = movesCriados;
        this.forca = forca;
        this.categoria = categoria;
        this.precisao = precisao;

        movesCriados++;
    }
}
