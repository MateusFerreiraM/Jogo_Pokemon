package jogo_pokemon.controller;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import jogo_pokemon.App;
import jogo_pokemon.Batalha;
import jogo_pokemon.GerenciadorDados;
import jogo_pokemon.GerenciadorDeTelas;
import jogo_pokemon.Pokemon;
import jogo_pokemon.Treinador;
import jogo_pokemon.Movimentos;

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
        
        // **A CORREÇÃO ESTÁ AQUI**
        // Se, por algum motivo, não houver batalha na sessão, mostramos um erro e voltamos.
        if (this.batalha == null) {
            System.err.println("ERRO: Tentativa de carregar a tela de batalha sem uma batalha em andamento.");
            // Idealmente, mostrar um alerta e voltar para o menu principal
            try {
                GerenciadorDeTelas.mudarTela("TelaMenuPrincipal.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Se a batalha existe, atualiza a tela
            atualizarTela();
        }
    }

    @FXML
    void onAtaqueFisicoClick() {
        processarTurno(batalha.getPkmAmigo().getMovimentosList().get(0));
    }

    @FXML
    void onAtaqueEspecialClick() {
        if (batalha.getContEspecial() > 0) {
            batalha.decrementarContEspecial();
            processarTurno(batalha.getPkmAmigo().getMovimentosList().get(1));
        } else {
            System.out.println("Sem ataques especiais! Usando ataque físico.");
            processarTurno(batalha.getPkmAmigo().getMovimentosList().get(0));
        }
    }

    private void processarTurno(Movimentos movimentoJogador) {
        // A lógica de processar turno continua a mesma
        batalha.atacar(batalha.getPkmAmigo(), batalha.getPkmInimigo(), movimentoJogador);
        atualizarTela();

        if (!batalha.getEmExecucao()) {
            fimDeBatalha();
            return;
        }

        System.out.println("Vez do inimigo...");
        int escolhaInimigo = (batalha.getContEspecialLider() > 0 && random.nextBoolean()) ? 1 : 0;
        Movimentos movimentoInimigo = batalha.getPkmInimigo().getMovimentosList().get(escolhaInimigo);
        
        if (escolhaInimigo == 1) {
            batalha.decrementarContEspecialLider();
        }

        batalha.atacar(batalha.getPkmInimigo(), batalha.getPkmAmigo(), movimentoInimigo);
        atualizarTela();

        if (!batalha.getEmExecucao()) {
            fimDeBatalha();
        }
    }
    
    private void fimDeBatalha() {
        // Desativa os botões para impedir mais ações
        btnAtaqueFisico.setDisable(true);
        btnAtaqueEspecial.setDisable(true);

        try {
            Treinador jogador = App.getTreinadorSessao();
            
            // "CURA" TODOS OS POKÉMONS DO JOGADOR NO FIM DA BATALHA
            System.out.println("A curar os seus Pokémon...");
            for (Pokemon pokemon : jogador.getPokemons()) {
                pokemon.setHpAtual(pokemon.getHp()); // Restaura o HP para o máximo
            }

            // SALVA O ESTADO ATUALIZADO DO JOGADOR
            System.out.println("A guardar o seu progresso...");
            GerenciadorDados gerenciador = new GerenciadorDados();
            List<Treinador> todosOsTreinadores = gerenciador.carregarTreinadores();
            for (int i = 0; i < todosOsTreinadores.size(); i++) {
                if (todosOsTreinadores.get(i).getId() == jogador.getId()) {
                    todosOsTreinadores.set(i, jogador); // Substitui o objeto antigo pelo novo
                    break;
                }
            }
            gerenciador.salvarTreinadores(todosOsTreinadores);
            
            // Limpa a batalha da sessão
            App.setBatalhaAtual(null); 
            
            // Navega para a tela de vitória ou derrota
            if (batalha.getVitoria()) {
                GerenciadorDeTelas.mudarTela("TelaVitoria.fxml");
            } else {
                GerenciadorDeTelas.mudarTela("TelaDerrota.fxml");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void atualizarTela() {
        // A lógica de atualizar tela continua a mesma
        Pokemon jogador = batalha.getPkmAmigo();
        Pokemon inimigo = batalha.getPkmInimigo();

        labelNomeJogador.setText(jogador.getNome() + " (" + jogador.getHpAtual() + "/" + jogador.getHp() + ")");
        barHPJogador.setProgress((double) jogador.getHpAtual() / jogador.getHp());

        labelNomeInimigo.setText(inimigo.getNome() + " (" + inimigo.getHpAtual() + "/" + inimigo.getHp() + ")");
        barHPInimigo.setProgress((double) inimigo.getHpAtual() / inimigo.getHp());
    }
}