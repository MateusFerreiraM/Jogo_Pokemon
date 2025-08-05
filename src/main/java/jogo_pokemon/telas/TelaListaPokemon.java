package jogo_pokemon.telas;

import jogo_pokemon.Pokemon;

public class TelaListaPokemon extends Tela {

    @Override
    public void mostrarTela() {
        System.out.println("---------- Seus Pokémons ----------\n");
        System.out.println("Lista de Pokémons: \n");

        int i = 1;
        for (Pokemon pkmn : this.contexto.getTreinador().getPokemons()) {
            // Transforma a lista de tipos numa string única, separada por "/"
            String tiposFormatados = pkmn.getTipos().stream()
                    .map(Enum::toString)
                    .collect(java.util.stream.Collectors.joining(" / "));
                    
            System.out.println(i + " - " + pkmn.getNome() + " (" + tiposFormatados + ")");
            i++;
        }
        
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
