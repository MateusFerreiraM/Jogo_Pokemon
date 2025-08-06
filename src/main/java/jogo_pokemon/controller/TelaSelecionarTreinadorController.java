package jogo_pokemon.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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
                    if (empty || item == null || item.getNome() == null) {
                        setText(null);
                    } else {
                        setText(item.getNome() + " (Região: " + item.getRegiao() + ")");
                    }
                }
            });
            
        } catch (IOException e) {
            AlertUtils.mostrarAlerta("Erro Crítico", "Não foi possível carregar a lista de treinadores: " + e.getMessage()); // 2. Usar a nova classe
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
            AlertUtils.mostrarAlerta("Nenhuma Seleção", "Por favor, selecione um treinador da lista."); // 2. Usar a nova classe
        }
    }

    @FXML
    void onVoltarClick() throws IOException {
        GerenciadorDeTelas.mudarTela("TelaInicial.fxml");
    }

    // 3. O método privado 'mostrarAlerta' foi removido daqui
}