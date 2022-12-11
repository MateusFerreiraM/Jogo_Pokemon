package uff.tank.seraphine;

public class Movimentos extends Identificacao {

    static enum Categoria { // Esse enum diz respeito somente aos movimentos
        FISICO,
        ESPECIAL,
        STATUS
    }

<<<<<<< HEAD
    private Tipos tipo;
    public double forca;
    public Categoria categoria;

    public Movimentos(Tipos tipo, Double forca, Categoria categoria) {
        this.forca = forca;
        this.categoria = categoria;
    }

    public double getForca() {
=======
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
>>>>>>> 7c396a327dedc009bd40a3c5838b40055d949381
        return forca;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Tipos getTipo() {
        return tipo;
    }

}
