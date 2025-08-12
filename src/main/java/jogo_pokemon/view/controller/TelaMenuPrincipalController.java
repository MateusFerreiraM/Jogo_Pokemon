package jogo_pokemon.view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import jogo_pokemon.App;
import jogo_pokemon.model.Treinador;
import jogo_pokemon.view.GerenciadorDeTelas;

/**
 * Controlador para a tela do menu principal.
 * Age como o hub central de navegação do jogo após o login do treinador.
 */
public class TelaMenuPrincipalController {

    @FXML
    private Label labelBoasVindas;

    /**
     * Inicializa o controlador. Obtém o treinador da sessão atual
     * e exibe uma mensagem de boas-vindas personalizada.
     */
    @FXML
    public void initialize() {
        Treinador treinador = App.getTreinadorSessao();
        if (treinador != null) {
            labelBoasVindas.setText("Bem-vindo, " + treinador.getNome() + "!");
        }
    }

    /**
     * Handler do botão "Batalhar!".
     * Navega para a tela de escolha de ginásio.
     */
    @FXML
    void onBatalharClick() {
        GerenciadorDeTelas.irParaTelaEscolherGinasio();
    }

    /**
     * Handler do botão "Pokédex".
     * Navega para a tela principal do Pokédex.
     */
    @FXML
    void onPokedexClick() {
        GerenciadorDeTelas.irParaPokedex();
    }

    /**
     * Handler do botão "Meus Pokémon".
     * Navega para a tela de gestão da equipa do jogador.
     */
    @FXML
    void onMeusPokemonClick() {
        GerenciadorDeTelas.irParaMeusPokemon();
    }

    /**
     * Handler do botão "Sair".
     * Limpa a sessão do treinador atual (logout) e retorna para a tela inicial.
     */
    @FXML
    void onSairClick() {
        App.setTreinadorSessao(null);
        GerenciadorDeTelas.irParaTelaInicial();
    }
}