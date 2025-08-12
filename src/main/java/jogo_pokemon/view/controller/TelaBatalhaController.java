package jogo_pokemon.view.controller;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import jogo_pokemon.App;
import jogo_pokemon.data.GerenciadorDados;
import jogo_pokemon.model.Batalha;
import jogo_pokemon.model.Movimentos;
import jogo_pokemon.model.Pokemon;
import jogo_pokemon.model.Treinador;
import jogo_pokemon.utils.GerenciadorDeMusica;
import jogo_pokemon.utils.ImageUtils;
import jogo_pokemon.view.GerenciadorDeTelas;

/**
 * Controlador para a tela de batalha principal do jogo.
 * Gere todas as interações do utilizador, animações e o fluxo dos turnos.
 */
public class TelaBatalhaController {

    @FXML private Label labelNomeInimigo;
    @FXML private ProgressBar barHPInimigo;
    @FXML private ImageView imgInimigo;

    @FXML private Label labelNomeJogador;
    @FXML private ProgressBar barHPJogador;
    @FXML private ImageView imgJogador;

    @FXML private Button btnAtaqueFisico;
    @FXML private Button btnAtaqueEspecial;
    
    private Batalha batalha;
    private Random random = new Random();

    /**
     * Inicializa o controlador da tela de batalha.
     * Carrega a sessão de batalha atual ou redireciona para o menu se não houver batalha ativa.
     */
    @FXML
    public void initialize() {
        this.batalha = App.getBatalhaAtual();
        if (this.batalha == null) {
            System.err.println("ERRO: Tentativa de carregar a tela de batalha sem uma batalha em andamento.");
            GerenciadorDeTelas.irParaMenuPrincipal();
        } else {
            configurarTelaInicial();
        }
    }
    
    /**
     * Handler para o clique no botão de ataque físico.
     */
    @FXML
    void onAtaqueFisicoClick() {
        executarTurnoJogador(batalha.getPkmAmigo().getMovimentosList().get(0));
    }

    /**
     * Handler para o clique no botão de ataque especial.
     */
    @FXML
    void onAtaqueEspecialClick() {
        batalha.decrementarContEspecial();
        atualizarTextoBotaoEspecial();
        executarTurnoJogador(batalha.getPkmAmigo().getMovimentosList().get(1));
    }

    /**
     * Executa a sequência completa e assíncrona do turno do jogador.
     * A sequência inclui: animação de ataque, cálculo de dano, animação de dano e da barra de vida,
     * e, por fim, a transição para o turno do inimigo ou para o fim da batalha.
     * @param movimentoJogador O movimento selecionado pelo jogador.
     */
    private void executarTurnoJogador(Movimentos movimentoJogador) {
        setBotoesAtaque(true);
        animarAtaque(imgJogador, () -> {
            batalha.atacar(batalha.getPkmAmigo(), batalha.getPkmInimigo(), movimentoJogador);
            atualizarLabelVida(labelNomeInimigo, batalha.getPkmInimigo());
            animarDano(imgInimigo);
            animarBarraDeVida(barHPInimigo, batalha.getPkmInimigo(), () -> {
                if (!batalha.getEmExecucao()) {
                    fimDeBatalha();
                } else {
                    executarTurnoInimigo();
                }
            });
        });
    }

    /**
     * Executa a sequência completa e assíncrona do turno do oponente.
     */
    private void executarTurnoInimigo() {
        animarAtaque(imgInimigo, () -> {
            int escolhaInimigo = (batalha.getContEspecialLider() > 0 && random.nextBoolean()) ? 1 : 0;
            Movimentos movimentoInimigo = batalha.getPkmInimigo().getMovimentosList().get(escolhaInimigo);
            if (escolhaInimigo == 1) {
                batalha.decrementarContEspecialLider();
            }
            batalha.atacar(batalha.getPkmInimigo(), batalha.getPkmAmigo(), movimentoInimigo);
            atualizarLabelVida(labelNomeJogador, batalha.getPkmAmigo());
            animarDano(imgJogador);
            animarBarraDeVida(barHPJogador, batalha.getPkmAmigo(), () -> {
                if (!batalha.getEmExecucao()) {
                    fimDeBatalha();
                } else {
                    setBotoesAtaque(false);
                }
            });
        });
    }

