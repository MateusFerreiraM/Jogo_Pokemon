package jogo_pokemon.view.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import jogo_pokemon.utils.GerenciadorDeMusica; // NOVO: Importa o gerenciador de música
import jogo_pokemon.view.GerenciadorDeTelas;

public class TelaDerrotaController {

    @FXML
    void onVoltarAoMenuClick() throws IOException {
        // NOVO: Para a música de batalha e volta a tocar a música de menu
        GerenciadorDeMusica.tocarMusicaMenu();

        GerenciadorDeTelas.mudarTela("TelaMenuPrincipal.fxml");
    }
}