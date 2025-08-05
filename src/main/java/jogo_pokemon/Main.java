package jogo_pokemon;

import jogo_pokemon.telas.TelaContext;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Iniciando as telas
        Scanner input = new Scanner(System.in); // Criamos um scanner para input no início da execução.
        TelaContext context = new TelaContext(input);
        while (context.isRodando()) {
            context.mostrarTela();
        }

        input.close();
    }
}   