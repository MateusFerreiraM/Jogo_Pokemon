package uff.tank.seraphine.telas;

import org.json.simple.JSONObject;
import uff.tank.seraphine.verPokedex;

public class TelaListaPokemon extends Tela {
    @Override
    public void mostrarTela() {
        System.out.println("---------- Selecione seu Pokemon ----------\n");
        JSONObject obj = verPokedex.mostrarPokedex();
        System.out.println("Lista de Pokemons: " + obj.get("pokemons"));
        System.out.println("\nV - Voltar à Pokédex");
        System.out.println("X - Sair");

        String escolha = this.contexto.getUserInput();

        switch (escolha) {
            case "v":
            case "V":
                this.trocarTela(new TelaPokedex(this.contexto));
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

    public TelaListaPokemon(TelaContext context) {
        super(context);
    }
}
