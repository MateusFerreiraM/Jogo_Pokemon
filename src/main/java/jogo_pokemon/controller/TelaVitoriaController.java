package jogo_pokemon.controller;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import jogo_pokemon.*;

public class TelaVitoriaController {

    @FXML
    private Label labelPokemonRecompensa;

    private GerenciadorDados gerenciador = new GerenciadorDados();

    @FXML
    public void initialize() {
        try {
            Treinador jogador = App.getTreinadorSessao();
            List<Pokemon> pokemonsDisponiveis = gerenciador.carregarPokemonsDisponiveis();

            // Filtra a lista para ter apenas os Pokémon que o jogador ainda não tem
            List<String> nomesPokemonJogador = jogador.getPokemons().stream()
                    .map(Pokemon::getNome)
                    .collect(Collectors.toList());
            List<Pokemon> pokemonsDeRecompensa = pokemonsDisponiveis.stream()
                    .filter(p -> !nomesPokemonJogador.contains(p.getNome()))
                    .collect(Collectors.toList());

            if (!pokemonsDeRecompensa.isEmpty()) {
                // Sorteia um novo Pokémon e adiciona ao treinador
                Pokemon novoPokemon = pokemonsDeRecompensa.get(new Random().nextInt(pokemonsDeRecompensa.size()));
                labelPokemonRecompensa.setText(novoPokemon.getNome() + "!");
                jogador.adicionarPokemon(novoPokemon);

                // Salva o progresso
                List<Treinador> todosOsTreinadores = gerenciador.carregarTreinadores();
                for (int i = 0; i < todosOsTreinadores.size(); i++) {
                    if (todosOsTreinadores.get(i).getId() == jogador.getId()) {
                        todosOsTreinadores.set(i, jogador);
                        break;
                    }
                }
                gerenciador.salvarTreinadores(todosOsTreinadores);
            } else {
                labelPokemonRecompensa.setText("Você já capturou todos os Pokémon!");
            }

        } catch (IOException e) {
            labelPokemonRecompensa.setText("Erro ao obter recompensa.");
            e.printStackTrace();
        }
    }

    @FXML
    void onVoltarAoMenuClick() throws IOException {
        GerenciadorDeTelas.mudarTela("TelaMenuPrincipal.fxml");
    }
}