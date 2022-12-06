package uff.tank.seraphine.telas;

public class TelaPokedex extends Tela {
    @Override
    public void mostrarTela() {
        System.out.println("---------- Pokedex ----------");
        System.out.println("1- Ver Pokémon");
        System.out.println("2- Ver Informações do Treinador");
        System.out.println("X- Sair");
    }
    public TelaPokedex(TelaContext context){
        super(context);
    }
}
