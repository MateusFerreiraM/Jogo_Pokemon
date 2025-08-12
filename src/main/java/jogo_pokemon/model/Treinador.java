package jogo_pokemon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Representa um treinador de Pokémon, com os seus dados pessoais e a sua equipa de Pokémon.
 */
public class Treinador {

    @JsonProperty("Nome")
    private String nome;
    
    @JsonProperty("Id")
    private int id;
    
    @JsonProperty("Regiao")
    private String regiao;
    
    @JsonProperty("Pokemons")
    private List<Pokemon> pokemons;

    @JsonProperty("PokemonAtualId")
    private int pokemonAtualId;

    @JsonIgnore
    private Pokemon pokemonAtual;

    public Treinador() {
        this.pokemons = new ArrayList<>();
    }
    
    public Treinador(String nome, String regiao, int id) {
        this.nome = nome;
        this.regiao = regiao;
        this.id = id;
        this.pokemons = new ArrayList<>();
    }

    /**
     * Obtém o Pokémon atualmente selecionado pelo treinador para batalhas.
     * Se o Pokémon atual não estiver definido, ele é carregado a partir do ID guardado (`pokemonAtualId`).
     * Se o ID não for encontrado, o primeiro Pokémon da lista é usado como padrão.
     * @return O Pokémon ativo do treinador.
     */
    @JsonIgnore
    public Pokemon getPokemonAtual() {
        if (pokemonAtual == null && !pokemons.isEmpty()) {
            Optional<Pokemon> encontrado = pokemons.stream()
                .filter(p -> p.getId() == this.pokemonAtualId)
                .findFirst();
            
            this.pokemonAtual = encontrado.orElse(pokemons.get(0));
        }
        return pokemonAtual;
    }

    /**
     * Define o Pokémon ativo do treinador e atualiza o ID correspondente.
     * @param pokemon O Pokémon da equipa a ser definido como ativo.
     */
    public void setPokemonAtual(Pokemon pokemon) {
        if (pokemon != null && this.pokemons.contains(pokemon)) {
            this.pokemonAtual = pokemon;
            this.pokemonAtualId = pokemon.getId();
        }
    }

    /**
     * Adiciona um novo Pokémon à equipa do treinador.
     * Se for o primeiro Pokémon, ele é automaticamente definido como o ativo.
     * @param pokemon O Pokémon a ser adicionado.
     */
    public void adicionarPokemon(Pokemon pokemon) {
        if (this.pokemons.isEmpty()) {
            // Define o primeiro Pokémon adicionado como o atual por padrão
            this.pokemonAtual = pokemon;
            this.pokemonAtualId = pokemon.getId();
        }
        this.pokemons.add(pokemon);
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getRegiao() { return regiao; }
    public void setRegiao(String regiao) { this.regiao = regiao; }
    public List<Pokemon> getPokemons() { return pokemons; }
    public void setPokemons(List<Pokemon> pokemons) { this.pokemons = pokemons; }
    public int getPokemonAtualId() { return pokemonAtualId; }
    public void setPokemonAtualId(int id) { this.pokemonAtualId = id; }

    @Override
    public String toString() {
        return "Treinador {" + "id = " + id + ", nome = " + nome + ", regiao = " + regiao + "}";
    }
}