package jogo_pokemon.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import jogo_pokemon.App;
import jogo_pokemon.data.GerenciadorDados;
import jogo_pokemon.model.Pokemon;
import jogo_pokemon.model.Treinador;
import jogo_pokemon.utils.AlertUtils;
import jogo_pokemon.view.GerenciadorDeTelas;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class TelaSelecionarTreinadorController {

    @FXML
    private ListView<Treinador> listaTreinadores;

    private GerenciadorDados gerenciador = new GerenciadorDados();

   @FXML
    public void initialize() {
        try {
            List<Treinador> treinadores = gerenciador.carregarTreinadores();
            ObservableList<Treinador> observableList = FXCollections.observableArrayList(treinadores);
            listaTreinadores.setItems(observableList);

            listaTreinadores.setCellFactory(param -> new ListCell<Treinador>() {
                private final Text nomeKeyText = new Text("Nome: ");
                private final Text nomeValueText = new Text();
                private final HBox nomeBox = new HBox(nomeKeyText, nomeValueText);

                private final Text regiaoKeyText = new Text("Região: ");
                private final Text regiaoValueText = new Text();
                private final HBox regiaoBox = new HBox(regiaoKeyText, regiaoValueText);

                private final Text pokemonCountKeyText = new Text("Pokémons Capturados: ");
                private final Text pokemonCountValueText = new Text();
                private final HBox pokemonCountBox = new HBox(pokemonCountKeyText, pokemonCountValueText);
                
                private final VBox cellBox = new VBox(nomeBox, regiaoBox, pokemonCountBox);

                {
                    nomeKeyText.getStyleClass().add("list-item-key");
                    regiaoKeyText.getStyleClass().add("list-item-key");
                    pokemonCountKeyText.getStyleClass().add("list-item-key");

                    nomeValueText.getStyleClass().add("list-item-value");
                    regiaoValueText.getStyleClass().add("list-item-value");
                    pokemonCountValueText.getStyleClass().add("list-item-value");
                    
                    getStyleClass().add("treinador-list-cell");
                }

                @Override
                protected void updateItem(Treinador item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        nomeValueText.setText(item.getNome());
                        regiaoValueText.setText(item.getRegiao());
                         pokemonCountValueText.setText(String.valueOf(item.getPokemons().size()));
                        
                        setGraphic(cellBox);
                    }
                }
            });
        } catch (IOException e) {
            AlertUtils.mostrarAlerta("Erro Crítico", "Não foi possível carregar a lista de treinadores: " + e.getMessage());
            listaTreinadores.setItems(FXCollections.observableArrayList(Collections.emptyList()));
        }
    }

    @FXML
    void onConfirmarClick() throws IOException {
        Treinador treinadorSelecionado = listaTreinadores.getSelectionModel().getSelectedItem();

        if (treinadorSelecionado != null) {
            for (Pokemon pokemon : treinadorSelecionado.getPokemons()) {
                pokemon.inicializarMovimentos();
            }
            App.setTreinadorSessao(treinadorSelecionado);
            GerenciadorDeTelas.mudarTela("TelaMenuPrincipal.fxml");
        } else {
            AlertUtils.mostrarAlerta("Nenhuma Seleção", "Por favor, selecione um treinador da lista.");
        }
    }

    @FXML
    void onVoltarClick() throws IOException {
        GerenciadorDeTelas.mudarTela("TelaInicial.fxml");
    }
}