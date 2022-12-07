package uff.tank.seraphine.telas;
import uff.tank.seraphine.Treinador;

public class TelaCriarTreinador extends Tela {
    @Override
    public void mostrarTela() {
        System.out.println("---------- Tela Cadastro ----------");

        System.out.println("Nome Treinador: ");
        System.out.print(">");

        String nome = this.contexto.getInput().nextLine();
        nome = nome.trim(); //Limpa o nome de qualquer espaço

        System.out.println("Região: ");
        String regiao = this.contexto.getInput().nextLine();
        regiao = regiao.trim();

        Treinador novoTreinador = new Treinador(nome, regiao);
        this.contexto.setTreinador(novoTreinador);

        this.trocarTela(new TelaMenuPrincipal(this.contexto));
    }
    public TelaCriarTreinador(TelaContext context){
        super(context);
    }
}
