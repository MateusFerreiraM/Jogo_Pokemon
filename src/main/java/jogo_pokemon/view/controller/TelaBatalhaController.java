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
import jogo_pokemon.utils.ImageUtils;
import jogo_pokemon.view.GerenciadorDeTelas;

public class TelaBatalhaController {

    @FXML private Label labelNomeInimigo;
    @FXML private ProgressBar barHPInimigo;
    @FXML private ImageView imgInimigo;

    @FXML private Label labelNomeJogador;
    @FXML private ProgressBar barHPJogador;
    @FXML private ImageView imgJogador;

    // REMOVIDO: As Labels de contador não são mais necessárias
    // @FXML private Label labelContadorEspecialInimigo;
    // @FXML private Label labelContadorEspecialJogador;

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

    @FXML
    void onAtaqueFisicoClick() {
        executarTurnoJogador(batalha.getPkmAmigo().getMovimentosList().get(0));
    }

    @FXML
    void onAtaqueEspecialClick() {
        batalha.decrementarContEspecial();
        // MODIFICADO: Agora atualiza o texto do próprio botão
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
            // REMOVIDO: A atualização do contador do inimigo não é mais visual
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
            
            if (batalha.getVitoria()) {
                GerenciadorDeTelas.mudarTela("TelaVitoria.fxml");
            } else {
                GerenciadorDeTelas.mudarTela("TelaDerrota.fxml");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void configurarTelaInicial() {
        Pokemon jogador = batalha.getPkmAmigo();
        Pokemon inimigo = batalha.getPkmInimigo();

        // Configura informações do jogador
        atualizarLabelVida(labelNomeJogador, jogador);
        configurarBarraDeVidaInicial(barHPJogador, jogador);
        ImageUtils.carregarPokemonImage(imgJogador, jogador.getImagePath());
        ImageUtils.aplicarSombra(imgJogador);

        // Configura informações do inimigo
        atualizarLabelVida(labelNomeInimigo, inimigo);
        configurarBarraDeVidaInicial(barHPInimigo, inimigo);
        ImageUtils.carregarPokemonImage(imgInimigo, inimigo.getImagePath());
        ImageUtils.aplicarSombra(imgInimigo);

        // MODIFICADO: A configuração inicial agora atualiza o texto do botão
        atualizarTextoBotaoEspecial();
        setBotoesAtaque(false);
    }
    
    // REMOVIDO: O método para atualizar a label foi substituído pelo método que atualiza o botão
    // private void atualizarContadorEspecialUI(Label label, int contador) { ... }

    // NOVO: Método para formatar e exibir o contador diretamente no botão de ataque especial
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