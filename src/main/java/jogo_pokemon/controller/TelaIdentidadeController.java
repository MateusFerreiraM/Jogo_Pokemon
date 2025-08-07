package jogo_pokemon.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.text.Text; // Importar Text em vez de Label
import jogo_pokemon.App;
import jogo_pokemon.GerenciadorDeTelas;
import jogo_pokemon.Treinador;

public class TelaIdentidadeController {

    // CORRIGIDO para Text
    @FXML private Text labelNome;
    @FXML private Text labelId;
    @FXML private Text labelRegiao;
    @FXML private Text labelQtdPokemon;

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
        GerenciadorDeTelas.mudarTela("TelaPokedex.fxml");
    }
}