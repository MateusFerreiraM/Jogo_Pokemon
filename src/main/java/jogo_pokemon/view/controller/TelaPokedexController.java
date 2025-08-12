package jogo_pokemon.view.controller;

import javafx.fxml.FXML;
import jogo_pokemon.view.GerenciadorDeTelas;

/**
 * Controlador para a tela principal do Pokédex.
 * Serve como um menu de navegação para outras telas de informação, como a lista
 * completa de Pokémon, a equipa do jogador e a identidade do treinador.
 */
public class TelaPokedexController {

    /**
     * Handler do botão "Ver Todos".
     * Navega para a tela que exibe a lista de todos os Pokémon existentes no jogo.
     */
    @FXML
    void onVerTodosClick() {
        GerenciadorDeTelas.irParaPokedexList();
    }

    /**
     * Handler do botão "Ver Meus Pokémon".
     * Navega para a tela de gestão da equipa do jogador.
     */
    @FXML
    void onVerMeusPokemonClick() {
        GerenciadorDeTelas.irParaMeusPokemon();
    }

    /**
     * Handler do botão "Info. Treinador".
     * Navega para a tela de identidade do treinador, exibindo as suas informações.
     */
    @FXML
    void onVerInfoTreinadorClick() {
        GerenciadorDeTelas.irParaTelaIdentidade();
    }

    /**
     * Handler do botão "Voltar".
     * Navega o utilizador de volta para o menu principal.
     */
    @FXML
    void onVoltarClick() {
        GerenciadorDeTelas.irParaMenuPrincipal();
    }
}