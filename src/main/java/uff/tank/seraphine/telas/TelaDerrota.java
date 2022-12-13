package uff.tank.seraphine.telas;

public class TelaDerrota extends Tela {

    @Override
    public void mostrarTela() {
        System.out.println("===== Você perdeu =====\n\n Treine mais e volte mais forte!\n\n");
        System.out.println("V - Voltar ao menu principal");
        System.out.println("X - Sair");

        String escolha = this.contexto.getUserInput();

        switch (escolha) {
            case "v":
            case "V":
                this.trocarTela(new TelaMenuPrincipal(this.contexto));
                break;
            case "x":
            case "X":
                this.contexto.sairPrograma();
                break;
            default:
                System.out.println("Por favor insira um valor válido");
        }
    }

    public TelaDerrota(TelaContext context) {
        super(context);
    }
}