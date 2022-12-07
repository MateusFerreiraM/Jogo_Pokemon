package uff.tank.seraphine.telas;
import uff.tank.seraphine.Treinador;

public class TelaCriarTreinador extends Tela {
    @Override
    public void mostrarTela() {
        System.out.println("---------- Tela Cadastro ----------");

        System.out.print("Nome Treinador: ");

        String nome = this.contexto.getUserInput();

        System.out.print("Região: ");

        String regiao = this.contexto.getUserInput();

        Treinador novoTreinador = new Treinador(nome, regiao);
        this.contexto.setTreinador(novoTreinador);

        this.trocarTela(new TelaMenuPrincipal(this.contexto));
    }
    public TelaCriarTreinador(TelaContext context){
        super(context);
    }
}
