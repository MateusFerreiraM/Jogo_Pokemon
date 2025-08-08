package jogo_pokemon.view.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import jogo_pokemon.view.GerenciadorDeTelas;

public class TelaPokedexController {

    // Novo método para o novo botão
    @FXML
    void onVerTodosClick() throws IOException {
        GerenciadorDeTelas.mudarTela("TelaPokedexList.fxml");
    }

    @FXML
    void onVerMeusPokemonClick() throws IOException {
        GerenciadorDeTelas.mudarTela("TelaMeusPokemon.fxml");
    }

    @FXML
    void onVerInfoTreinadorClick() throws IOException {
        GerenciadorDeTelas.mudarTela("TelaIdentidade.fxml");
    }



    @FXML
    void onVoltarClick() throws IOException {
        GerenciadorDeTelas.mudarTela("TelaMenuPrincipal.fxml");
    }
}