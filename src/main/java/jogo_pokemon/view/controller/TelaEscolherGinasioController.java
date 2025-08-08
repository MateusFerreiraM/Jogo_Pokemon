package jogo_pokemon.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import jogo_pokemon.App;
import jogo_pokemon.data.GerenciadorDados;
import jogo_pokemon.model.LiderGin;
import jogo_pokemon.utils.AlertUtils;
import jogo_pokemon.view.GerenciadorDeTelas;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class TelaEscolherGinasioController {

    @FXML
    private ListView<LiderGin> listaGinasios;

    private GerenciadorDados gerenciador = new GerenciadorDados();

    @FXML
    public void initialize() {
        try {
            List<LiderGin> lideres = gerenciador.carregarLideres();
            
            // DEBUG: Imprime o que foi carregado para o terminal
            System.out.println("Líderes carregados: " + lideres.size());
            for (LiderGin lider : lideres) {
                System.out.println("  - ID: " + lider.getId() + ", Nome: " + lider.getNome() + ", Regiao: " + lider.getRegiao());
            }

            ObservableList<LiderGin> observableList = FXCollections.observableArrayList(lideres);
            listaGinasios.setItems(observableList);
            
            listaGinasios.setCellFactory(param -> new ListCell<LiderGin>() {
                private final Text nomeGinasioText = new Text();
                private final Text nomeLiderText = new Text();
                private final VBox cellBox = new VBox(2, nomeGinasioText, nomeLiderText);
                {
                    nomeGinasioText.getStyleClass().add("list-item-title");
                    nomeLiderText.getStyleClass().add("list-item-subtitle");
                }

                @Override
                protected void updateItem(LiderGin lider, boolean empty) {
                    super.updateItem(lider, empty);
                    if (empty || lider == null) {
                        setGraphic(null);
                    } else {
                        // Código mais seguro que previne erros se um campo for nulo
                        String regiao = lider.getRegiao() != null ? lider.getRegiao() : "Região Desconhecida";
                        String nome = lider.getNome() != null ? lider.getNome() : "Líder Desconhecido";

                        nomeGinasioText.setText("Ginásio de " + regiao);
                        nomeLiderText.setText("Líder: " + nome);
                        
                        setGraphic(cellBox);
                    }
                }
            });
            
        } catch (IOException e) {
            AlertUtils.mostrarAlerta("Erro Crítico", "Não foi possível carregar a lista de ginásios: " + e.getMessage());
            e.printStackTrace();
            listaGinasios.setItems(FXCollections.observableArrayList(Collections.emptyList()));
        }
    }

    @FXML
    void onConfirmarClick() throws IOException {
        LiderGin liderSelecionado = listaGinasios.getSelectionModel().getSelectedItem();
        if (liderSelecionado != null) {
            App.setLiderSelecionado(liderSelecionado);
            GerenciadorDeTelas.mudarTela("TelaConfirmarBatalha.fxml");
        } else {
            AlertUtils.mostrarAlerta("Nenhuma Seleção", "Por favor, selecione um ginásio para desafiar.");
        }
    }

    @FXML
    void onVoltarClick() throws IOException {
        GerenciadorDeTelas.mudarTela("TelaMenuPrincipal.fxml");
    }
}