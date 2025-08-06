package jogo_pokemon.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import jogo_pokemon.*;

public class TelaConfirmarBatalhaController {

    @FXML
    private Label labelDesafio;
    @FXML
    private ImageView imgOponente; // Adicionado para a imagem do Pokémon

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
                
                // Garante que o Pokémon do oponente também tenha os seus movimentos inicializados
                if (oponente.getPokemonAtual() != null) {
                    oponente.getPokemonAtual().inicializarMovimentos();
                }
                
                // Mensagem de desafio mais amigável e direta
                String mensagem = String.format(
                    "Você está prestes a entrar no \nGinásio %s!\nO líder %s lhe aguarda, com seu poderoso %s.\nTem a certeza que quer enfrentá-lo?",
                    oponente.getRegiao(), oponente.getNome(), oponente.getPokemonAtual().getNome()
                );
                labelDesafio.setText(mensagem);

                // Carrega a imagem do Pokémon do oponente
                carregarImagem(imgOponente, oponente.getPokemonAtual().getImagePath());

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
        GerenciadorDeTelas.mudarTela("TelaEscolherGinasio.fxml");
    }

    // Método auxiliar para carregar imagens de forma segura
    private void carregarImagem(ImageView imageView, String nomeImagem) {
        if (nomeImagem != null && !nomeImagem.isEmpty()) {
            try {
                String caminhoCompleto = "/jogo_pokemon/images/" + nomeImagem;
                InputStream stream = getClass().getResourceAsStream(caminhoCompleto);
                if (stream != null) {
                    imageView.setImage(new Image(stream));
                } else {
                    System.err.println("Imagem não encontrada no caminho: " + caminhoCompleto);
                    imageView.setImage(null);
                }
            } catch (Exception e) {
                System.err.println("Ocorreu um erro ao carregar a imagem: " + nomeImagem);
                e.printStackTrace();
                imageView.setImage(null);
            }
        } else {
            imageView.setImage(null);
        }
    }
}
