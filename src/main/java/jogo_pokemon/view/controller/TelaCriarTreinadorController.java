package jogo_pokemon.view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import jogo_pokemon.App;
import jogo_pokemon.data.GerenciadorDados;
import jogo_pokemon.model.Treinador;
import jogo_pokemon.utils.AlertUtils;
import jogo_pokemon.view.GerenciadorDeTelas;
import java.io.IOException;
import java.util.List;

/**
 * Controlador para a tela de criação de um novo treinador.
 * Responsável por validar os dados de entrada e criar um novo perfil de jogador.
 */
public class TelaCriarTreinadorController {

    @FXML private TextField campoNome;
    @FXML private TextField campoRegiao;
    
    private GerenciadorDados gerenciador = new GerenciadorDados();

    /**
     * Handler do botão "Confirmar".
     * Valida os campos de nome e região. Se forem válidos, verifica se o nome do treinador
     * já existe. Se não existir, cria um novo treinador, guarda-o na sessão e avança
     * para a tela de escolha do primeiro Pokémon.
     */
    @FXML
    void onConfirmarClick() {
        String nome = campoNome.getText().trim();
        String regiao = campoRegiao.getText().trim();

        if (nome.isEmpty() || regiao.isEmpty()) {
            AlertUtils.mostrarAlerta("Erro de Validação", "Os campos de nome e região não podem estar vazios.");
            return;
        }

        try {
            List<Treinador> todosOsTreinadores = gerenciador.carregarTreinadores();
            boolean treinadorExiste = todosOsTreinadores.stream()
                    .anyMatch(t -> t.getNome().equalsIgnoreCase(nome));

            if (treinadorExiste) {
                AlertUtils.mostrarAlerta("Erro", "Já existe um treinador com este nome. Por favor, escolha outro.");
            } else {
                int novoId = todosOsTreinadores.stream().mapToInt(Treinador::getId).max().orElse(0) + 1;
                Treinador novoTreinador = new Treinador(nome, regiao, novoId);
                App.setTreinadorSessao(novoTreinador);
                GerenciadorDeTelas.irParaTelaPrimeiraEscolha();
            }
        } catch (IOException e) {
            AlertUtils.mostrarAlerta("Erro Crítico", "Não foi possível aceder aos dados dos treinadores: " + e.getMessage());
        }
    }

    /**
     * Handler do botão "Voltar".
     * Retorna o utilizador para a tela inicial.
     */
    @FXML void onVoltarClick() {
        GerenciadorDeTelas.irParaTelaInicial();
    }
}