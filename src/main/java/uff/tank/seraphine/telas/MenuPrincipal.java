package uff.tank.seraphine.telas;

import uff.tank.seraphine.Treinador;

public class MenuPrincipal implements Tela{
    static void mudaTela(Treinador treinador){
        System.out.println("----------------");
        System.out.println("1- Selecionar pokémon principal");
        System.out.println("2- Abrir pokédex");
        System.out.println("3- Batalhar um ginásio");
        System.out.println("X- Sair");
        System.out.println("----------------");
    }
}
