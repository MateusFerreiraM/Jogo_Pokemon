package uff.tank.seraphine;

public class Movimentos extends Identificacao {

    static enum Categoria { // Esse enum diz respeito somente aos movimentos
        FISICO,
        ESPECIAL,
        STATUS
    }

    private static int movesCriados = 0; // O id de um movimento se baseia em quando ele foi criado
    private Tipos tipo;
    public int forca;
    public Categoria categoria;

    public Movimentos(Tipos tipo, int forca, Categoria categoria, int precisao) {

        this.id = movesCriados;
        this.forca = forca;
        this.categoria = categoria;

        movesCriados++;
    }

    public int getForca() {
        return forca;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Tipos getTipo() {
        return tipo;
    }

}
