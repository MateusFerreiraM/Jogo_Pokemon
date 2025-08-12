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

/**
 * Controlador para a tela de escolha do primeiro Pokémon.
 * Esta tela é exibida após a criação de um novo treinador.
 */
public class TelaPrimeiraEscolhaController {

    @FXML private Label labelBoasVindas;
    @FXML private ImageView imgBulbasaur;
    @FXML private ImageView imgCharmander;
    @FXML private ImageView imgSquirtle;
    @FXML private ImageView imgPikachu;

    private Treinador treinador;
    private GerenciadorDados gerenciador = new GerenciadorDados();

    /**
     * Inicializa o controlador. Obtém o novo treinador da sessão, exibe uma mensagem
     * de boas-vindas e carrega as imagens dos Pokémon iniciais.
     */
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

    /**
     * Handler para o clique na imagem do Bulbasaur.
     * @throws IOException se ocorrer um erro ao guardar os dados.
     */
    @FXML
    void onBulbasaurClick() throws IOException {
        escolherPokemon(1);
    }

    /**
     * Handler para o clique na imagem do Charmander.
     * @throws IOException se ocorrer um erro ao guardar os dados.
     */
    @FXML
    void onCharmanderClick() throws IOException {
        escolherPokemon(4);
    }

    /**
     * Handler para o clique na imagem do Squirtle.
     * @throws IOException se ocorrer um erro ao guardar os dados.
     */
    @FXML
    void onSquirtleClick() throws IOException {
        escolherPokemon(7);
    }

    /**
     * Handler para o clique na imagem do Pikachu.
     * @throws IOException se ocorrer um erro ao guardar os dados.
     */
    @FXML
    void onPikachuClick() throws IOException {
        escolherPokemon(25);
    }
    
    /**
     * Lógica central para atribuir o Pokémon inicial ao novo treinador.
     * Encontra o Pokémon pelo ID, adiciona-o ao treinador, guarda o novo treinador na lista
     * de perfis, exibe um alerta de sucesso e navega para o menu principal.
     * @param pokemonId O ID do Pokémon que foi escolhido.
     * @throws IOException se ocorrer um erro ao carregar ou guardar os dados.
     */
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
            GerenciadorDeTelas.irParaMenuPrincipal();
        } else {
            AlertUtils.mostrarAlerta("Erro", "O Pokémon com o ID " + pokemonId + " não foi encontrado.");
        }
    }

    /**
     * Handler do botão "Voltar".
     * Retorna para a tela inicial, cancelando a criação do treinador.
     */
    @FXML
    void onVoltarClick() {
        GerenciadorDeTelas.irParaTelaInicial();
    }

    /**
     * Carrega uma imagem da pasta de recursos para um determinado ImageView.
     * @param imageView O componente onde a imagem será exibida.
     * @param nomeImagem O nome do ficheiro da imagem (ex: "bulbasaur.png").
     */
    private void carregarImagem(ImageView imageView, String nomeImagem) {
        if (nomeImagem != null && !nomeImagem.isEmpty()) {
            try {
                String caminhoCompleto = "/jogo_pokemon/images/" + nomeImagem;
                InputStream stream = getClass().getResourceAsStream(caminhoCompleto);
                if (stream != null) {
                    imageView.setImage(new Image(stream));
                } else {
                    System.err.println("Imagem não encontrada no caminho: " + caminhoCompleto);
                }
            } catch (Exception e) {
                System.err.println("Ocorreu um erro ao carregar a imagem: " + nomeImagem);
                e.printStackTrace();
            }
        }
    }
}