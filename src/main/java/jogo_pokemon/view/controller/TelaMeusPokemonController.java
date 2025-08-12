package jogo_pokemon.view.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import jogo_pokemon.model.Treinador;
import jogo_pokemon.utils.AlertUtils;
import jogo_pokemon.view.GerenciadorDeTelas;

/**
 * Controlador para a tela "Meus Pokémon".
 * Permite ao jogador ver a sua equipa e definir qual Pokémon será o principal para as batalhas.
 */
public class TelaMeusPokemonController {

    @FXML private ListView<Pokemon> listaMeusPokemon;
    @FXML private Label labelPokemonAtual;
    @FXML private Button btnDefinirAtual;

    private Treinador jogador;
    private GerenciadorDados gerenciador = new GerenciadorDados();

    /**
     * Inicializa o controlador. Carrega o treinador da sessão, atualiza a tela
     * e adiciona um listener para habilitar o botão "Definir como Atual" apenas
     * quando um Pokémon for selecionado na lista.
     */
    @FXML
    public void initialize() {
        this.jogador = App.getTreinadorSessao();
        btnDefinirAtual.setDisable(true); 

        if (jogador != null) {
            atualizarTela();
            listaMeusPokemon.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                btnDefinirAtual.setDisable(newSelection == null);
            });
        }
    }

    /**
     * Handler do botão "Definir como Atual".
     * Define o Pokémon selecionado como o ativo do jogador, guarda a alteração
     * no ficheiro de dados e atualiza a tela.
     */
    @FXML
    void onDefinirAtualClick() {
        Pokemon pokemonSelecionado = listaMeusPokemon.getSelectionModel().getSelectedItem();
        if (pokemonSelecionado != null) {
            jogador.setPokemonAtual(pokemonSelecionado);
            
            try {
                List<Treinador> todosOsTreinadores = gerenciador.carregarTreinadores();
                for (int i = 0; i < todosOsTreinadores.size(); i++) {
                    if (todosOsTreinadores.get(i).getId() == jogador.getId()) {
                        todosOsTreinadores.set(i, jogador);
                        break;
                    }
                }
                gerenciador.salvarTreinadores(todosOsTreinadores);
                AlertUtils.mostrarAlerta("Sucesso", pokemonSelecionado.getNome() + " é agora o seu Pokémon principal!");
            } catch (IOException e) {
                AlertUtils.mostrarAlerta("Erro ao Salvar", "Não foi possível guardar a alteração: " + e.getMessage());
                e.printStackTrace();
            }
            
            atualizarTela();
        }
    }

    /**
     * Atualiza todos os elementos visuais da tela.
     * Recarrega a lista de Pokémon com uma formatação personalizada que destaca
     * o Pokémon atualmente ativo com uma estrela e um estilo visual diferente.
     */
    private void atualizarTela() {
        if (jogador.getPokemonAtual() != null) {
            labelPokemonAtual.setText("Atual: " + jogador.getPokemonAtual().getNome());
        } else {
            labelPokemonAtual.setText("Atual: [Nenhum]");
        }

        ObservableList<Pokemon> observableList = FXCollections.observableArrayList(jogador.getPokemons());
        listaMeusPokemon.setItems(observableList);

        listaMeusPokemon.setCellFactory(param -> new ListCell<Pokemon>() {
            private final ImageView imageView = new ImageView();
            private final Text nomeText = new Text();
            private final Text infoText = new Text();
            private final VBox textVBox = new VBox(nomeText, infoText);
            private final HBox cellHBox = new HBox(10, imageView, textVBox);
            {
                cellHBox.setAlignment(Pos.CENTER_LEFT);
            }

            @Override
            protected void updateItem(Pokemon pokemon, boolean empty) {
                super.updateItem(pokemon, empty);
                if (empty || pokemon == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    try (InputStream stream = App.class.getResourceAsStream("images/" + pokemon.getImagePath())) {
                        if (stream != null) {
                            imageView.setImage(new Image(stream));
                            imageView.setFitHeight(50);
                            imageView.setFitWidth(50);
                        }
                    } catch (Exception e) { imageView.setImage(null); }

                    // Lógica para destacar o Pokémon atual
                    nomeText.getStyleClass().remove("pokemon-atual");
                    if (pokemon.equals(jogador.getPokemonAtual())) {
                        nomeText.setText("\u2605 ATUAL | " + pokemon.getNome()); // Adiciona o ícone de estrela
                        nomeText.getStyleClass().add("pokemon-atual"); // Aplica o estilo CSS
                    } else {
                        nomeText.setText(pokemon.getNome());
                    }
                    nomeText.getStyleClass().add("list-item-title");

                    String tiposFormatados = pokemon.getTipos().stream()
                            .map(Enum::toString)
                            .collect(Collectors.joining(" / "));
                    infoText.setText("HP: " + pokemon.getHpAtual() + "/" + pokemon.getHp() + " | Tipos: " + tiposFormatados);
                    infoText.getStyleClass().add("list-item-subtitle");

                    setGraphic(cellHBox);
                }
            }
        });
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