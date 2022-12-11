package uff.tank.seraphine.utils;

<<<<<<< HEAD
public class ConsoleUtils {
    public static void clearConsole() {
        // System.out.println("\033[H\033[2J");//Sequencia de escape para reposicionar o
        // cursor e limpar o terminal
        try {
=======

public class ConsoleUtils {
    public static void clearConsole(){
        //System.out.println("\033[H\033[2J");//Sequencia de escape para reposicionar o cursor e limpar o terminal
        try{
>>>>>>> 7c396a327dedc009bd40a3c5838b40055d949381
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
<<<<<<< HEAD
        } catch (Exception e) {
=======
        } catch (Exception e){
>>>>>>> 7c396a327dedc009bd40a3c5838b40055d949381
            e.printStackTrace();
        }
    }
}
