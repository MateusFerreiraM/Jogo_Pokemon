package jogo_pokemon.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import jogo_pokemon.App;
import jogo_pokemon.GerenciadorDados;
import jogo_pokemon.GerenciadorDeTelas;
import jogo_pokemon.LiderGin;

import java.io.IOException;
import java.util.List;

public class TelaEscolherGinasioController {

    @FXML
    private ListView<LiderGin> listaGinasios;

    private GerenciadorDados gerenciador = new GerenciadorDados();

    @FXML
    public void initialize() {
        try {
            // Carrega os LÍDERES de lideres.json
            List<LiderGin> lideres = gerenciador.carregarLideres();
            
            ObservableList<LiderGin> observableList = FXCollections.observableArrayList(lideres);
            listaGinasios.setItems(observableList);
            
            // MELHORIA VISUAL: Faz com que a lista mostre o nome do líder e a região
            listaGinasios.setCellFactory(param -> new ListCell<LiderGin>() {
                @Override
                protected void updateItem(LiderGin item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null || item.getNome() == null) {
                        setText(null);
                    } else {
                        setText("Ginásio " + item.getRegiao() + " (Líder: " + item.getNome() + ")");
                    }
                }
            });
            
        } catch (IOException e) {
            mostrarAlerta("Erro Crítico", "Não foi possível carregar a lista de ginásios: " + e.getMessage());
        }
    }

    @FXML
    void onConfirmarClick() throws IOException {
        LiderGin liderSelecionado = listaGinasios.getSelectionModel().getSelectedItem();

        if (liderSelecionado != null) {
            App.setLiderSelecionado(liderSelecionado);
            GerenciadorDeTelas.mudarTela("TelaConfirmarBatalha.fxml");
        } else {
            mostrarAlerta("Nenhuma Seleção", "Por favor, selecione um ginásio para desafiar.");
        }
    }

    @FXML
    void onVoltarClick() throws IOException {
        GerenciadorDeTelas.mudarTela("TelaMenuPrincipal.fxml");
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}