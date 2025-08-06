package jogo_pokemon.controller;

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
import jogo_pokemon.App;
import jogo_pokemon.GerenciadorDados;
import jogo_pokemon.GerenciadorDeTelas;
import jogo_pokemon.Pokemon;
import jogo_pokemon.Treinador;
import jogo_pokemon.utils.AlertUtils;

public class TelaMeusPokemonController {

    @FXML private ListView<Pokemon> listaMeusPokemon;
    @FXML private Label labelPokemonAtual;
    @FXML private Button btnDefinirAtual;

    private Treinador jogador;
    private GerenciadorDados gerenciador = new GerenciadorDados(); // Adicionado para salvar

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

    @FXML
    void onDefinirAtualClick() {
        Pokemon pokemonSelecionado = listaMeusPokemon.getSelectionModel().getSelectedItem();
        if (pokemonSelecionado != null) {
            jogador.setPokemonAtual(pokemonSelecionado);
            
            // Salva a alteração no ficheiro
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

    private void atualizarTela() {
        if (jogador.getPokemonAtual() != null) {
            labelPokemonAtual.setText("Atual: " + jogador.getPokemonAtual().getNome());
        } else {
            labelPokemonAtual.setText("Atual: [Nenhum]");
        }

        ObservableList<Pokemon> observableList = FXCollections.observableArrayList(jogador.getPokemons());
        listaMeusPokemon.setItems(observableList);

        // **A CORREÇÃO ESTÁ AQUI**
        listaMeusPokemon.setCellFactory(param -> new ListCell<Pokemon>() {
            // Estes elementos são agora criados para CADA CÉLULA, individualmente
            private final ImageView imageView = new ImageView();
            private final Label labelInfo = new Label();
            private final HBox hbox = new HBox(10, imageView, labelInfo);
            {
                hbox.setAlignment(Pos.CENTER_LEFT);
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
                            imageView.setFitHeight(40);
                            imageView.setFitWidth(40);
                        }
                    } catch (Exception e) {
                        imageView.setImage(null);
                    }

                    // Formata o texto
                    String prefixo = pokemon.equals(jogador.getPokemonAtual()) ? "★ ATUAL | " : "";
                    String tiposFormatados = pokemon.getTipos().stream()
                            .map(Enum::toString)
                            .collect(Collectors.joining(" / "));
                    labelInfo.setText(prefixo + pokemon.getNome() + "\n(HP: " + pokemon.getHpAtual() + "/" + pokemon.getHp() + " | Tipos: " + tiposFormatados + ")");
                    
                    setGraphic(hbox);
                }
            }
        });
    }

    @FXML
    void onVoltarClick() throws IOException {
        GerenciadorDeTelas.mudarTela("TelaMenuPrincipal.fxml");
    }
}