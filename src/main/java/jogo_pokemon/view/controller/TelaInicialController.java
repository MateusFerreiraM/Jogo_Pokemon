package jogo_pokemon.view.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import jogo_pokemon.view.GerenciadorDeTelas;

import java.io.IOException;

public class TelaInicialController {

    @FXML
    void onSelecionarTreinadorClick() throws IOException {
        GerenciadorDeTelas.mudarTela("TelaSelecionarTreinador.fxml");
    }
    
    @FXML
    void onCriarTreinadorClick() throws IOException {
        GerenciadorDeTelas.mudarTela("TelaCriarTreinador.fxml");
    }

    @FXML
    void onSairClick() {
        Platform.exit();
    }
}