package uff.tank.seraphine.telas;

import org.json.simple.JSONObject;
import uff.tank.seraphine.utils.JSONUtils;

public class TelaIdentidade extends Tela {

    @Override
    public void mostrarTela() {

        JSONObject obj = JSONUtils.getObjectByID(this.contexto.getTreinador().getId(), "assets/dados.json");

        System.out.println("---------- Identidade ----------\n");
        System.out.println("Nome: " + obj.get("Nome"));
        System.out.println("Regiao: " + obj.get("Regiao"));
        System.out.println("ID: " + obj.get("Id"));
        System.out.println("Número de Pokémon obtidos: " + this.contexto.getTreinador().getQtdPokemon());
        System.out.println("\nV - Voltar à pokédex");
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

    public TelaIdentidade(TelaContext context) {
        super(context);
    }
}
