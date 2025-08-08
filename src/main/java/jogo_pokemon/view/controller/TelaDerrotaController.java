package jogo_pokemon.view.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import jogo_pokemon.view.GerenciadorDeTelas;

public class TelaDerrotaController {

    @FXML
    void onVoltarAoMenuClick() throws IOException {
        GerenciadorDeTelas.mudarTela("TelaMenuPrincipal.fxml");
    }
}