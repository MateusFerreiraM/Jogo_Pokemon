package jogo_pokemon.utils;

import java.io.IOException;

public class ConsoleUtils {
    public static void clearConsole() {
        // Sequencia de escape para reposicionar o cursor e limpar o terminal
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // APLICAÇÃO DA FORMA MODERNA E CORRETA
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            // Captura de exceções mais específicas, o que é uma boa prática
            e.printStackTrace();
        }
    }

    public static void sleep(int time) {
        try {
            // A conversão para Long não é necessária, pois Thread.sleep aceita long
            Thread.sleep(time);
        } catch (InterruptedException e) {
            // Captura da exceção específica em vez de uma genérica
            e.printStackTrace();
        }
    }
}