    /**
     * Finaliza a batalha, toca o efeito sonoro apropriado, cura os Pokémon, guarda o progresso
     * e navega para a tela de resultado (vitória ou derrota).
     */
    private void fimDeBatalha() {
        try {
            if (batalha.getVitoria()) {
                GerenciadorDeMusica.tocarSfxVitoria();
            } else {
                GerenciadorDeMusica.tocarSfxDerrota();
            }

            Treinador jogador = App.getTreinadorSessao();
            for (Pokemon pokemon : jogador.getPokemons()) {
                pokemon.setHpAtual(pokemon.getHp());
            }

            GerenciadorDados gerenciador = new GerenciadorDados();
            List<Treinador> todosOsTreinadores = gerenciador.carregarTreinadores();
            for (int i = 0; i < todosOsTreinadores.size(); i++) {
                if (todosOsTreinadores.get(i).getId() == jogador.getId()) {
                    todosOsTreinadores.set(i, jogador);
                    break;
                }
            }
            gerenciador.salvarTreinadores(todosOsTreinadores);
            
            App.setBatalhaAtual(null); 
            
            if (batalha.getVitoria()) {
                GerenciadorDeTelas.irParaTelaDeVitoria();
            } else {
                GerenciadorDeTelas.irParaTelaDeDerrota();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Cria e executa uma animação de "salto" para o ImageView do Pokémon atacante.
     * @param atacanteView O ImageView do Pokémon que irá atacar.
     * @param aoConcluir A ação (Runnable) a ser executada quando a animação terminar.
     */
    private void animarAtaque(ImageView atacanteView, Runnable aoConcluir) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(150), atacanteView);
        tt.setByX(20);
        tt.setByY(-10);
        tt.setCycleCount(2);
        tt.setAutoReverse(true);

        tt.setOnFinished(event -> {
            if (aoConcluir != null) {
                aoConcluir.run();
            }
        });
        tt.play();
    }

    /**
     * Cria e executa uma animação de "tremor" para o ImageView do Pokémon que recebe dano.
     * @param defensorView O ImageView do Pokémon que irá defender.
     */
    private void animarDano(ImageView defensorView) {
        TranslateTransition tt1 = new TranslateTransition(Duration.millis(50), defensorView);
        tt1.setByX(5);
        TranslateTransition tt2 = new TranslateTransition(Duration.millis(50), defensorView);
        tt2.setByX(-10);
        TranslateTransition tt3 = new TranslateTransition(Duration.millis(50), defensorView);
        tt3.setByX(10);
        TranslateTransition tt4 = new TranslateTransition(Duration.millis(50), defensorView);
        tt4.setByX(-5);
        
        SequentialTransition st = new SequentialTransition(tt1, tt2, tt3, tt4);
        st.play();
    }
        
    /**
     * Configura o estado visual inicial da tela, exibindo os dados dos Pokémon.
     */
    private void configurarTelaInicial() {
        Pokemon jogador = batalha.getPkmAmigo();
        Pokemon inimigo = batalha.getPkmInimigo();
        atualizarLabelVida(labelNomeJogador, jogador);
        configurarBarraDeVidaInicial(barHPJogador, jogador);
        ImageUtils.carregarPokemonImage(imgJogador, jogador.getImagePath());
        ImageUtils.aplicarSombra(imgJogador);
        atualizarLabelVida(labelNomeInimigo, inimigo);
        configurarBarraDeVidaInicial(barHPInimigo, inimigo);
        ImageUtils.carregarPokemonImage(imgInimigo, inimigo.getImagePath());
        ImageUtils.aplicarSombra(imgInimigo);
        atualizarTextoBotaoEspecial();
        setBotoesAtaque(false);
    }
    
    /**
     * Atualiza o texto do botão de ataque especial com o contador de usos restantes.
     */
    private void atualizarTextoBotaoEspecial() {
        int contador = batalha.getContEspecial();
        btnAtaqueEspecial.setText("Ataque Especial (" + contador + "/2)");
    }

    /**
     * Define o valor inicial de uma barra de vida e a sua cor correspondente.
     * @param barra O ProgressBar a ser configurado.
     * @param pokemon O Pokémon associado a esta barra de vida.
     */
    private void configurarBarraDeVidaInicial(ProgressBar barra, Pokemon pokemon) {
        double porcentagem = (double) pokemon.getHpAtual() / pokemon.getHp();
        barra.setProgress(porcentagem);
        atualizarCorBarraVida(barra, porcentagem);
    }
    
    /**
     * Atualiza o texto de um Label para exibir o nome e o HP (atual/máximo) de um Pokémon.
     * @param label O Label a ser atualizado.
     * @param pokemon O Pokémon cujos dados serão exibidos.
     */
    private void atualizarLabelVida(Label label, Pokemon pokemon) {
        label.setText(pokemon.getNome() + " (" + pokemon.getHpAtual() + "/" + pokemon.getHp() + ")");
    }

    /**
     * Anima a transição de uma barra de vida de seu valor atual para um novo valor.
     * @param barra O ProgressBar a ser animado.
     * @param pokemon O Pokémon associado, para calcular a percentagem final de HP.
     * @param aoConcluir Ação a ser executada no final da animação.
     */
    private void animarBarraDeVida(ProgressBar barra, Pokemon pokemon, Runnable aoConcluir) {
        double porcentagemFinal = Math.max(0.0, (double) pokemon.getHpAtual() / pokemon.getHp());
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(0.8), new KeyValue(barra.progressProperty(), porcentagemFinal))
        );
        timeline.setOnFinished(event -> {
            atualizarCorBarraVida(barra, porcentagemFinal);
            if (aoConcluir != null) {
                aoConcluir.run();
            }
        });
        timeline.play();
    }

    /**
     * Atualiza a classe de estilo CSS de uma barra de vida com base na percentagem de HP.
     * @param barra O ProgressBar a ser estilizado.
     * @param porcentagem A percentagem de HP (0.0 a 1.0).
     */
    private void atualizarCorBarraVida(ProgressBar barra, double porcentagem) {
        barra.getStyleClass().removeAll("green-bar", "yellow-bar", "red-bar");
        if (porcentagem > 0.5) {
            barra.getStyleClass().add("green-bar");
        } else if (porcentagem > 0.25) {
            barra.getStyleClass().add("yellow-bar");
        } else {
            barra.getStyleClass().add("red-bar");
        }
    }

    /**
     * Ativa ou desativa os botões de ataque do jogador.
     * O botão de ataque especial só é reativado se ainda houver usos disponíveis.
     * @param desativar true para desativar os botões, false para ativar.
     */
    private void setBotoesAtaque(boolean desativar) {
        btnAtaqueFisico.setDisable(desativar);
        if (desativar) {
            btnAtaqueEspecial.setDisable(true);
        } else {
            btnAtaqueEspecial.setDisable(batalha.getContEspecial() <= 0);
        }
    }
}