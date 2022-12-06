package uff.tank.seraphine.telas;

import uff.tank.seraphine.telas.Tela;

public class TelaListaPokemon extends Tela {
    @Override
    public void mostrarTela() {
        System.out.println("\n\n\n\n---------- Selecione seu Pokemon ----------");
        System.out.println("Lista de Pokemons: ");
        //TODO: Criar função que retorna a lista de todos os pokemon do usuário.
        //TODO: Criar função que adiciona paginação à essa lista
        System.out.println("X- Sair");
        System.out.println("\n\n\n\n");
    }
    public TelaListaPokemon(TelaContext context){
        super(context);
    }
}
