package jogo_pokemon.view.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import jogo_pokemon.view.GerenciadorDeTelas;

/**
 * Controlador para a tela inicial do jogo, a primeira tela que o utilizador vê.
 * Oferece as opções de iniciar um novo jogo ou carregar um existente.
 */
public class TelaInicialController {

    /**
     * Handler do botão "Selecionar Treinador".
     * Navega o utilizador para a tela de seleção de perfis existentes.
     */
    @FXML
    void onSelecionarTreinadorClick() {
        GerenciadorDeTelas.irParaTelaSelecionarTreinador();
    }
    
    /**
     * Handler do botão "Criar Treinador".
     * Navega o utilizador para a tela de criação de um novo perfil.
     */
    @FXML
    void onCriarTreinadorClick() {
        GerenciadorDeTelas.irParaTelaCriarTreinador();
    }

    /**
     * Handler do botão "Sair".
     * Encerra a aplicação.
     */
    @FXML
    void onSairClick() {
        Platform.exit();
    }
}