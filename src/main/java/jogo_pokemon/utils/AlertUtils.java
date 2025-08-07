package jogo_pokemon.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;

public class AlertUtils {

    public static void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null); // CORREÇÃO: Remove o texto do cabeçalho
        alert.setContentText(mensagem);

        // Aplica estilo (isto permanece igual)
        try {
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(AlertUtils.class.getResource("/jogo_pokemon/view/styles.css").toExternalForm());
            dialogPane.getStyleClass().add("dialog-pane");
        } catch (Exception e) {
            System.err.println("Aviso: Não foi possível aplicar o estilo ao Alert. Verifique o caminho do styles.css.");
            e.printStackTrace();
        }

        alert.showAndWait();
    }
}