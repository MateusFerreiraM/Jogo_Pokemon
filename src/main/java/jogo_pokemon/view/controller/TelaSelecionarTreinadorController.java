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

/**
 * Controlador para a tela de seleção de treinador.
 * Carrega e exibe os perfis de treinador existentes para que o jogador possa carregar o seu progresso.
 */
public class TelaSelecionarTreinadorController {

    @FXML
    private ListView<Treinador> listaTreinadores;

    private GerenciadorDados gerenciador = new GerenciadorDados();

   /**
    * Inicializa o controlador. Este método carrega a lista de treinadores guardados
    * e configura uma fábrica de células (cellFactory) para exibir cada treinador na ListView
    * com uma formatação personalizada, mostrando o nome, a região e a quantidade de Pokémon.
    */
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

    /**
     * Handler do botão "Confirmar".
     * Carrega o perfil do treinador selecionado para a sessão do jogo, inicializa os seus Pokémon
     * e navega para o menu principal. Exibe um alerta se nenhum treinador for selecionado.
     */
    @FXML
    void onConfirmarClick() {
        Treinador treinadorSelecionado = listaTreinadores.getSelectionModel().getSelectedItem();

        if (treinadorSelecionado != null) {
            for (Pokemon pokemon : treinadorSelecionado.getPokemons()) {
                pokemon.inicializarMovimentos();
            }
            App.setTreinadorSessao(treinadorSelecionado);
            GerenciadorDeTelas.irParaMenuPrincipal();
        } else {
            AlertUtils.mostrarAlerta("Nenhuma Seleção", "Por favor, selecione um treinador da lista.");
        }
    }

    /**
     * Handler do botão "Voltar".
     * Navega o utilizador de volta para a tela inicial.
     */
    @FXML
    void onVoltarClick() {
        GerenciadorDeTelas.irParaTelaInicial();
    }
}