package uff.tank.seraphine.telas;

public class TelaGinasio4 extends Tela {

    // Tela do ginásio de Seropedica (Maria)
    @Override
    public void mostrarTela() {
        System.out.println("E ai, " + this.contexto.getTreinador().getNome()
                + "? Chegamos ao vasto ginásio de Seropedica.\nEstamos na presença da poderosa Maria com seu Alakazam.\nTem certeza que consegue enfrentá-la?\n");
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

    public TelaGinasio4(TelaContext context) {
        super(context);
    }
}