package uff.tank.seraphine.telas;

import java.util.Scanner;

//Primeira tela que o programa abre
public class TelaInicial extends Tela {
    @Override
    public void mostrarTela() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("---------- Tela Inicial ----------");
        System.out.println("Selecione uma opção:");
        System.out.println("1- Selecionar Treinador");
        System.out.println("2- Criar treinador");
        System.out.println("X- Sair");
        System.out.print("\n>");

        String escolha = teclado.next();
        escolha = escolha.trim();//Remove qualquer espaço antes e depois da escolha

        switch (escolha){
            case "1":
                this.trocarTela(new TelaSelecionaTreinador(this.contexto));
                break;
            case "2":
                this.trocarTela(new TelaCriarTreinador(this.contexto));
                break;
            case "X":
            case "x":
                break;
            default:
                System.out.println("Por favor, insira um valor válido!");
        }
        teclado.close();
    }
    public TelaInicial(TelaContext context){
        super(context);
    }
}
