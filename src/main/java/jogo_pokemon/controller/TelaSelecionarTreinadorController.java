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
import jogo_pokemon.Pokemon;
import jogo_pokemon.Treinador;

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
                    if (empty || item == null || item.getNome() == null) {
                        setText(null);
                    } else {
                        setText(item.getNome() + " (Região: " + item.getRegiao() + ")");
                    }
                }
            });
            
        } catch (IOException e) {
            mostrarAlerta("Erro Crítico", "Não foi possível carregar a lista de treinadores: " + e.getMessage());
        }
    }

    @FXML
    void onConfirmarClick() throws IOException {
        Treinador treinadorSelecionado = listaTreinadores.getSelectionModel().getSelectedItem();

        if (treinadorSelecionado != null) {
            
            // **A CORREÇÃO FINAL ESTÁ AQUI**
            // Para cada Pokémon do treinador carregado, garantimos que os seus movimentos são inicializados.
            for (Pokemon pokemon : treinadorSelecionado.getPokemons()) {
                pokemon.inicializarMovimentos();
            }

            App.setTreinadorSessao(treinadorSelecionado);
            GerenciadorDeTelas.mudarTela("TelaMenuPrincipal.fxml");
        } else {
            mostrarAlerta("Nenhuma Seleção", "Por favor, selecione um treinador da lista.");
        }
    }

    @FXML
    void onVoltarClick() throws IOException {
        GerenciadorDeTelas.mudarTela("TelaInicial.fxml");
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}