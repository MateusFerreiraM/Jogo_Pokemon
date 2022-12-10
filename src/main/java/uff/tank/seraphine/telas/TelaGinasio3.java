package uff.tank.seraphine.telas;

public class TelaGinasio3 extends Tela {

    // Tela ginasio de Boa Viagem (Cael)
    @Override
    public void mostrarTela() {
        System.out.println("Hey, " + this.contexto.getTreinador().getNome()
                + "! Agora você está no glorioso estádio de Boa Viagem.\nAqui se encontra o líder de ginásio Cael, com seu veloz Rapidash.\nTem certeza que consegue enfrentá-lo?\n");
        System.out.println("1 - Claro! Manda ver!");
        System.out.println("2 - Eh... Não tenho certeza, acho que vou voltar.\n");
        System.out.println("X - Sair");

        String escolha = this.contexto.getUserInput();

        switch (escolha) {
            case "1":
                this.trocarTela(new TelaBatalha(this.contexto));
                break;
            case "2":
                this.trocarTela(new TelaEscolherGinasio(this.contexto));
                break;
            case "X":
            case "x":
                this.contexto.sairPrograma();
                break;
            default:
                System.out.println("Por favor, insira um valor válido!");
        }
    }

    public TelaGinasio3(TelaContext context) {
        super(context);
    }
}