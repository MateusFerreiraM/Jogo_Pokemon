package jogo_pokemon.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import jogo_pokemon.App;

import java.io.IOException;

public class GerenciadorDeTelas {

    private static Scene cenaPrincipal;

    public static void inicializar(Scene scene) {
        cenaPrincipal = scene;
    }

    public static void mudarTela(String fxml) throws IOException {
        // Carrega o novo FXML a partir da pasta /resources/jogo_pokemon/view/
        Parent root = FXMLLoader.load(App.class.getResource("view/" + fxml));
        
        // Agora 'cenaPrincipal' existe e podemos alterar o seu conteúdo
        cenaPrincipal.setRoot(root);
    }
}