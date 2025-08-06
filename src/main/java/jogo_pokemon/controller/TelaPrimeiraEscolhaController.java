package jogo_pokemon.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import jogo_pokemon.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class TelaPrimeiraEscolhaController {

    @FXML
    private Label labelBoasVindas;

    private GerenciadorDados gerenciador = new GerenciadorDados();
    private Treinador novoTreinador;

    @FXML
    public void initialize() {
        this.novoTreinador = App.getTreinadorSessao();
        if (this.novoTreinador != null) {
            labelBoasVindas.setText("Olá, " + this.novoTreinador.getNome() + "! Escolha o seu primeiro Pokémon:");
        } else {
            labelBoasVindas.setText("Erro: Nenhum treinador encontrado na sessão.");
        }
    }

    // A CORREÇÃO ESTÁ NOS MÉTODOS ABAIXO
    @FXML
    void onCharmanderClick() {
        try {
            escolherPokemon(4);
        } catch (IOException e) {
            tratarIOException(e);
        }
    }

    @FXML
    void onSquirtleClick() {
        try {
            escolherPokemon(7);
        } catch (IOException e) {
            tratarIOException(e);
        }
    }

    @FXML
    void onBulbasaurClick() {
        try {
            escolherPokemon(1);
        } catch (IOException e) {
            tratarIOException(e);
        }
    }

    @FXML
    void onPikachuClick() {
        try {
            escolherPokemon(25);
        } catch (IOException e) {
            tratarIOException(e);
        }
    }

    private void escolherPokemon(int pokemonId) throws IOException { // Este método continua a poder lançar o erro
        if (novoTreinador == null) {
            mostrarAlerta("Erro Crítico", "Não foi possível encontrar o treinador da sessão para adicionar o Pokémon.");
            return;
        }

        List<Pokemon> pokemonsDisponiveis = gerenciador.carregarPokemonsDisponiveis();
        Optional<Pokemon> pokemonEscolhido = pokemonsDisponiveis.stream()
                .filter(p -> p.getId() == pokemonId)
                .findFirst();

        if (pokemonEscolhido.isPresent()) {
            novoTreinador.adicionarPokemon(pokemonEscolhido.get());
            
            List<Treinador> todosOsTreinadores = gerenciador.carregarTreinadores();
            todosOsTreinadores.add(novoTreinador);
            gerenciador.salvarTreinadores(todosOsTreinadores);

            mostrarAlerta("Sucesso!", novoTreinador.getNome() + " foi criado e o " + pokemonEscolhido.get().getNome() + " foi adicionado à sua equipe!");
            
            GerenciadorDeTelas.mudarTela("TelaMenuPrincipal.fxml");

        } else {
             mostrarAlerta("Erro", "O Pokémon com o ID " + pokemonId + " não foi encontrado.");
        }
    }

    // Método auxiliar para tratar o erro e mostrar um alerta
    private void tratarIOException(IOException e) {
        mostrarAlerta("Erro de Ficheiro", "Ocorreu um erro ao ler ou salvar os dados: " + e.getMessage());
        e.printStackTrace(); // Imprime o erro detalhado no terminal para debugging
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}