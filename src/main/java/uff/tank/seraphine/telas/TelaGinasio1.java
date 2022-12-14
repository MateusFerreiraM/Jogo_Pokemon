package uff.tank.seraphine.telas;

import uff.tank.seraphine.Batalha;
import uff.tank.seraphine.LiderGin;
import uff.tank.seraphine.utils.JSONUtils;

public class TelaGinasio1 extends Tela {

    // Tela do ginásio Guadalupe (Mateus)
    @Override
    public void mostrarTela() {
        System.out.println("Saudações, " + this.contexto.getTreinador().getNome()
                + "! Este é o grande ginásio de Guadalupe!\nNosso mais forte líder de ginásio é o Mateus, com seu astucioso Arcanine.\nTem certeza que consegue enfrentá-lo?\n");
        System.out.println("1 - Claro! Manda ver!");
        System.out.println("2 - Eh... Não tenho certeza, acho que vou voltar.\n");
        System.out.println("X - Sair");

        String escolha = this.contexto.getUserInput();

        switch (escolha) {
            case "1":
                LiderGin oponente = LiderGin.getLiderFromJSONObject(
                        JSONUtils.getObjectByID(1, "assets/lideres.json"));
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

    public TelaGinasio1(TelaContext context) {
        super(context);
    }
}