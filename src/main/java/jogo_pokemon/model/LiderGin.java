package jogo_pokemon.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Random;

/**
 * Representa um Líder de Ginásio, um tipo especial de Treinador.
 * O seu Pokémon pode ser pré-definido ou aleatório, com base no ficheiro lideres.json.
 */
public class LiderGin extends Treinador {

    @JsonProperty("Pokemon")
    private String pokemonName;

    /**
     * Construtor padrão para deserialização JSON.
     */
    public LiderGin() {
        super();
    }

    /**
     * Associa o objeto Pokémon correto ao líder com base no nome lido do JSON.
     * Se o nome for "random", um Pokémon aleatório é escolhido.
     * @param pokemonsDisponiveis A lista de todos os Pokémon do jogo.
     */
    public void carregarPokemon(List<Pokemon> pokemonsDisponiveis) {
        if ("random".equalsIgnoreCase(pokemonName)) {
            int randomIndex = new Random().nextInt(pokemonsDisponiveis.size());
            this.adicionarPokemon(pokemonsDisponiveis.get(randomIndex));
        } else {
            pokemonsDisponiveis.stream()
                .filter(p -> p.getNome().equalsIgnoreCase(this.pokemonName))
                .findFirst()
                .ifPresent(this::adicionarPokemon);
        }
    }
}