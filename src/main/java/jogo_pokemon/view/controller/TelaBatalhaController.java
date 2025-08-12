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
    
    @FXML
    void onAtaqueFisicoClick() {
        executarTurnoJogador(batalha.getPkmAmigo().getMovimentosList().get(0));
    }

    @FXML
    void onAtaqueEspecialClick() {
        batalha.decrementarContEspecial();
        atualizarTextoBotaoEspecial();
        executarTurnoJogador(batalha.getPkmAmigo().getMovimentosList().get(1));
    }

    // MODIFICADO: O fluxo do turno agora inclui as novas animações
    private void executarTurnoJogador(Movimentos movimentoJogador) {
        setBotoesAtaque(true);
        // 1. Anima o ataque do jogador
        animarAtaque(imgJogador, () -> {
            // 2. Quando a animação de ataque termina, calcula o dano
            batalha.atacar(batalha.getPkmAmigo(), batalha.getPkmInimigo(), movimentoJogador);
            atualizarLabelVida(labelNomeInimigo, batalha.getPkmInimigo());
            
            // 3. Inicia a animação de dano no inimigo (tremor)
            animarDano(imgInimigo);

            // 4. Inicia a animação da barra de vida, que é mais longa
            animarBarraDeVida(barHPInimigo, batalha.getPkmInimigo(), () -> {
                // 5. Quando a barra de vida termina de animar, verifica o estado da batalha
                if (!batalha.getEmExecucao()) {
                    fimDeBatalha();
                } else {
                    executarTurnoInimigo();
                }
            });
        });
    }

    // MODIFICADO: O fluxo do turno do inimigo também inclui as novas animações
    private void executarTurnoInimigo() {
        System.out.println("Vez do inimigo...");
        
        // 1. Anima o ataque do inimigo
        animarAtaque(imgInimigo, () -> {
            // 2. Quando a animação de ataque termina, escolhe o movimento e calcula o dano
            int escolhaInimigo = (batalha.getContEspecialLider() > 0 && random.nextBoolean()) ? 1 : 0;
            Movimentos movimentoInimigo = batalha.getPkmInimigo().getMovimentosList().get(escolhaInimigo);
            if (escolhaInimigo == 1) {
                batalha.decrementarContEspecialLider();
            }
            batalha.atacar(batalha.getPkmInimigo(), batalha.getPkmAmigo(), movimentoInimigo);
            atualizarLabelVida(labelNomeJogador, batalha.getPkmAmigo());
            
            // 3. Inicia a animação de dano no jogador (tremor)
            animarDano(imgJogador);

            // 4. Inicia a animação da barra de vida
            animarBarraDeVida(barHPJogador, batalha.getPkmAmigo(), () -> {
                // 5. Quando a barra de vida termina, verifica o estado da batalha ou reativa os botões
                if (!batalha.getEmExecucao()) {
                    fimDeBatalha();
                } else {
                    setBotoesAtaque(false);
                }
            });
        });
    }

    // NOVO: Método para animar o "salto" de ataque
    private void animarAtaque(ImageView atacanteView, Runnable aoConcluir) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(150), atacanteView);
        tt.setByX(20); // Move 20 pixels para a direita
        tt.setByY(-10); // Move 10 pixels para cima
        tt.setCycleCount(2); // Faz o movimento 2 vezes (ida e volta)
        tt.setAutoReverse(true); // Garante que o segundo ciclo é o de retorno

        tt.setOnFinished(event -> {
            if (aoConcluir != null) {
                aoConcluir.run(); // Executa a próxima ação da batalha
            }
        });

        tt.play();
    }

    // NOVO: Método para animar o "tremor" de dano
    private void animarDano(ImageView defensorView) {
        TranslateTransition tt1 = new TranslateTransition(Duration.millis(50), defensorView);
        tt1.setByX(5);
        TranslateTransition tt2 = new TranslateTransition(Duration.millis(50), defensorView);
        tt2.setByX(-10);
        TranslateTransition tt3 = new TranslateTransition(Duration.millis(50), defensorView);
        tt3.setByX(10);
        TranslateTransition tt4 = new TranslateTransition(Duration.millis(50), defensorView);
        tt4.setByX(-5);

        // Executa as pequenas translações em sequência para criar o efeito de tremor   
        SequentialTransition st = new SequentialTransition(tt1, tt2, tt3, tt4);
        st.play();
    }

    private void fimDeBatalha() {
        try {
            // MODIFICADO: Toca o efeito sonoro ANTES de qualquer outra coisa
            if (batalha.getVitoria()) {
                GerenciadorDeMusica.tocarSfxVitoria();
            } else {
                GerenciadorDeMusica.tocarSfxDerrota();
            }

            Treinador jogador = App.getTreinadorSessao();
            System.out.println("A curar os seus Pokémon...");
            for (Pokemon pokemon : jogador.getPokemons()) {
                pokemon.setHpAtual(pokemon.getHp());
            }

            System.out.println("A guardar o seu progresso...");
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
            
            // A transição de tela acontece enquanto o som de vitória/derrota toca
            if (batalha.getVitoria()) {
                GerenciadorDeTelas.irParaTelaDeVitoria();
            } else {
                GerenciadorDeTelas.irParaTelaDeDerrota();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        
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
    
    private void atualizarTextoBotaoEspecial() {
        int contador = batalha.getContEspecial();
        btnAtaqueEspecial.setText("Ataque Especial (" + contador + "/2)");
    }

    private void configurarBarraDeVidaInicial(ProgressBar barra, Pokemon pokemon) {
        double porcentagem = (double) pokemon.getHpAtual() / pokemon.getHp();
        barra.setProgress(porcentagem);
        atualizarCorBarraVida(barra, porcentagem);
    }
    
    private void atualizarLabelVida(Label label, Pokemon pokemon) {
        label.setText(pokemon.getNome() + " (" + pokemon.getHpAtual() + "/" + pokemon.getHp() + ")");
    }

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

    private void setBotoesAtaque(boolean desativar) {
        btnAtaqueFisico.setDisable(desativar);
        if (desativar) {
            btnAtaqueEspecial.setDisable(true);
        } else {
            btnAtaqueEspecial.setDisable(batalha.getContEspecial() <= 0);
        }
    }
}