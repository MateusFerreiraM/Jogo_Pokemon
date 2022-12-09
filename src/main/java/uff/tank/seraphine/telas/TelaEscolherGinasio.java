package uff.tank.seraphine.telas;

public class TelaEscolherGinasio extends Tela {
    @Override
    public void mostrarTela() {
        System.out.println("---------- Ginásios ----------\n");
        System.out.println("Selecione um Ginásio:");
        // TODO: Função para recuperar o número de ginásios
        // TODO: Função para listar os ginásios com paginação
        System.out.println("\nV - Voltar ao menu principal");
        System.out.println("X - Sair");

        String escolha = contexto.getUserInput();

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

    public TelaEscolherGinasio(TelaContext context) {
        super(context);
    }
}
