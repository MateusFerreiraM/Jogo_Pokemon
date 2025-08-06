package jogo_pokemon;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Treinador {

    @JsonProperty("Nome")
    private String nome;
    
    @JsonProperty("Id")
    private int id;
    
    @JsonProperty("Regiao")
    private String regiao;
    
    @JsonProperty("Pokemons")
    private List<Pokemon> pokemons;

    // NOVO CAMPO: Guarda o ID do Pokémon atual no JSON
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

    // --- Getters e Setters ---
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

    // --- Lógica Corrigida para o Pokémon Atual ---

    @JsonIgnore
    public Pokemon getPokemonAtual() {
        // Se o objeto pokemonAtual ainda não foi definido...
        if (pokemonAtual == null && !pokemons.isEmpty()) {
            // ...procura na lista pelo Pokémon com o ID guardado.
            Optional<Pokemon> encontrado = pokemons.stream()
                .filter(p -> p.getId() == this.pokemonAtualId)
                .findFirst();
            
            // Se o encontrar, define-o como o atual. Senão, usa o primeiro como fallback.
            this.pokemonAtual = encontrado.orElse(pokemons.get(0));
        }
        return pokemonAtual;
    }

    public void setPokemonAtual(Pokemon pokemon) {
        if (pokemon != null && this.pokemons.contains(pokemon)) {
            this.pokemonAtual = pokemon;
            this.pokemonAtualId = pokemon.getId(); // Atualiza também o ID a ser guardado
        }
    }

    public void adicionarPokemon(Pokemon pokemon) {
        if (this.pokemons.isEmpty()) {
            setPokemonAtual(pokemon); // Usa o novo método para definir o primeiro como atual
        }
        this.pokemons.add(pokemon);
    }

    @Override
    public String toString() {
        return "Treinador {" + "id = " + id + ", nome = " + nome + ", regiao = " + regiao + "}";
    }
}