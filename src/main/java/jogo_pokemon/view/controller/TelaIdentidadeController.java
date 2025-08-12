package jogo_pokemon.view.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import jogo_pokemon.App;
import jogo_pokemon.model.Treinador;
import jogo_pokemon.view.GerenciadorDeTelas;

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
    void onVoltarClick() {
        GerenciadorDeTelas.irParaPokedex();
    }
}