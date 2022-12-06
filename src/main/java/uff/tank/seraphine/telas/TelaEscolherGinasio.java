package uff.tank.seraphine.telas;

import uff.tank.seraphine.telas.Tela;

public class TelaEscolherGinasio extends Tela {
    @Override
    public void mostrarTela() {
        System.out.println("---------- Ginásios ----------");
        System.out.println("Selecione um Ginásio:");
        //TODO: Função para recuperar o número de ginásios
        //TODO: Função para listar os ginásios com paginação
        System.out.println("X- Sair");
        System.out.println("\n\n\n\n");
    }
    public TelaEscolherGinasio(TelaContext context){
        super(context);
    }
}
