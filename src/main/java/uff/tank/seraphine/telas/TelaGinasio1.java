package uff.tank.seraphine.telas;

<<<<<<< HEAD
import uff.tank.seraphine.Batalha;
=======
>>>>>>> 7c396a327dedc009bd40a3c5838b40055d949381
import uff.tank.seraphine.LiderGin;
import uff.tank.seraphine.utils.JSONUtils;

public class TelaGinasio1 extends Tela {

    // Tela do ginásio Guadalupe (Mateus)
    @Override
    public void mostrarTela() {
        System.out.println("Saudações, " + this.contexto.getTreinador().getNome()
                + "! Este é o grande ginásio de Guadalupe!\nNosso mais forte líder de ginásio é o Mateus com seu astucioso Arcanine.\nTem certeza que consegue enfrentá-lo?\n");
        System.out.println("1 - Claro! Manda ver!");
        System.out.println("2 - Eh... Não tenho certeza, acho que vou voltar.\n");
        System.out.println("X - Sair");

        String escolha = this.contexto.getUserInput();

        switch (escolha) {
            case "1":
                LiderGin oponente = LiderGin.getLiderFromJSONObject(
<<<<<<< HEAD
                        JSONUtils.getObjectByID(1, "assets/lideres.json"));
                Batalha batalha = new Batalha(this.contexto.getTreinador().getPokemonAtual(),
                        oponente.getPokemonAtual());
                this.trocarTela(new TelaBatalha(this.contexto, oponente, batalha));
=======
                        JSONUtils.getObjectByID(1, "assets/lideres.json")
                );
                this.trocarTela(new TelaBatalha(this.contexto, oponente));
>>>>>>> 7c396a327dedc009bd40a3c5838b40055d949381
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