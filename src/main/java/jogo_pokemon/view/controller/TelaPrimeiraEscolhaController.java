package jogo_pokemon.view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import jogo_pokemon.App;
import jogo_pokemon.data.GerenciadorDados;
import jogo_pokemon.model.Pokemon;
import jogo_pokemon.model.Treinador;
import jogo_pokemon.utils.AlertUtils;
import jogo_pokemon.view.GerenciadorDeTelas;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public class TelaPrimeiraEscolhaController {

    @FXML private Label labelBoasVindas;
    @FXML private ImageView imgBulbasaur;
    @FXML private ImageView imgCharmander;
    @FXML private ImageView imgSquirtle;
    @FXML private ImageView imgPikachu;

    private Treinador treinador;
    private GerenciadorDados gerenciador = new GerenciadorDados();

    @FXML
    public void initialize() {
        this.treinador = App.getTreinadorSessao();
        if (this.treinador != null) {
            String nomeTreinador = this.treinador.getNome();
            labelBoasVindas.setText("Olá, " + nomeTreinador + "! \nSeja bem-vindo(a) ao centro de escolha Pokémon!\nEscolha um dos Pokémons disponíveis em nosso laboratório para iniciar sua jornada.");
        } else {
            labelBoasVindas.setText("Erro: Nenhum treinador encontrado na sessão.");
        }

        carregarImagem(imgBulbasaur, "bulbasaur.png");
        carregarImagem(imgCharmander, "charmander.png");
        carregarImagem(imgSquirtle, "squirtle.png");
        carregarImagem(imgPikachu, "pikachu.png");
    }

    @FXML
    void onBulbasaurClick() throws IOException {
        escolherPokemon(1);
    }

    @FXML
    void onCharmanderClick() throws IOException {
        escolherPokemon(4);
    }

    @FXML
    void onSquirtleClick() throws IOException {
        escolherPokemon(7);
    }

    @FXML
    void onPikachuClick() throws IOException {
        escolherPokemon(25);
    }

    private void escolherPokemon(int pokemonId) throws IOException {
        if (treinador == null) {
            AlertUtils.mostrarAlerta("Erro Crítico", "Não foi possível encontrar o treinador da sessão.");
            return;
        }

        List<Pokemon> pokemonsDisponiveis = gerenciador.carregarPokemonsDisponiveis();
        Optional<Pokemon> pokemonEscolhidoOptional = pokemonsDisponiveis.stream()
                .filter(p -> p.getId() == pokemonId)
                .findFirst();

        if (pokemonEscolhidoOptional.isPresent()) {
            Pokemon pokemonEscolhido = pokemonEscolhidoOptional.get();
            pokemonEscolhido.inicializarMovimentos();
            treinador.adicionarPokemon(pokemonEscolhido);

            List<Treinador> todosOsTreinadores = gerenciador.carregarTreinadores();
            todosOsTreinadores.add(treinador);
            gerenciador.salvarTreinadores(todosOsTreinadores);

            AlertUtils.mostrarAlerta("Parabéns!", "Você escolheu o " + pokemonEscolhido.getNome() + " para ser seu primeiro Pokémon! Sua jornada como treinador(a) acaba de começar. Prepare-se para grandes aventuras!");
            GerenciadorDeTelas.mudarTela("TelaMenuPrincipal.fxml");
        } else {
            AlertUtils.mostrarAlerta("Erro", "O Pokémon com o ID " + pokemonId + " não foi encontrado.");
        }
    }

    @FXML
    void onVoltarClick() throws IOException {
        GerenciadorDeTelas.mudarTela("TelaInicial.fxml");
    }

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