package jogo_pokemon.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import jogo_pokemon.GerenciadorDados;
import jogo_pokemon.GerenciadorDeTelas;
import jogo_pokemon.Pokemon;
import jogo_pokemon.utils.AlertUtils;

public class TelaPokedexListController {

    @FXML
    private ListView<Pokemon> listaPokedexCompleta;

    private GerenciadorDados gerenciador = new GerenciadorDados();

    @FXML
    public void initialize() {
        try {
            // Carrega TODOS os pokémons disponíveis
            List<Pokemon> todosOsPokemon = gerenciador.carregarPokemonsDisponiveis();

            ObservableList<Pokemon> observableList = FXCollections.observableArrayList(todosOsPokemon);
            listaPokedexCompleta.setItems(observableList);

            // Formata a exibição na lista para ser mais legível
            listaPokedexCompleta.setCellFactory(param -> new ListCell<Pokemon>() {
                @Override
                protected void updateItem(Pokemon item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null || item.getNome() == null) {
                        setText(null);
                    } else {
                        String tiposFormatados = item.getTipos().stream()
                                .map(Enum::toString)
                                .collect(Collectors.joining(" / "));
                        setText(String.format("#%03d - %s (Tipos: %s)", item.getId(), item.getNome(), tiposFormatados));
                    }
                }
            });

        } catch (IOException e) {
            AlertUtils.mostrarAlerta("Erro Crítico", "Não foi possível carregar a Pokédex: " + e.getMessage());
        }
    }

    @FXML
    void onVoltarClick() throws IOException {
        // Volta para o menu da Pokédex
        GerenciadorDeTelas.mudarTela("TelaPokedex.fxml");
    }
}