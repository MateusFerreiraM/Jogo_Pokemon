package jogo_pokemon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {

    private static Treinador treinadorSessao; // Para guardar o treinador atual

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("view/TelaInicial.fxml"));
        Scene scene = new Scene(root, 640, 480);
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