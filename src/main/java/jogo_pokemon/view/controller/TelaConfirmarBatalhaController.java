package jogo_pokemon.view.controller;

import java.io.IOException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import jogo_pokemon.App;
import jogo_pokemon.model.Batalha;
import jogo_pokemon.data.GerenciadorDados;
import jogo_pokemon.model.LiderGin;
import jogo_pokemon.model.Pokemon;
import jogo_pokemon.utils.AlertUtils;
import jogo_pokemon.utils.GerenciadorDeMusica;
import jogo_pokemon.utils.ImageUtils;
import jogo_pokemon.view.GerenciadorDeTelas;

/**
 * Controlador para a tela que aparece antes de uma batalha de ginásio.
 * Apresenta o oponente e pede a confirmação do jogador.
 */
public class TelaConfirmarBatalhaController {

    @FXML private Label labelDesafio;
    @FXML private ImageView imgOponente;

    private LiderGin oponente;
    private GerenciadorDados gerenciador = new GerenciadorDados();

    /**
     * Método de inicialização. É chamado quando o FXML é carregado.
     * Carrega o líder selecionado a partir do estado global da aplicação.
     */
    @FXML
    public void initialize() {
        this.oponente = App.getLiderSelecionado();
        if (this.oponente != null) {
            carregarDadosOponente();
        } else {
            labelDesafio.setText("Erro: Nenhum líder de ginásio foi selecionado.");
        }
    }
    
    /**
     * Carrega os dados do Pokémon do oponente e atualiza a interface.
     * Se o ginásio for da região "Misterioso", exibe uma imagem e texto genéricos
     * para manter o suspense. Caso contrário, exibe as informações reais do Pokémon.
     */
    private void carregarDadosOponente() {
        try {
            List<Pokemon> pokemonsDisponiveis = gerenciador.carregarPokemonsDisponiveis();
            oponente.carregarPokemon(pokemonsDisponiveis);

            Pokemon pokemonOponente = oponente.getPokemonAtual();

            if (pokemonOponente != null) {
                pokemonOponente.inicializarMovimentos();
                
                if (oponente.getRegiao().equalsIgnoreCase("Misterioso")) {
                    String mensagem = String.format(
                        "Você está prestes a desafiar %s no Ginásio de %s!\nEle usará um Pokémon misterioso...",
                        oponente.getNome(), oponente.getRegiao()
                    );
                    labelDesafio.setText(mensagem);

                    ImageUtils.carregarPokemonImage(imgOponente, "interrogacao.png");
                    ImageUtils.aplicarSombra(imgOponente);
                } else {
                    String mensagem = String.format(
                        "Você está prestes a desafiar %s no Ginásio de %s!\nEle usará seu poderoso %s.",
                        oponente.getNome(), oponente.getRegiao(), pokemonOponente.getNome()
                    );
                    labelDesafio.setText(mensagem);

                    ImageUtils.carregarPokemonImage(imgOponente, pokemonOponente.getImagePath());
                    ImageUtils.aplicarSombra(imgOponente);
                }

            } else {
                labelDesafio.setText("Erro ao carregar o Pokémon do oponente " + oponente.getNome() + ".");
                AlertUtils.mostrarAlerta("Erro de Dados", "Não foi possível encontrar o Pokémon associado ao líder " + oponente.getNome() + ". Verifique o ficheiro lideres.json.");
            }
        } catch (IOException e) {
            labelDesafio.setText("Erro ao ler os ficheiros de dados.");
            e.printStackTrace();
        }
    }
    
    /**
     * Handler do botão "Lutar!". Inicia a música de batalha, cria uma nova instância de Batalha
     * e transita para a tela de batalha principal.
     */
    @FXML
    void onConfirmarClick() {
        GerenciadorDeMusica.tocarMusicaBatalha();
        Batalha batalha = new Batalha(App.getTreinadorSessao().getPokemonAtual(), oponente.getPokemonAtual());
        App.setBatalhaAtual(batalha);
        GerenciadorDeTelas.irParaTelaDeBatalha();
    }

    /**
     * Handler do botão "Voltar". Limpa o líder selecionado do estado global da aplicação
     * e retorna para a tela de escolha de ginásio.
     */
    @FXML
    void onVoltarClick() {
        App.setLiderSelecionado(null);
        GerenciadorDeTelas.irParaTelaEscolherGinasio();
    }
}