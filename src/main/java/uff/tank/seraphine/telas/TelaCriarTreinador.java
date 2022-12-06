package uff.tank.seraphine.telas;

import uff.tank.seraphine.Treinador;

import java.util.Scanner;

public class TelaCriarTreinador extends Tela {
    @Override
    public void mostrarTela() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("---------- Tela Cadastro ----------");

        System.out.print("Nome Treinador: ");

        String nome = teclado.next();
        nome = nome.trim();//Limpa o nome de qualquer espaço

        System.out.println("Região: ");
        String regiao = teclado.next();
        regiao = regiao.trim();

        Treinador novoTreinador;
        novoTreinador = new Treinador(nome, regiao);
        teclado.close();

        this.trocarTela(new TelaMenuPrincipal(novoTreinador, this.contexto));
    }
    public TelaCriarTreinador(TelaContext context){
        super(context);
    }
}
