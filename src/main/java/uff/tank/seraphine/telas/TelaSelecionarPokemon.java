package uff.tank.seraphine.telas;

import uff.tank.seraphine.Pokemon;

public class TelaSelecionarPokemon extends Tela {

    @Override
    public void mostrarTela() {
        System.out.println("---------- Selecionar Pokémon ----------\n");
        if (this.contexto.getTreinador().pokemonAtual != null) {
            System.out.println("Pokémon atual: " + this.contexto.getTreinador().pokemonAtual.getNome());
        } else {
            System.out.println("Você não possuí nenhum Pokémon selecionado!");
        }

        System.out.println("\nLista de Pokemons : ");
        for (Pokemon pkmn : this.contexto.getTreinador().getPokemons()) {
            System.out.println(pkmn.getId() + " - " + pkmn.getNome() + "/" + pkmn.getTipos());
        }
        System.out.println("\nV - Voltar ao menu principal");
        System.out.println("X - Sair");
        System.out.print(">");

        String escolha = this.contexto.getUserInput();

        boolean isNumber = false;

        try {
            // Tenta ver se o input é um número que pode ser Id
            // Se o parse falhar, a string contém letras, logo, não é um Id
            Integer.parseInt(escolha);
            isNumber = true;
        } catch (NumberFormatException e) {
            isNumber = false;
        }

        if (isNumber) {
            boolean naLista = false;
            for (Pokemon i : this.contexto.getTreinador().getPokemons()) {
                if (i.getId() == Integer.parseInt(escolha)) {
                    this.contexto.getTreinador().setPokemonAtual(i);
                    naLista = true;
                    break;
                }
            }

            if (!naLista) {
                System.out.println("Pokémon não encontrado!");
            }
        } else {
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
                    break;
            }
        }
    }

    public TelaSelecionarPokemon(TelaContext context) {
        super(context);
    }
}
