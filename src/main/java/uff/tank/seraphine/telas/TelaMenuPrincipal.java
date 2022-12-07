package uff.tank.seraphine.telas;

//Menu pós login/criação de treinador

public class TelaMenuPrincipal extends Tela {
    public void mostrarTela(){
        System.out.println("=====Menu Principal=====");
        System.out.println("Olá, " + this.contexto.getTreinador().getNome()+ "\n");
        System.out.println("1- Selecionar Pokémon atual");
        System.out.println("2- Abrir Pokédex");
        System.out.println("3- Batalhar Ginásio");

        this.contexto.getInput().nextLine();
    }
    public TelaMenuPrincipal(TelaContext context){
        super(context);
    }
}
