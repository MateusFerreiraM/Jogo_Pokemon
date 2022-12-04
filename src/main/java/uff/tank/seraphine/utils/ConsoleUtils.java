package uff.tank.seraphine.utils;

public class ConsoleUtils {
    public static void clearConsole(){
        System.out.println("\033[H\033[2J");//Sequencia de escape para reposicionar o cursor e limpar o terminal
    }
}
