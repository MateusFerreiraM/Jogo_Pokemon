package jogo_pokemon.model;

/**
 * Representa um movimento (ataque) que um Pokémon pode utilizar.
 * Um movimento tem um tipo elemental, uma força base e uma categoria (físico ou especial).
 */
public class Movimentos {

    /**
     * Define a categoria de um movimento, que influencia o cálculo de dano.
     */
    public enum Categoria { FISICO, ESPECIAL }

    private Tipos tipo;
    private double forca;
    private Categoria categoria;

    /**
     * Construtor padrão para deserialização.
     */
    public Movimentos() {}

    /**
     * Cria um novo movimento com os atributos especificados.
     * @param tipo O tipo elemental do movimento.
     * @param forca A força base do movimento.
     * @param categoria A categoria do movimento (FISICO ou ESPECIAL).
     */
    public Movimentos(Tipos tipo, double forca, Categoria categoria) {
        this.tipo = tipo;
        this.forca = forca;
        this.categoria = categoria;
    }

    // Getters e Setters
    public Tipos getTipo() { return tipo; }
    public void setTipo(Tipos tipo) { this.tipo = tipo; }

    public double getForca() { return forca; }
    public void setForca(double forca) { this.forca = forca; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}