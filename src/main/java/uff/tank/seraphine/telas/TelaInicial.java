package uff.tank.seraphine.telas;

//Primeira tela que o programa abre
public class TelaInicial extends Tela {
    @Override
    public void mostrarTela() {
        System.out.println("---------- Tela Inicial ----------\n");
        System.out.println("Selecione uma opção:");
        System.out.println("1 - Selecionar Treinador");
        System.out.println("2 - Criar treinador");
        System.out.println("X - Sair");
        System.out.print("\n>");

        String escolha = this.contexto.getUserInput();

        switch (escolha) {
            case "1":
                this.trocarTela(new TelaSelecionaTreinador(this.contexto));
                break;
            case "2":
                this.trocarTela(new TelaCriarTreinador(this.contexto));
                break;
            case "X":
            case "x":
                this.contexto.sairPrograma();
                break;
            default:
                System.out.println("Por favor, insira um valor válido!");
        }
    }

    public TelaInicial(TelaContext context) {
        super(context);
    }
}
