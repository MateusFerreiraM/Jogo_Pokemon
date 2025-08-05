package jogo_pokemon.telas;

import jogo_pokemon.GerenciadorDados;
import jogo_pokemon.Pokemon;
import jogo_pokemon.Treinador;
import jogo_pokemon.utils.ConsoleUtils;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class TelaPrimeiraEscolha extends Tela {

    private final GerenciadorDados gerenciador;

    public TelaPrimeiraEscolha(TelaContext context) {
        super(context);
        this.gerenciador = new GerenciadorDados();
    }

    @Override
    public void mostrarTela() {
        Treinador novoTreinador = this.contexto.getTreinador();
        System.out.println("Olá, " + novoTreinador.getNome()
                + "! Seja bem vindo(a) ao centro de escolha Pokémon!\nEscolha um dos Pokémons disponíveis em nosso laboratório para iniciar sua jornada.\n");
        System.out.println("1 - Charmander");
        System.out.println("2 - Squirtle");
        System.out.println("3 - Bulbasaur");
        System.out.println("4 - Pikachu\n");

        System.out.print("\n>");
        String escolha = contexto.getUserInput();

        int idEscolhido = 0;
        switch (escolha) {
            case "1": idEscolhido = 4; break;
            case "2": idEscolhido = 7; break;
            case "3": idEscolhido = 1; break;
            case "4": idEscolhido = 25; break;
        }

        // Se a escolha foi inválida, idEscolhido continuará 0.
        if (idEscolhido == 0) {
            System.out.println("Por favor insira um valor válido");
            ConsoleUtils.sleep(1500);
            return; // Encerra a execução do método aqui.
        }

        // **A CORREÇÃO ESTÁ AQUI**
        // Criamos uma variável final para ser usada na expressão lambda.
        final int finalIdEscolhido = idEscolhido;

        try {
            List<Pokemon> pokemonsDisponiveis = gerenciador.carregarPokemonsDisponiveis();

            // Usamos a variável final dentro do filter
            Optional<Pokemon> pokemonEscolhido = pokemonsDisponiveis.stream()
                    .filter(p -> p.getId() == finalIdEscolhido)
                    .findFirst();

            if (pokemonEscolhido.isPresent()) {
                novoTreinador.adicionarPokemon(pokemonEscolhido.get());

                List<Treinador> todosOsTreinadores = gerenciador.carregarTreinadores();
                todosOsTreinadores.add(novoTreinador);
                gerenciador.salvarTreinadores(todosOsTreinadores);

                System.out.println("\nVocê escolheu o " + pokemonEscolhido.get().getNome() + "! Parabéns!");
                System.out.println("Seu perfil de treinador foi salvo.");
                ConsoleUtils.sleep(2500);
                
                this.trocarTela(new TelaMenuPrincipal(this.contexto));
            } else {
                System.out.println("Erro: O Pokémon com ID " + finalIdEscolhido + " não foi encontrado na base de dados.");
                ConsoleUtils.sleep(2000);
            }

        } catch (IOException e) {
            System.out.println("Ocorreu um erro crítico ao salvar os dados: " + e.getMessage());
            ConsoleUtils.sleep(3000);
        }
    }
}