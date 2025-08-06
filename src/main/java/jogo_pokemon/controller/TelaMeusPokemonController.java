package jogo_pokemon.controller;

import java.io.IOException;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import jogo_pokemon.App;
import jogo_pokemon.GerenciadorDeTelas;
import jogo_pokemon.Pokemon;
import jogo_pokemon.Treinador;

public class TelaMeusPokemonController {

    @FXML
    private ListView<Pokemon> listaMeusPokemon;

    @FXML
    public void initialize() {
        Treinador jogador = App.getTreinadorSessao();

        if (jogador != null && jogador.getPokemons() != null) {
            // Converte a lista de pokémons do jogador para um formato que a ListView entende
            ObservableList<Pokemon> observableList = FXCollections.observableArrayList(jogador.getPokemons());
            listaMeusPokemon.setItems(observableList);

            // Formata a exibição na lista para ser mais legível
            listaMeusPokemon.setCellFactory(param -> new ListCell<Pokemon>() {
                @Override
                protected void updateItem(Pokemon item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null || item.getNome() == null) {
                        setText(null);
                    } else {
                        String tiposFormatados = item.getTipos().stream()
                                .map(Enum::toString)
                                .collect(Collectors.joining(" / "));
                        setText(item.getNome() + " (HP: " + item.getHpAtual() + "/" + item.getHp() + " | Tipos: " + tiposFormatados + ")");
                    }
                }
            });
        }
    }

    @FXML
    void onVoltarClick() throws IOException {
        GerenciadorDeTelas.mudarTela("TelaPokedex.fxml");
    }
}