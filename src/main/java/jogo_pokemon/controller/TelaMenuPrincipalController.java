package jogo_pokemon.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import jogo_pokemon.App;
import jogo_pokemon.GerenciadorDeTelas;
import jogo_pokemon.Treinador;

public class TelaMenuPrincipalController {

    @FXML
    private Label labelBoasVindas;

    @FXML
    public void initialize() {
        Treinador treinador = App.getTreinadorSessao();
        if (treinador != null) {
            labelBoasVindas.setText("Bem-vindo, " + treinador.getNome() + "!");
        }
    }

    @FXML
    void onBatalharClick() throws IOException {
        // FINALIZANDO A LÓGICA DESTE BOTÃO:
        // Garante que este botão leva para a tela de ESCOLHA DE GINÁSIO.
        GerenciadorDeTelas.mudarTela("TelaEscolherGinasio.fxml");
    }

    @FXML
    void onPokedexClick() throws IOException {
        // CORREÇÃO: Adiciona a navegação que estava em falta
        GerenciadorDeTelas.mudarTela("TelaPokedex.fxml");
    }

    @FXML
    void onMeusPokemonClick() throws IOException {
        GerenciadorDeTelas.mudarTela("TelaMeusPokemon.fxml");
    }

    @FXML
    void onSairClick() throws IOException {
        App.setTreinadorSessao(null);
        GerenciadorDeTelas.mudarTela("TelaInicial.fxml");
    }
}