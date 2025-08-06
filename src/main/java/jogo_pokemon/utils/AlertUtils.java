package jogo_pokemon.utils;

import javafx.scene.control.Alert;

public class AlertUtils {
    public static void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);

        // Aplica o CSS ao DialogPane
        alert.getDialogPane().getStylesheets().add(
            AlertUtils.class.getResource("/jogo_pokemon/view/styles.css").toExternalForm());

        alert.showAndWait();
    }
}