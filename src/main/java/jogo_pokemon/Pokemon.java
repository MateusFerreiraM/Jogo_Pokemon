package jogo_pokemon;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // 1. IMPORTE A ANOTAÇÃO
import com.fasterxml.jackson.annotation.JsonProperty;
import jogo_pokemon.Movimentos.Categoria;
import java.util.ArrayList;
import java.util.List;

// 2. ADICIONE A ANOTAÇÃO AQUI, ACIMA DA DECLARAÇÃO DA CLASSE
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
    
    // Construtor vazio para o Jackson
    public Pokemon() {
        this.tipos = new ArrayList<>();
        this.movimentosList = new ArrayList<>();
    }

    // Construtor principal para criar um Pokémon a partir dos dados
    public Pokemon(String nome, int id, List<Tipos> tipos, int ataque, int defesa, int hp) {
        this.id = id;
        this.nome = nome;
        this.tipos = tipos;
        this.ataque = ataque;
        this.defesa = defesa;
        this.hp = hp;
        this.hpAtual = this.hp;
        
        // Mantemos a lógica de criar movimentos padrão
        this.movimentosList = new ArrayList<>();
        if (tipos != null && !tipos.isEmpty()) {
            this.movimentosList.add(new Movimentos(tipos.get(0), 1.0, Categoria.FISICO));
            if (tipos.size() == 1) {
                this.movimentosList.add(new Movimentos(tipos.get(0), 1.15, Categoria.ESPECIAL));
            } else {
                this.movimentosList.add(new Movimentos(tipos.get(1), 1.15, Categoria.ESPECIAL));
            }
        }
    }

    public void inicializarMovimentos() {
        // Se a lista de movimentos já foi inicializada, não faz nada.
        if (this.movimentosList != null && !this.movimentosList.isEmpty()) {
            return;
        }

        this.movimentosList = new ArrayList<>();
        if (this.tipos != null && !this.tipos.isEmpty()) {
            // Usando o campo 'forca' que já corrigimos na classe Movimentos
            this.movimentosList.add(new Movimentos(tipos.get(0), 1.0, Categoria.FISICO));
            if (tipos.size() == 1) {
                this.movimentosList.add(new Movimentos(tipos.get(0), 1.15, Categoria.ESPECIAL));
            } else {
                this.movimentosList.add(new Movimentos(tipos.get(1), 1.15, Categoria.ESPECIAL));
            }
        }
    }

    // Getters e Setters para que o Jackson possa ler e escrever as propriedades
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public List<Tipos> getTipos() { return tipos; }
    public void setTipos(List<Tipos> tipos) { this.tipos = tipos; }
    
    public List<Movimentos> getMovimentosList() { return movimentosList; }
    public void setMovimentosList(List<Movimentos> movimentosList) { this.movimentosList = movimentosList; }

    public int getHp() { return hp; }
    public void setHp(int hp) {
        this.hp = hp;
        this.hpAtual = hp;
    }
    
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getAtaque() { return ataque; }
    public void setAtaque(int ataque) { this.ataque = ataque; }
    
    public int getDefesa() { return defesa; }
    public void setDefesa(int defesa) { this.defesa = defesa; }

    // Métodos de negócio
    @JsonIgnore
    public int getHpAtual() { return hpAtual; }
    public void setHpAtual(int hpAtual) { this.hpAtual = hpAtual; }

    @JsonIgnore
    public boolean estaVivo() {
        return this.hpAtual > 0;
    }
    
    public void perdeHp(double dano) {
        int danoX = (int) dano;
        if (this.estaVivo()) {
            this.hpAtual -= danoX;
        }
    }
}   