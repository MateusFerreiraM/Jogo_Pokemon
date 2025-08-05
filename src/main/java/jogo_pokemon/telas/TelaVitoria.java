package jogo_pokemon.telas;

import jogo_pokemon.GerenciadorDados;
import jogo_pokemon.Pokemon;
import jogo_pokemon.Treinador;
import jogo_pokemon.utils.ConsoleUtils;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class TelaVitoria extends Tela {

    public TelaVitoria(TelaContext context) {
        super(context);
    }

    @Override
    public void mostrarTela() {
        System.out.println("===== PARABÉNS! Você Ganhou! =====\n");
        System.out.println("Como recompensa, você recebeu um novo Pokémon!\n");

        GerenciadorDados gerenciador = new GerenciadorDados();
        Treinador jogador = this.contexto.getTreinador();
        Pokemon novoPkmn = null;

        try {
            // 1. Resolve "getPokemonAleatorio()": Carrega todos os pokémons disponíveis
            List<Pokemon> pokemonsDisponiveis = gerenciador.carregarPokemonsDisponiveis();
            List<String> nomesPokemonJogador = jogador.getPokemons().stream()
                    .map(Pokemon::getNome)
                    .collect(Collectors.toList());

            // Filtra a lista para ter apenas os Pokémon que o jogador ainda não tem
            List<Pokemon> pokemonsDeRecompensa = pokemonsDisponiveis.stream()
                    .filter(p -> !nomesPokemonJogador.contains(p.getNome()))
                    .collect(Collectors.toList());

            if (!pokemonsDeRecompensa.isEmpty()) {
                // Escolhe um aleatório da lista de possíveis recompensas
                novoPkmn = pokemonsDeRecompensa.get(new Random().nextInt(pokemonsDeRecompensa.size()));
            }

            if (novoPkmn != null) {
                for (int i = 0; i < 6; i++) {
                    System.out.print(".");
                    ConsoleUtils.sleep(500);
                }

                System.out.println("\n\n" + novoPkmn.getNome() + "!\n");

                // 2. Resolve "addPokemon": Usa o método renomeado
                jogador.adicionarPokemon(novoPkmn);

                // 3. Resolve "CadastroTreinador": Usa o GerenciadorDados para atualizar
                List<Treinador> todosOsTreinadores = gerenciador.carregarTreinadores();
                // Encontra o jogador atual na lista e substitui pelo objeto atualizado
                for (int i = 0; i < todosOsTreinadores.size(); i++) {
                    if (todosOsTreinadores.get(i).getId() == jogador.getId()) {
                        todosOsTreinadores.set(i, jogador);
                        break;
                    }
                }
                gerenciador.salvarTreinadores(todosOsTreinadores);
                System.out.println(novoPkmn.getNome() + " foi adicionado à sua equipe e seu progresso foi salvo.");

            } else {
                System.out.println("Você já capturou todos os Pokémon disponíveis! Incrível!");
            }

        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao buscar a recompensa ou salvar os dados: " + e.getMessage());
            ConsoleUtils.sleep(2000);
        }

        // Menu de navegação
        System.out.println("\nV - Voltar ao menu principal");
        System.out.println("X - Sair");

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
}