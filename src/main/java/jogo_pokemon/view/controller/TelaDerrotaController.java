package jogo_pokemon.view.controller;

import javafx.fxml.FXML;
import jogo_pokemon.utils.GerenciadorDeMusica;
import jogo_pokemon.view.GerenciadorDeTelas;

public class TelaDerrotaController {

    @FXML
    void onVoltarAoMenuClick() {
        GerenciadorDeMusica.tocarMusicaMenu();
        GerenciadorDeTelas.irParaMenuPrincipal();
    }
}