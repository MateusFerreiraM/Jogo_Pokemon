package uff.tank.seraphine.telas;

import uff.tank.seraphine.CadastroTreinador;
import uff.tank.seraphine.Pokemon;
import uff.tank.seraphine.utils.ConsoleUtils;

public class TelaVitoria extends Tela {

    @Override
    public void mostrarTela() {
        System.out.println("===== Você Ganhou! =====\n");
        System.out.println("Como recompensa, você recebeu:");
        Pokemon novoPkmn;
        while (true) {
            novoPkmn = Pokemon.getPokemonAleatorio();
            if (!this.contexto.getTreinador().getPokemons().contains(novoPkmn)) {
                break;
            }
        }

        for (int i = 0; i < 6; i++) {
            System.out.print(".");
            ConsoleUtils.sleep(0750);
        }
        System.out.println(novoPkmn.getNome() + "!\n");
        this.contexto.getTreinador().addPokemon(novoPkmn);
        CadastroTreinador.atualizarTreinador(this.contexto.getTreinador());
        System.out.println("V- Voltar ao menu principal");
        System.out.println("X- Sair");

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

    public TelaVitoria(TelaContext context) {
        super(context);
    }
}