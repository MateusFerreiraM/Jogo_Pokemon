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
import jogo_pokemon.view.GerenciadorDeTelas;

import java.io.IOException;
import java.net.URL;

public class App extends Application {

    private static Treinador treinadorSessao; // Para guardar o treinador atual

    @Override
    public void start(Stage stage) throws IOException {
        // Define os caminhos exatos e corretos para os recursos das fontes
        final String FONT_TITLE_PATH = "/jogo_pokemon/fonts/Pokemon_Solid_Normal.ttf";
        final String FONT_TEXT_PATH = "/jogo_pokemon/fonts/Pokemon_Classic_Regular.ttf";

        try {
            // Tenta carregar as fontes para a memória da aplicação
            Font.loadFont(getClass().getResourceAsStream(FONT_TITLE_PATH), 10);
            Font.loadFont(getClass().getResourceAsStream(FONT_TEXT_PATH), 10);
            System.out.println("SUCESSO: Fontes personalizadas carregadas na aplicação!");
        } catch (Exception e) {
            System.err.println("ERRO CRÍTICO: Falha ao carregar os ficheiros de fonte. Verifique os nomes e a localização.");
            e.printStackTrace();
        }
        
        // O resto do código continua igual...
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

    public static Treinador getTreinadorSessao() {
        return treinadorSessao;
    }

    public static void setTreinadorSessao(Treinador treinador) {
        treinadorSessao = treinador;
    }

    public static void main(String[] args) {
        launch();
    }

    private static Batalha batalhaAtual; // Novo campo para a batalha

    public static Batalha getBatalhaAtual() {
        return batalhaAtual;
    }

    public static void setBatalhaAtual(Batalha batalha) {
        batalhaAtual = batalha;
    }

    private static LiderGin liderSelecionado; // Novo campo

    public static LiderGin getLiderSelecionado() {
        return liderSelecionado;
    }

    public static void setLiderSelecionado(LiderGin lider) {
        liderSelecionado = lider;
    }

}