package jogo_pokemon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import jogo_pokemon.model.Batalha;
import jogo_pokemon.model.LiderGin;
import jogo_pokemon.model.Treinador;
import jogo_pokemon.utils.GerenciadorDeMusica;
import jogo_pokemon.view.GerenciadorDeTelas;
import java.io.IOException;
import java.net.URL;

/**
 * Classe principal da aplicação.
 * Responsável por iniciar o JavaFX, carregar recursos iniciais (fontes, CSS, música)
 * e gerir o estado global do jogo (sessão do treinador, batalha atual, etc.).
 */
public class App extends Application {

    /** Guarda a instância do treinador que está atualmente a jogar. */
    private static Treinador treinadorSessao;
    /** Guarda a instância da batalha que está a decorrer. */
    private static Batalha batalhaAtual;
    /** Guarda a instância do líder de ginásio selecionado para um desafio. */
    private static LiderGin liderSelecionado;

    /**
     * Ponto de entrada principal para a aplicação JavaFX.
     * Configura e lança a janela principal (Stage).
     * @param stage O palco principal fornecido pelo runtime do JavaFX.
     * @throws IOException se o ficheiro FXML inicial não puder ser carregado.
     */
    @Override
    public void start(Stage stage) throws IOException {
        final String FONT_TITLE_PATH = "/jogo_pokemon/fonts/Pokemon_Solid_Normal.ttf";
        final String FONT_TEXT_PATH = "/jogo_pokemon/fonts/Pokemon_Classic_Regular.ttf";

        try {
            Font.loadFont(getClass().getResourceAsStream(FONT_TITLE_PATH), 10);
            Font.loadFont(getClass().getResourceAsStream(FONT_TEXT_PATH), 10);
        } catch (Exception e) {
            System.err.println("ERRO CRÍTICO: Falha ao carregar os ficheiros de fonte.");
            e.printStackTrace();
        }

        GerenciadorDeMusica.carregarMusicas();
        GerenciadorDeMusica.tocarMusicaMenu();
        
        Parent root = FXMLLoader.load(getClass().getResource("view/TelaInicial.fxml"));
        Scene scene = new Scene(root, 640, 480);

        final String CSS_PATH = "/jogo_pokemon/view/styles.css";
        URL css = getClass().getResource(CSS_PATH);
        if (css != null) {
            scene.getStylesheets().add(css.toExternalForm());
        } else {
            System.err.println("AVISO: Folha de estilos não encontrada no caminho: " + CSS_PATH);
        }

        GerenciadorDeTelas.inicializar(scene);
        stage.setTitle("Jogo Pokémon");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Este método é chamado quando a aplicação está a fechar.
     * Garante que os recursos, como a música, são parados corretamente.
     */
    @Override
    public void stop() {
        GerenciadorDeMusica.pararTudo();
        System.out.println("Aplicação encerrada.");
    }

    public static Treinador getTreinadorSessao() {
        return treinadorSessao;
    }

    public static void setTreinadorSessao(Treinador treinador) {
        treinadorSessao = treinador;
    }

    public static Batalha getBatalhaAtual() {
        return batalhaAtual;
    }

    public static void setBatalhaAtual(Batalha batalha) {
        batalhaAtual = batalha;
    }



    public static LiderGin getLiderSelecionado() {
        return liderSelecionado;
    }

    public static void setLiderSelecionado(LiderGin lider) {
        liderSelecionado = lider;
    }
    
    /**
     * O método main, ponto de entrada da aplicação Java.
     * @param args Argumentos de linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        launch();
    }
}