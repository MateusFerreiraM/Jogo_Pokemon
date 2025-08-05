package uff.tank.seraphine.telas;

import java.util.Optional;
import java.util.stream.Collectors;
import uff.tank.seraphine.Pokemon;
import uff.tank.seraphine.utils.ConsoleUtils;

public class TelaSelecionarPokemon extends Tela {

    public TelaSelecionarPokemon(TelaContext context) {
        super(context);
    }

    @Override
    public void mostrarTela() {
        System.out.println("---------- Selecionar Pokémon ----------\n");
        if (this.contexto.getTreinador().getPokemonAtual() != null) {
            System.out.println("Pokémon atual: " + this.contexto.getTreinador().getPokemonAtual().getNome());
        } else {
            System.out.println("Você não possui nenhum Pokémon selecionado!");
        }

        System.out.println("\nSeus Pokémons:");
        // Itera e exibe a lista de pokémons que o jogador possui
        this.contexto.getTreinador().getPokemons().forEach(pkmn -> {
            String tiposFormatados = pkmn.getTipos().stream()
                    .map(Enum::toString)
                    .collect(Collectors.joining(" / "));
            System.out.println(pkmn.getId() + " - " + pkmn.getNome() + " (" + tiposFormatados + ")");
        });

        System.out.println("\nDigite o ID do Pokémon que deseja como 'Atual'.");
        System.out.println("V - Voltar ao menu principal");
        System.out.print("\n>");

        String escolha = this.contexto.getUserInput();

        if (escolha.equalsIgnoreCase("V")) {
            this.trocarTela(new TelaMenuPrincipal(this.contexto));
            return;
        }

        try {
            int idEscolhido = Integer.parseInt(escolha);
            
            // Usa streams para encontrar o Pokémon de forma mais limpa e eficiente
            Optional<Pokemon> pokemonEscolhido = this.contexto.getTreinador().getPokemons().stream()
                .filter(p -> p.getId() == idEscolhido)
                .findFirst();

            if (pokemonEscolhido.isPresent()) {
                this.contexto.getTreinador().setPokemonAtual(pokemonEscolhido.get());
                System.out.println("\n" + pokemonEscolhido.get().getNome() + " foi definido como seu Pokémon atual!");
                ConsoleUtils.sleep(2000);
            } else {
                System.out.println("\nVocê não possui um Pokémon com esse ID.");
                ConsoleUtils.sleep(1500);
            }
        } catch (NumberFormatException e) {
            System.out.println("\nEntrada inválida. Por favor, digite um ID ou 'V' para voltar.");
            ConsoleUtils.sleep(1500);
        }
    }
}