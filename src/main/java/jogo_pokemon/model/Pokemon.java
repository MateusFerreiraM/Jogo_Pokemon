package jogo_pokemon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jogo_pokemon.model.Movimentos.Categoria;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma criatura Pokémon, com os seus atributos, estatísticas e movimentos.
 * Esta classe é mapeada a partir do ficheiro pokemon.json.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon {

    @JsonProperty("Nome")
    private String nome;

    @JsonProperty("Id")
    private int id;

    @JsonProperty("tipos")
    private List<Tipos> tipos;

    private List<Movimentos> movimentosList;

    @JsonProperty("HP")
    private int hp;

    @JsonProperty("Ataque")
    private int ataque;

    @JsonProperty("Defesa")
    private int defesa;

    @JsonIgnore
    private int hpAtual;

    @JsonProperty("ImagePath")
    private String imagePath;
    
    /**
     * Construtor padrão necessário para a deserialização JSON (Jackson).
     */
    public Pokemon() {
        this.tipos = new ArrayList<>();
        this.movimentosList = new ArrayList<>();
    }

    /**
     * Inicializa os movimentos de um Pokémon com base nos seus tipos.
     * Este método é chamado após um Pokémon ser carregado dos dados para garantir que ele tenha ataques.
     */
    public void inicializarMovimentos() {
        if (this.movimentosList != null && !this.movimentosList.isEmpty()) {
            return; // Já inicializado, não faz nada.
        }

        this.movimentosList = new ArrayList<>();
        if (this.tipos != null && !this.tipos.isEmpty()) {
            this.movimentosList.add(new Movimentos(tipos.get(0), 1.0, Categoria.FISICO));
            if (tipos.size() == 1) {
                this.movimentosList.add(new Movimentos(tipos.get(0), 1.15, Categoria.ESPECIAL));
            } else {
                this.movimentosList.add(new Movimentos(tipos.get(1), 1.15, Categoria.ESPECIAL));
            }
        }
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public List<Tipos> getTipos() { return tipos; }
    public void setTipos(List<Tipos> tipos) { this.tipos = tipos; }
    
    public List<Movimentos> getMovimentosList() { return movimentosList; }
    public void setMovimentosList(List<Movimentos> movimentosList) { this.movimentosList = movimentosList; }

    public int getHp() { return hp; }
    public void setHp(int hp) { this.hp = hp; this.hpAtual = hp; }
    
    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public int getAtaque() { return ataque; }
    public void setAtaque(int ataque) { this.ataque = ataque; }
    
    public int getDefesa() { return defesa; }
    public void setDefesa(int defesa) { this.defesa = defesa; }

    @JsonIgnore
    public int getHpAtual() { return hpAtual; }
    public void setHpAtual(int hpAtual) { this.hpAtual = hpAtual; }

    /**
     * Verifica se o Pokémon ainda tem pontos de vida.
     * @return true se o HP atual for maior que zero, caso contrário false.
     */
    @JsonIgnore
    public boolean estaVivo() {
        return this.hpAtual > 0;
    }
}