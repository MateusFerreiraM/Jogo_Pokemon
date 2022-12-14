package uff.tank.seraphine;

public class Movimentos {

    static enum Categoria { // Esse enum diz respeito somente aos movimentos
        FISICO,
        ESPECIAL,
        STATUS
    }

    private Tipos tipo;
    public double forca;
    public Categoria categoria;

    public Movimentos(Tipos tipo, Double forca, Categoria categoria) {
        this.forca = forca;
        this.categoria = categoria;
    }

    public double getForca() {
        return forca;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Tipos getTipo() {
        return tipo;
    }

}
