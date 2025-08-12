package jogo_pokemon.view.controller;

import javafx.fxml.FXML;
import jogo_pokemon.utils.GerenciadorDeMusica;
import jogo_pokemon.view.GerenciadorDeTelas;

/**
 * Controlador para a tela de derrota, exibida quando o jogador perde uma batalha.
 */
public class TelaDerrotaController {

    /**
     * Handler do botão "Voltar ao Menu Principal".
     * Retoma a música de menu e navega o utilizador de volta para a tela do menu principal.
     */
    @FXML
    void onVoltarAoMenuClick() {
        GerenciadorDeMusica.tocarMusicaMenu();
        GerenciadorDeTelas.irParaMenuPrincipal();
    }
}