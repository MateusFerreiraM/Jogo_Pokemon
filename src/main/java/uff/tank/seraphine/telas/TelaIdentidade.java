package uff.tank.seraphine.telas;


public class TelaIdentidade extends Tela {
    @Override
    public void mostrarTela() {
        System.out.println("=====Identidade=====");
        System.out.println("Nome: " + this.contexto.getTreinador().getNome());
        System.out.println("Regiao: " + this.contexto.getTreinador().getNome());
        System.out.println("1- Voltar");
        System.out.println("X- Sair");
    }
    public TelaIdentidade(TelaContext context){
        super(context);
    }
}
