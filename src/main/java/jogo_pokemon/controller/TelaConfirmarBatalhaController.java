package jogo_pokemon.controller;

import java.io.IOException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import jogo_pokemon.*;

public class TelaConfirmarBatalhaController {

    @FXML
    private Label labelDesafio;

    private LiderGin oponente;
    private GerenciadorDados gerenciador = new GerenciadorDados();

    @FXML
    public void initialize() {
        // Busca o líder que foi guardado na sessão na tela anterior
        this.oponente = App.getLiderSelecionado();
        if (this.oponente != null) {
            try {
                // Carrega os dados do Pokémon do líder
                List<Pokemon> pokemonsDisponiveis = gerenciador.carregarPokemonsDisponiveis();
                oponente.carregarPokemon(pokemonsDisponiveis);
                
                // **A CORREÇÃO ESTÁ AQUI**
                // Garante que o Pokémon do oponente também tenha os seus movimentos inicializados
                if (oponente.getPokemonAtual() != null) {
                    oponente.getPokemonAtual().inicializarMovimentos();
                }
                
                labelDesafio.setText("Este é o ginásio de " + oponente.getRegiao() + "!\nO líder é " + oponente.getNome() + ", com seu astucioso " + oponente.getPokemonAtual().getNome() + ".\n\nTem a certeza que quer enfrentá-lo?");

            } catch (IOException e) {
                labelDesafio.setText("Erro ao carregar os dados do oponente.");
                e.printStackTrace();
            }
        }
    }

    @FXML
    void onConfirmarClick() throws IOException {
        // Cria a batalha
        Batalha batalha = new Batalha(App.getTreinadorSessao().getPokemonAtual(), oponente.getPokemonAtual());
        
        // Guarda a batalha na sessão da aplicação
        App.setBatalhaAtual(batalha);
        
        // Navega para a tela de batalha
        GerenciadorDeTelas.mudarTela("TelaBatalha.fxml");
    }

    @FXML
    void onVoltarClick() throws IOException {
        // Limpa o líder selecionado e volta para a tela de escolha
        App.setLiderSelecionado(null);
        GerenciadorDeTelas.mudarTela("TelaSelecionarTreinador.fxml");
    }
}