package uff.tank.seraphine;

import org.json.simple.JSONObject;
import uff.tank.seraphine.utils.JSONUtils;
import java.util.ArrayList;

public class LiderGin extends Treinador {   // Lider de Ginásio é um tipo especial de treinador

    public LiderGin(String nome, String regiao, int id, Pokemon pkmn) {
        super(nome, regiao);
        this.id = id;
        this.pokemonAtual = pkmn;
        this.pokemons = new ArrayList<Pokemon>();
        this.pokemons.add(pkmn);
    }

    public static LiderGin getLiderFromJSONObject(JSONObject obj) {
        int id = Integer.parseInt(obj.get("Id").toString());
        String nome = obj.get("Nome").toString();
        String regiao = obj.get("Regiao").toString();
        Pokemon pokemon;
        if (obj.get("Pokemon").toString().equals("random")) {
            pokemon = Pokemon.getPokemonAleatorio();
        } else {
            pokemon = Pokemon.getPokemonFromJSONObject(
                    JSONUtils.getObjectByName(obj.get("Pokemon").toString(), "assets/pokemon.json"));
        }

        return new LiderGin(nome, regiao, id, pokemon);
    }
}
