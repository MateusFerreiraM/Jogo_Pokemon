package jogo_pokemon.view.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import jogo_pokemon.App;
import jogo_pokemon.model.Treinador;
import jogo_pokemon.view.GerenciadorDeTelas;

/**
 * Controlador para a tela de Identidade do Treinador, que exibe as informações do jogador.
 */
public class TelaIdentidadeController {

    @FXML private Text labelNome;
    @FXML private Text labelId;
    @FXML private Text labelRegiao;
    @FXML private Text labelQtdPokemon;

    /**
     * Inicializa o controlador. Carrega os dados do treinador que está na sessão
     * e preenche os campos de texto com as informações correspondentes.
     */
    @FXML
    public void initialize() {
        Treinador jogador = App.getTreinadorSessao();
        if (jogador != null) {
            labelNome.setText(jogador.getNome());
            labelId.setText(String.valueOf(jogador.getId()));
            labelRegiao.setText(jogador.getRegiao());
            labelQtdPokemon.setText(String.valueOf(jogador.getPokemons().size()));
        }
    }

    /**
     * Handler do botão "Voltar".
     * Navega o utilizador de volta para a tela do Pokédex.
     */
    @FXML
    void onVoltarClick() {
        GerenciadorDeTelas.irParaPokedex();
    }
}