package uff.tank.seraphine.telas;

public class TelaEscolherGinasio extends Tela {
    @Override
    public void mostrarTela() {
        System.out.println("---------- Ginásios ----------\n");
        System.out.println("Selecione um Ginásio:");
        System.out.println("1 - Guadalupe");
        System.out.println("2 - Valadares");
        System.out.println("3 - Boa Viagem");
        System.out.println("4 - Seropedica");
        // TODO: Função para recuperar o número de ginásios
        // TODO: Função para listar os ginásios com paginação
        System.out.println("\nV - Voltar ao menu principal");
        System.out.println("X - Sair");

        String escolha = contexto.getUserInput();

        switch (escolha) {
            case "1":
                this.trocarTela(new TelaGinasio1(this.contexto));
                break;
            case "2":
                this.trocarTela(new TelaGinasio2(this.contexto));
                break;
            case "3":
                this.trocarTela(new TelaGinasio3(this.contexto));
                break;
            case "4":
                this.trocarTela(new TelaGinasio4(this.contexto));
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
