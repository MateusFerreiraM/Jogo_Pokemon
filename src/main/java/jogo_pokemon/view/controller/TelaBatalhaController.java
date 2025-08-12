package jogo_pokemon.view.controller;

import java.io.IOException;
import java.util.List;
import java.util.Random;
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
import jogo_pokemon.utils.GerenciadorDeMusica; // NOVO: Importa o gerenciador de música
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
            try {
                GerenciadorDeTelas.mudarTela("TelaMenuPrincipal.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            configurarTelaInicial();
        }
    }

    // ... (os métodos onAtaqueFisicoClick, onAtaqueEspecialClick, executarTurnoJogador e executarTurnoInimigo permanecem iguais)
    
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

    private void executarTurnoJogador(Movimentos movimentoJogador) {
        setBotoesAtaque(true);
        batalha.atacar(batalha.getPkmAmigo(), batalha.getPkmInimigo(), movimentoJogador);
        atualizarLabelVida(labelNomeInimigo, batalha.getPkmInimigo());
        animarBarraDeVida(barHPInimigo, batalha.getPkmInimigo(), () -> {
            if (!batalha.getEmExecucao()) {
                fimDeBatalha();
            } else {
                executarTurnoInimigo();
            }
        });
    }

    private void executarTurnoInimigo() {
        System.out.println("Vez do inimigo...");
        int escolhaInimigo = (batalha.getContEspecialLider() > 0 && random.nextBoolean()) ? 1 : 0;
        Movimentos movimentoInimigo = batalha.getPkmInimigo().getMovimentosList().get(escolhaInimigo);
        if (escolhaInimigo == 1) {
            batalha.decrementarContEspecialLider();
        }
        batalha.atacar(batalha.getPkmInimigo(), batalha.getPkmAmigo(), movimentoInimigo);
        atualizarLabelVida(labelNomeJogador, batalha.getPkmAmigo());
        animarBarraDeVida(barHPJogador, batalha.getPkmAmigo(), () -> {
            if (!batalha.getEmExecucao()) {
                fimDeBatalha();
            } else {
                setBotoesAtaque(false);
            }
        });
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
                GerenciadorDeTelas.mudarTela("TelaVitoria.fxml");
            } else {
                GerenciadorDeTelas.mudarTela("TelaDerrota.fxml");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // ... (o restante dos seus métodos de ajuda permanece igual)
    
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