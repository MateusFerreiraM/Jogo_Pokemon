package uff.tank.seraphine.telas;

import uff.tank.seraphine.Batalha;
import uff.tank.seraphine.LiderGin;
import uff.tank.seraphine.utils.JSONUtils;

public class TelaGinasio4 extends Tela {

    // Tela do ginásio de Seropedica (Maria)
    @Override
    public void mostrarTela() {
        System.out.println("E ai, " + this.contexto.getTreinador().getNome()
                + "? Chegamos ao vasto ginásio de Seropedica.\nEstamos na presença da poderosa Maria, com seu Alakazam.\nTem certeza que consegue enfrentá-la?\n");
        System.out.println("1 - Claro! Manda ver!");
        System.out.println("2 - Eh... Não tenho certeza, acho que vou voltar.\n");
        System.out.println("X - Sair");

        String escolha = this.contexto.getUserInput();

        switch (escolha) {
            case "1":
                LiderGin oponente = LiderGin.getLiderFromJSONObject(
                        JSONUtils.getObjectByID(4, "assets/lideres.json"));
                Batalha batalha = new Batalha(this.contexto.getTreinador().getPokemonAtual(),
                        oponente.getPokemonAtual());
                this.trocarTela(new TelaBatalha(this.contexto, oponente, batalha));
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