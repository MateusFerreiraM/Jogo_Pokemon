package uff.tank.seraphine.utils;

public class ConsoleUtils {
    public static void clearConsole() {
        // System.out.println("\033[H\033[2J");//Sequencia de escape para reposicionar o
        // cursor e limpar o terminal
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
