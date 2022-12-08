package uff.tank.seraphine.telas;

import java.util.Scanner;

public class TelaSelecionaTreinador extends Tela {
    @Override
    public void mostrarTela() {
        System.out.println("---------- Selecionar Treinador ----------");
        System.out.println("INCOMPLETO");
        // TODO: Exibir lista treinadores
        System.out.println("V- Voltar para a tela inicial");
        System.out.println("X- Sair");

        String escolha = this.contexto.getUserInput();

        switch (escolha) {
            case "v":
            case "V":
                this.trocarTela(new TelaInicial(this.contexto));
                break;
            case "x":
            case "X":
                this.contexto.sairPrograma();
                break;
            default:
                System.out.println("Por favor insira um valor válido");
                break;
        }
    }

    public TelaSelecionaTreinador(TelaContext contexto) {
        super(contexto);
    }
}
