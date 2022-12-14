package uff.tank.seraphine.telas;

import uff.tank.seraphine.Batalha;
import uff.tank.seraphine.LiderGin;
import uff.tank.seraphine.utils.JSONUtils;

public class TelaGinasio5 extends Tela {

    // Tela do ginásio de Niteroi (Vania)
    @Override
    public void mostrarTela() {
        System.out.println("Oi, " + this.contexto.getTreinador().getNome()
                + "? Estamos no grandioso ginásio de Computação!\nCuidado com a perigosa Vania e seu Meowth.\nTem certeza que consegue enfrentá-la?\n");
        System.out.println("1 - Claro! Manda ver!");
        System.out.println("2 - Eh... Não tenho certeza, acho que vou voltar.\n");
        System.out.println("X - Sair");

        String escolha = this.contexto.getUserInput();

        switch (escolha) {
            case "1":
                LiderGin oponente = LiderGin.getLiderFromJSONObject(
                        JSONUtils.getObjectByID(5, "assets/lideres.json"));
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

    public TelaGinasio5(TelaContext context) {
        super(context);
    }

}
