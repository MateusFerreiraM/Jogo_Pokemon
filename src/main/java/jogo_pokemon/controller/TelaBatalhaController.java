package jogo_pokemon.controller;

import java.io.IOException;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import jogo_pokemon.App;
import jogo_pokemon.Batalha;
import jogo_pokemon.GerenciadorDeTelas;
import jogo_pokemon.Pokemon;
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
        // A lógica de fim de batalha continua a mesma
        btnAtaqueFisico.setDisable(true);
        btnAtaqueEspecial.setDisable(true);

        String resultado = batalha.getVitoria() ? "Você Venceu!" : "Você Perdeu!";
        mostrarAlerta("Fim da Batalha", resultado);

        try {
            App.setBatalhaAtual(null);
            GerenciadorDeTelas.mudarTela("TelaInicial.fxml");
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

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}