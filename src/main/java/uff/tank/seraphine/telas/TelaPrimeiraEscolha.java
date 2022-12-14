package uff.tank.seraphine.telas;

import uff.tank.seraphine.CadastroTreinador;
import uff.tank.seraphine.Pokemon;
import uff.tank.seraphine.utils.ConsoleUtils;
import uff.tank.seraphine.utils.JSONUtils;

public class TelaPrimeiraEscolha extends Tela {

    private final String POKEMON_PATH = "assets/pokemon.json";

    @Override
    public void mostrarTela() {
        System.out.println("Olá, " + this.contexto.getTreinador().getNome()
                + "! Seja bem vindo(a) ao centro de escolha Pokémon!\nEscolha um dos Pokémons disponíveis em nosso laboratório para iniciar sua jornada.\n");
        System.out.println("1 - Charmander");
        System.out.println("2 - Squirtle");
        System.out.println("3 - Bulbasaur");
        System.out.println("4 - Pikachu\n");

        System.out.print("\n>");
        String escolha = contexto.getUserInput();

        Pokemon pkmn = null;
        switch (escolha) {
            case "1": // charmander
                pkmn = Pokemon.getPokemonFromJSONObject(
                        JSONUtils.getObjectByID(4, POKEMON_PATH));
                this.contexto.getTreinador().adicionarPokemon(pkmn);
                this.trocarTela(new TelaMenuPrincipal(this.contexto));
                break;

            case "2": // squirtle
                pkmn = Pokemon.getPokemonFromJSONObject(
                        JSONUtils.getObjectByID(7, POKEMON_PATH));
                this.contexto.getTreinador().adicionarPokemon(pkmn);
                this.trocarTela(new TelaMenuPrincipal(this.contexto));
                break;

            case "3": // bulbasaur
                pkmn = Pokemon.getPokemonFromJSONObject(
                        JSONUtils.getObjectByID(1, POKEMON_PATH));
                this.contexto.getTreinador().adicionarPokemon(pkmn);
                this.trocarTela(new TelaMenuPrincipal(this.contexto));
                break;

            case "4": // pikachu
                pkmn = Pokemon.getPokemonFromJSONObject(
                        JSONUtils.getObjectByID(25, POKEMON_PATH));
                this.contexto.getTreinador().adicionarPokemon(pkmn);
                this.trocarTela(new TelaMenuPrincipal(this.contexto));
                break;

            default:
                System.out.println("Por favor insira um valor válido");
                ConsoleUtils.sleep(1500);
        }

        CadastroTreinador.cadastrarTreinador(this.contexto.getTreinador());
        System.out.println(this.contexto.getTreinador().pokemonAtual);
    }

    public TelaPrimeiraEscolha(TelaContext context) {
        super(context);
    }
}
