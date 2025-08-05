package uff.tank.seraphine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty; // Importe a anotação
import java.util.ArrayList;
import java.util.List;

public class Treinador {

    @JsonProperty("Nome")
    private String nome;
    
    @JsonProperty("Id")
    private int id;
    
    @JsonProperty("Regiao")
    private String regiao;
    
    @JsonProperty("Pokemons")
    private List<Pokemon> pokemons;

    @JsonIgnore
    private Pokemon pokemonAtual;

    // Construtor vazio é necessário para o Jackson
    public Treinador() {
        this.pokemons = new ArrayList<>();
    }
    
    // Construtor para criar um novo treinador
    public Treinador(String nome, String regiao, int id) {
        this.nome = nome;
        this.regiao = regiao;
        this.id = id;
        this.pokemons = new ArrayList<>();
    }

    // Getters e Setters para todos os campos que serão salvos no JSON
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    // Métodos de negócio (não precisam de setter)
    @JsonIgnore
    public Pokemon getPokemonAtual() {
        // Lógica para garantir que sempre haja um pokemon atual se a lista não estiver vazia
        if (pokemonAtual == null && !pokemons.isEmpty()) {
            this.pokemonAtual = pokemons.get(0);
        }
        return pokemonAtual;
    }

    public void setPokemonAtual(Pokemon pokemon) {
        // Garante que o pokemon a ser definido como atual esteja na lista do treinador
        if (this.pokemons.contains(pokemon)) {
            this.pokemonAtual = pokemon;
        }
    }

    public void adicionarPokemon(Pokemon pokemon) {
        if (this.pokemons.isEmpty()) {
            this.pokemonAtual = pokemon; // O primeiro a ser adicionado se torna o atual
        }
        this.pokemons.add(pokemon);
    }

    @Override
    public String toString() {
        return "Treinador {" + "id = " + id + ", nome = " + nome + ", regiao = " + regiao + "}";
    }
}