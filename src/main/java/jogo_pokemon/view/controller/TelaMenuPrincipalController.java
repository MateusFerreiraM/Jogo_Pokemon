package jogo_pokemon.view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import jogo_pokemon.App;
import jogo_pokemon.model.Treinador;
import jogo_pokemon.view.GerenciadorDeTelas;

public class TelaMenuPrincipalController {

    @FXML
    private Label labelBoasVindas;

    @FXML
    public void initialize() {
        Treinador treinador = App.getTreinadorSessao();
        if (treinador != null) {
            labelBoasVindas.setText("Bem-vindo, " + treinador.getNome() + "!");
        }
    }

    @FXML
    void onBatalharClick() {
        GerenciadorDeTelas.irParaTelaEscolherGinasio();
    }

    @FXML
    void onPokedexClick() {
        GerenciadorDeTelas.irParaPokedex();
    }

    @FXML
    void onMeusPokemonClick() {
        GerenciadorDeTelas.irParaMeusPokemon();
    }

    @FXML
    void onSairClick() {
        App.setTreinadorSessao(null);
        GerenciadorDeTelas.irParaTelaInicial();
    }
}
