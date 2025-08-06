package jogo_pokemon.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import jogo_pokemon.App;
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
            List<Pokemon> todosOsPokemon = gerenciador.carregarPokemonsDisponiveis();
            ObservableList<Pokemon> observableList = FXCollections.observableArrayList(todosOsPokemon);
            listaPokedexCompleta.setItems(observableList);

            // **A ALTERAÇÃO ESTÁ AQUI**
            listaPokedexCompleta.setCellFactory(param -> new ListCell<Pokemon>() {
                private ImageView imageView = new ImageView();
                private Label labelInfo = new Label();
                private HBox hbox = new HBox(10, imageView, labelInfo);

                @Override
                protected void updateItem(Pokemon pokemon, boolean empty) {
                    super.updateItem(pokemon, empty);
                    if (empty || pokemon == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        // Carrega a imagem
                        try (InputStream stream = App.class.getResourceAsStream("images/" + pokemon.getImagePath())) {
                            if (stream != null) {
                                imageView.setImage(new Image(stream));
                                imageView.setFitHeight(40);
                                imageView.setFitWidth(40);
                            }
                        } catch (Exception e) {
                            imageView.setImage(null);
                        }

                        // Formata o texto
                        String tiposFormatados = pokemon.getTipos().stream()
                                .map(Enum::toString)
                                .collect(Collectors.joining(" / "));
                        labelInfo.setText(String.format("#%03d - %s\n(Tipos: %s)", pokemon.getId(), pokemon.getNome(), tiposFormatados));
                        
                        setGraphic(hbox);
                    }
                }
            });

        } catch (IOException e) {
            AlertUtils.mostrarAlerta("Erro Crítico", "Não foi possível carregar a Pokédex: " + e.getMessage());
        }
    }

    @FXML
    void onVoltarClick() throws IOException {
        GerenciadorDeTelas.mudarTela("TelaPokedex.fxml");
    }
}