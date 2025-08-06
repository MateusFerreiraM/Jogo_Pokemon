package jogo_pokemon.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import jogo_pokemon.App;
import jogo_pokemon.GerenciadorDeTelas;
import jogo_pokemon.Treinador;

public class TelaIdentidadeController {

    @FXML private Label labelNome;
    @FXML private Label labelId;
    @FXML private Label labelRegiao;
    @FXML private Label labelQtdPokemon;

    @FXML
    public void initialize() {
        Treinador jogador = App.getTreinadorSessao();
        if (jogador != null) {
            labelNome.setText(jogador.getNome());
            labelId.setText(String.valueOf(jogador.getId()));
            labelRegiao.setText(jogador.getRegiao());
            labelQtdPokemon.setText(String.valueOf(jogador.getPokemons().size()));
        }
    }

    @FXML
    void onVoltarClick() throws IOException {
        GerenciadorDeTelas.mudarTela("TelaPokedex.fxml"); // Volta para o menu da Pokédex
    }
}