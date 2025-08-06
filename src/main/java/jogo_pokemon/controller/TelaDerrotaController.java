package jogo_pokemon.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import jogo_pokemon.GerenciadorDeTelas;

public class TelaDerrotaController {

    @FXML
    void onVoltarAoMenuClick() throws IOException {
        GerenciadorDeTelas.mudarTela("TelaMenuPrincipal.fxml");
    }
}