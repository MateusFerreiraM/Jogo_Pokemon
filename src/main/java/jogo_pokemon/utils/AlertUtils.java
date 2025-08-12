package jogo_pokemon.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AlertUtils {

    public static void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null); 
        alert.setContentText(mensagem);

        // MODIFICADO: Adicionamos um ícone personalizado de Pokébola ao alerta!
        try {
            ImageView icon = new ImageView(new Image(AlertUtils.class.getResourceAsStream("/jogo_pokemon/images/pokebola.png")));
            icon.setFitHeight(48);
            icon.setFitWidth(48);
            alert.setGraphic(icon);
        } catch (Exception e) {
            System.err.println("Aviso: Ícone da pokebola não encontrado.");
        }


        try {
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(AlertUtils.class.getResource("/jogo_pokemon/view/styles.css").toExternalForm());
            // MODIFICADO: Usamos uma classe de estilo mais específica
            dialogPane.getStyleClass().add("pokemon-dialog");
        } catch (Exception e) {
            System.err.println("Aviso: Não foi possível aplicar o estilo ao Alert. Verifique o caminho do styles.css.");
            e.printStackTrace();
        }

        alert.showAndWait();
    }
}