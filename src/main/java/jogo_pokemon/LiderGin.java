package jogo_pokemon;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

// LiderGin ainda herda de Treinador, mas terá seu próprio construtor para o Jackson
public class LiderGin extends Treinador {

    // Este campo será lido do JSON ("Charmander" ou "random")
    @JsonProperty("Pokemon")
    private String pokemonName;

    // Construtor vazio para o Jackson
    public LiderGin() {
        super();
    }

    /**
     * Este método especial será chamado após o líder ser carregado do JSON.
     * Ele pega o nome do Pokémon (pokemonName) e o transforma no objeto Pokemon real.
     */
    public void carregarPokemon(List<Pokemon> pokemonsDisponiveis) {
        if ("random".equalsIgnoreCase(pokemonName)) {
            // Lógica para pegar um pokemon aleatório da lista
            int randomIndex = new java.util.Random().nextInt(pokemonsDisponiveis.size());
            this.adicionarPokemon(pokemonsDisponiveis.get(randomIndex));
        } else {
            // Procura o pokemon pelo nome na lista de todos os pokemons
            pokemonsDisponiveis.stream()
                .filter(p -> p.getNome().equalsIgnoreCase(this.pokemonName))
                .findFirst()
                .ifPresent(this::adicionarPokemon);
        }
    }
}