package jogo_pokemon.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import jogo_pokemon.App;
import jogo_pokemon.GerenciadorDados;
import jogo_pokemon.GerenciadorDeTelas;
import jogo_pokemon.Treinador;
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
            mostrarAlerta("Erro de Validação", "Os campos de nome e região não podem estar vazios.");
            return;
        }

        try {
            List<Treinador> todosOsTreinadores = gerenciador.carregarTreinadores();
            boolean treinadorExiste = todosOsTreinadores.stream()
                    .anyMatch(t -> t.getNome().equalsIgnoreCase(nome));

            if (treinadorExiste) {
                mostrarAlerta("Erro", "Já existe um treinador com este nome. Por favor, escolha outro.");
            } else {
                // SUCESSO! Agora criamos o treinador e avançamos.
                int novoId = todosOsTreinadores.stream().mapToInt(Treinador::getId).max().orElse(0) + 1;
                Treinador novoTreinador = new Treinador(nome, regiao, novoId);

                // Guarda o novo treinador na sessão da aplicação
                App.setTreinadorSessao(novoTreinador);

                // Navega para a próxima tela
                GerenciadorDeTelas.mudarTela("TelaPrimeiraEscolha.fxml");
            }
        } catch (IOException e) {
            mostrarAlerta("Erro Crítico", "Não foi possível aceder aos dados dos treinadores: " + e.getMessage());
        }
    }

    @FXML void onVoltarClick() throws IOException {
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