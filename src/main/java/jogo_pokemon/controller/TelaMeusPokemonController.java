package jogo_pokemon.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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

    @FXML
    public void initialize() {
        this.jogador = App.getTreinadorSessao();
        btnDefinirAtual.setDisable(true); // Começa desativado

        if (jogador != null) {
            atualizarTela();

            // Adiciona um "ouvinte" para ativar o botão quando um item for selecionado
            listaMeusPokemon.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                btnDefinirAtual.setDisable(newSelection == null);
            });
        }
    }

    @FXML
    void onDefinirAtualClick() {
        Pokemon pokemonSelecionado = listaMeusPokemon.getSelectionModel().getSelectedItem();
        if (pokemonSelecionado != null) {
            // 1. Define o novo Pokémon atual na memória
            jogador.setPokemonAtual(pokemonSelecionado);

            // 2. SALVA A ALTERAÇÃO NO FICHEIRO
            try {
                GerenciadorDados gerenciador = new GerenciadorDados();
                List<Treinador> todosOsTreinadores = gerenciador.carregarTreinadores();
                for (int i = 0; i < todosOsTreinadores.size(); i++) {
                    if (todosOsTreinadores.get(i).getId() == jogador.getId()) {
                        todosOsTreinadores.set(i, jogador); // Substitui o objeto antigo pelo atualizado
                        break;
                    }
                }
                gerenciador.salvarTreinadores(todosOsTreinadores);
                AlertUtils.mostrarAlerta("Sucesso", pokemonSelecionado.getNome() + " é agora o seu Pokémon principal!");

            } catch (IOException e) {
                AlertUtils.mostrarAlerta("Erro ao Salvar", "Não foi possível guardar a alteração: " + e.getMessage());
                e.printStackTrace();
            }

            // 3. Atualiza a tela para refletir a mudança
            atualizarTela();
        }
    }

    private void atualizarTela() {
        // Atualiza a label do Pokémon atual
        if (jogador.getPokemonAtual() != null) {
            labelPokemonAtual.setText("Atual: " + jogador.getPokemonAtual().getNome());
        } else {
            labelPokemonAtual.setText("Atual: [Nenhum]");
        }

        // Atualiza a lista de Pokémon
        ObservableList<Pokemon> observableList = FXCollections.observableArrayList(jogador.getPokemons());
        listaMeusPokemon.setItems(observableList);

        // Formata a exibição, destacando o Pokémon atual
        listaMeusPokemon.setCellFactory(param -> new ListCell<Pokemon>() {
            @Override
            protected void updateItem(Pokemon item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    String prefixo = "";
                    if (item.equals(jogador.getPokemonAtual())) {
                        prefixo = "★ ATUAL | ";
                    }
                    String tiposFormatados = item.getTipos().stream()
                            .map(Enum::toString)
                            .collect(Collectors.joining(" / "));
                    setText(prefixo + item.getNome() + " (HP: " + item.getHpAtual() + "/" + item.getHp() + " | Tipos: " + tiposFormatados + ")");
                }
            }
        });
    }

    @FXML
    void onVoltarClick() throws IOException {
        GerenciadorDeTelas.mudarTela("TelaMenuPrincipal.fxml");
    }
}