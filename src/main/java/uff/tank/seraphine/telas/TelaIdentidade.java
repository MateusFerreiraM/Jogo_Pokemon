package uff.tank.seraphine.telas;

import uff.tank.seraphine.telas.Tela;

public class TelaIdentidade extends Tela {
    @Override
    public void mostrarTela() {
        super.mostrarTela();
        System.out.println("=====Menu Principal=====");
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("Regiao: " + usuario.getNome());
        System.out.println("1- Voltar");
        System.out.println("X- Sair");
    }
    public TelaIdentidade(TelaContext context){
        super(context);
    }
}
