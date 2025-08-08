package jogo_pokemon.view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import jogo_pokemon.App;
import jogo_pokemon.data.GerenciadorDados;
import jogo_pokemon.model.Treinador;
import jogo_pokemon.utils.AlertUtils; // 1. Importar a nova classe
import jogo_pokemon.view.GerenciadorDeTelas;

import java.io.IOException;
import java.util.List;

public class TelaCriarTreinadorController {

    @FXML private TextField campoNome;
    @FXML private TextField campoRegiao;
    private GerenciadorDados gerenciador = new GerenciadorDados();

    @FXML
    void onConfirmarClick() throws IOException {
        String nome = campoNome.getText().trim();
        String regiao = campoRegiao.getText().trim();

        if (nome.isEmpty() || regiao.isEmpty()) {
            AlertUtils.mostrarAlerta("Erro de Validação", "Os campos de nome e região não podem estar vazios."); // 2. Usar a nova classe
            return;
        }

        try {
            List<Treinador> todosOsTreinadores = gerenciador.carregarTreinadores();
            boolean treinadorExiste = todosOsTreinadores.stream()
                    .anyMatch(t -> t.getNome().equalsIgnoreCase(nome));

            if (treinadorExiste) {
                AlertUtils.mostrarAlerta("Erro", "Já existe um treinador com este nome. Por favor, escolha outro."); // 2. Usar a nova classe
            } else {
                int novoId = todosOsTreinadores.stream().mapToInt(Treinador::getId).max().orElse(0) + 1;
                Treinador novoTreinador = new Treinador(nome, regiao, novoId);
                App.setTreinadorSessao(novoTreinador);
                GerenciadorDeTelas.mudarTela("TelaPrimeiraEscolha.fxml");
            }
        } catch (IOException e) {
            AlertUtils.mostrarAlerta("Erro Crítico", "Não foi possível aceder aos dados dos treinadores: " + e.getMessage()); // 2. Usar a nova classe
        }
    }

    @FXML void onVoltarClick() throws IOException {
        GerenciadorDeTelas.mudarTela("TelaInicial.fxml");
    }

    // 3. O método privado 'mostrarAlerta' foi removido daqui
}