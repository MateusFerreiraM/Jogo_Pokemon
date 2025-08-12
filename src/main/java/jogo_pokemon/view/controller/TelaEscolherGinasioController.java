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

/**
 * Controlador para a tela de seleção de ginásio.
 * Carrega e exibe a lista de líderes disponíveis para desafio.
 */
public class TelaEscolherGinasioController {

    @FXML
    private ListView<LiderGin> listaGinasios;

    private GerenciadorDados gerenciador = new GerenciadorDados();

    /**
     * Inicializa o controlador, carregando a lista de líderes de ginásio a partir do
     * ficheiro de dados e configurando a exibição personalizada na ListView.
     */
    @FXML
    public void initialize() {
        try {
            List<LiderGin> lideres = gerenciador.carregarLideres();
            ObservableList<LiderGin> observableList = FXCollections.observableArrayList(lideres);
            listaGinasios.setItems(observableList);
            
            // Configura a fábrica de células para renderizar cada item da lista de forma personalizada.
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

    /**
     * Handler do botão "Confirmar".
     * Verifica se um líder foi selecionado, guarda a seleção no estado global da aplicação
     * e navega para a tela de confirmação de batalha.
     */
    @FXML
    void onConfirmarClick() {
        LiderGin liderSelecionado = listaGinasios.getSelectionModel().getSelectedItem();
        if (liderSelecionado != null) {
            App.setLiderSelecionado(liderSelecionado);
            GerenciadorDeTelas.irParaTelaConfirmarBatalha();
        } else {
            AlertUtils.mostrarAlerta("Nenhuma Seleção", "Por favor, selecione um ginásio para desafiar.");
        }
    }

    /**
     * Handler do botão "Voltar".
     * Navega o utilizador de volta para o menu principal.
     */
    @FXML
    void onVoltarClick() {
        GerenciadorDeTelas.irParaMenuPrincipal();
    }
}