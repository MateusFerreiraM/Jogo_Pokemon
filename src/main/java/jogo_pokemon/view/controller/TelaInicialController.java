package jogo_pokemon.view.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import jogo_pokemon.view.GerenciadorDeTelas;

public class TelaInicialController {

    @FXML
    void onSelecionarTreinadorClick() {
        GerenciadorDeTelas.irParaTelaSelecionarTreinador();
    }
    
    @FXML
    void onCriarTreinadorClick() {
        GerenciadorDeTelas.irParaTelaCriarTreinador();
    }

    @FXML
    void onSairClick() {
        Platform.exit();
    }
}