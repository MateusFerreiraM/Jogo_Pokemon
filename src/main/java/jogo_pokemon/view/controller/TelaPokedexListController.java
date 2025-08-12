package jogo_pokemon.view.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import jogo_pokemon.App;
import jogo_pokemon.data.GerenciadorDados;
import jogo_pokemon.model.Pokemon;
import jogo_pokemon.utils.AlertUtils;
import jogo_pokemon.view.GerenciadorDeTelas;

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

            // REFATORAÇÃO VISUAL DA LISTA
            listaPokedexCompleta.setCellFactory(param -> new ListCell<Pokemon>() {
                private final ImageView imageView = new ImageView();
                
                private final Text idKeyText = new Text("#ID: ");
                private final Text idValueText = new Text();
                private final HBox idBox = new HBox(idKeyText, idValueText);
                
                private final Text nomeKeyText = new Text("Nome: ");
                private final Text nomeValueText = new Text();
                private final HBox nomeBox = new HBox(nomeKeyText, nomeValueText);
                
                private final Text tiposKeyText = new Text("Tipos: ");
                private final Text tiposValueText = new Text();
                private final HBox tiposBox = new HBox(tiposKeyText, tiposValueText);
                
                private final VBox textVBox = new VBox(idBox, nomeBox, tiposBox);
                private final HBox cellHBox = new HBox(15, imageView, textVBox);

                {
                    // Aplica as classes de estilo
                    idKeyText.getStyleClass().add("list-item-key");
                    nomeKeyText.getStyleClass().add("list-item-key");
                    tiposKeyText.getStyleClass().add("list-item-key");

                    idValueText.getStyleClass().add("list-item-value");
                    nomeValueText.getStyleClass().add("list-item-value");
                    tiposValueText.getStyleClass().add("list-item-value");
                    
                    cellHBox.setAlignment(Pos.CENTER_LEFT);
                }

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
                                imageView.setFitHeight(50);
                                imageView.setFitWidth(50);
                            }
                        } catch (Exception e) { imageView.setImage(null); }

                        // Define os textos
                        idValueText.setText(String.format("%03d", pokemon.getId()));
                        nomeValueText.setText(pokemon.getNome());
                        String tiposFormatados = pokemon.getTipos().stream()
                                .map(Enum::toString)
                                .collect(Collectors.joining(" / "));
                        tiposValueText.setText(tiposFormatados);
                        
                        setGraphic(cellHBox);
                    }
                }
            });

        } catch (IOException e) {
            AlertUtils.mostrarAlerta("Erro Crítico", "Não foi possível carregar a Pokédex: " + e.getMessage());
        }
    }

    @FXML
    void onVoltarClick() {
        GerenciadorDeTelas.irParaPokedex();
    }
}