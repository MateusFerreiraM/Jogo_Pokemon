package jogo_pokemon.view.controller;

import javafx.fxml.FXML;
import jogo_pokemon.view.GerenciadorDeTelas;

public class TelaPokedexController {

    @FXML
    void onVerTodosClick() {
        GerenciadorDeTelas.irParaPokedexList();
    }

    @FXML
    void onVerMeusPokemonClick() {
        GerenciadorDeTelas.irParaMeusPokemon();
    }

    @FXML
    void onVerInfoTreinadorClick() {
        GerenciadorDeTelas.irParaTelaIdentidade();
    }

    @FXML
    void onVoltarClick() {
        GerenciadorDeTelas.irParaMenuPrincipal();
    }
}