package uff.tank.seraphine.telas;

import uff.tank.seraphine.Treinador;
import uff.tank.seraphine.telas.Tela;
import uff.tank.seraphine.telas.TelaContext;

//Menu pós login/criação de treinador

public class TelaMenuPrincipal extends Tela {
    public void mostrarTela(){
        System.out.println("=====Menu Principal=====");
        System.out.println("Olá, " + usuario.getNome()+ "\n");
        System.out.println("1- Selecionar Pokémon atual");
        System.out.println("2- Abrir Pokédex");
        System.out.println("3- Batalhar Ginásio");

    }
    public TelaMenuPrincipal(Treinador treinador, TelaContext context){
        super(context);
        this.usuario = treinador;
    }

    public Treinador getUsuario() {
        return usuario;
    }

}
