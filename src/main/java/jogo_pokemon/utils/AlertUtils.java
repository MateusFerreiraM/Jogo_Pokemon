package jogo_pokemon.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Classe utilitária para criar e exibir alertas padronizados e estilizados para a aplicação.
 */
public class AlertUtils {

    /**
     * Exibe um alerta estilizado com o tema do jogo.
     * O alerta usa um ícone de Pokébola e aplica os estilos definidos em styles.css.
     * @param titulo O título que aparecerá na barra da janela do alerta.
     * @param mensagem O texto principal a ser exibido no corpo do alerta.
     */
    public static void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null); 
        alert.setContentText(mensagem);

        // Adiciona um ícone personalizado de Pokébola ao alerta
        try {
            ImageView icon = new ImageView(new Image(AlertUtils.class.getResourceAsStream("/jogo_pokemon/images/pokebola.png")));
            icon.setFitHeight(48);
            icon.setFitWidth(48);
            alert.setGraphic(icon);
        } catch (Exception e) {
            System.err.println("Aviso: Ícone da pokebola não encontrado.");
        }

        // Aplica a folha de estilos e a classe de CSS personalizada
        try {
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(AlertUtils.class.getResource("/jogo_pokemon/view/styles.css").toExternalForm());
            dialogPane.getStyleClass().add("pokemon-dialog");
        } catch (Exception e) {
            System.err.println("Aviso: Não foi possível aplicar o estilo ao Alert. Verifique o caminho do styles.css.");
            e.printStackTrace();
        }

        alert.showAndWait();
    }
}