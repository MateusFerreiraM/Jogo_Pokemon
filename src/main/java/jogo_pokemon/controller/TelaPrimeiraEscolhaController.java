package jogo_pokemon.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import jogo_pokemon.*;
import jogo_pokemon.utils.AlertUtils; // 1. Importar a nova classe

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

    @FXML void onCharmanderClick() { try { escolherPokemon(4); } catch (IOException e) { tratarIOException(e); } }
    @FXML void onSquirtleClick() { try { escolherPokemon(7); } catch (IOException e) { tratarIOException(e); } }
    @FXML void onBulbasaurClick() { try { escolherPokemon(1); } catch (IOException e) { tratarIOException(e); } }
    @FXML void onPikachuClick() { try { escolherPokemon(25); } catch (IOException e) { tratarIOException(e); } }

    private void escolherPokemon(int pokemonId) throws IOException {
        if (novoTreinador == null) {
            AlertUtils.mostrarAlerta("Erro Crítico", "Não foi possível encontrar o treinador da sessão para adicionar o Pokémon."); // 2. Usar a nova classe
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

            AlertUtils.mostrarAlerta("Sucesso!", novoTreinador.getNome() + " foi criado e o " + pokemonEscolhido.get().getNome() + " foi adicionado à sua equipe!"); // 2. Usar a nova classe
            
            GerenciadorDeTelas.mudarTela("TelaMenuPrincipal.fxml");

        } else {
             AlertUtils.mostrarAlerta("Erro", "O Pokémon com o ID " + pokemonId + " não foi encontrado."); // 2. Usar a nova classe
        }
    }

    private void tratarIOException(IOException e) {
        AlertUtils.mostrarAlerta("Erro de Ficheiro", "Ocorreu um erro ao ler ou salvar os dados: " + e.getMessage()); // 2. Usar a nova classe
        e.printStackTrace();
    }

    // 3. O método privado 'mostrarAlerta' foi removido daqui
}