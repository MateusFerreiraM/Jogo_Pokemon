package jogo_pokemon.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import jogo_pokemon.App;
import jogo_pokemon.GerenciadorDados;
import jogo_pokemon.GerenciadorDeTelas;
import jogo_pokemon.Treinador;
import jogo_pokemon.utils.AlertUtils;
import jogo_pokemon.Pokemon;

import java.io.IOException;
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
                @Override
                protected void updateItem(Treinador item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        // Crie os Labels para o nome e a região
                        Label nomeLabel = new Label(item.getNome());
                        Label regiaoLabel = new Label("Região: " + item.getRegiao());

                        // Estilize os Labels para combinar com o tema
                        nomeLabel.setFont(Font.font("Pokemon Classic", FontWeight.BOLD, 14));
                        nomeLabel.setTextFill(Color.web("#0075BE"));

                        regiaoLabel.setFont(Font.font("Pokemon Classic", 12));
                        regiaoLabel.setTextFill(Color.web("#555555"));

                        // Crie um VBox para organizar os Labels verticalmente
                        VBox cellBox = new VBox(nomeLabel, regiaoLabel);
                        
                        // Defina o VBox como o gráfico da célula
                        setGraphic(cellBox);
                        setText(null); // Limpe o texto da célula, pois agora estamos usando o gráfico
                    }
                }
            });
        } catch (IOException e) {
            AlertUtils.mostrarAlerta("Erro Crítico", "Não foi possível carregar a lista de treinadores: " + e.getMessage());
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