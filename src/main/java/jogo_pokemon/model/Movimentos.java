package jogo_pokemon.model;

// Assumindo que a classe Movimentos ainda não existe ou pode ser substituída.
// Se ela tiver mais lógica, podemos adaptar.
public class Movimentos {

    public enum Categoria { FISICO, ESPECIAL }

    private Tipos tipo;
    private double forca; // Renomeado de 'multiplicador' para 'forca'
    private Categoria categoria;

    public Movimentos() {}

    public Movimentos(Tipos tipo, double forca, Categoria categoria) {
        this.tipo = tipo;
        this.forca = forca; // Ajustado aqui
        this.categoria = categoria;
    }

    // Getters e Setters
    public Tipos getTipo() { return tipo; }
    public void setTipo(Tipos tipo) { this.tipo = tipo; }

    public double getForca() { return forca; } // Ajustado aqui
    public void setForca(double forca) { this.forca = forca; } // Ajustado aqui

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}