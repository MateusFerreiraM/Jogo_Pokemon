package uff.tank.seraphine.telas;

import uff.tank.seraphine.Batalha;
import uff.tank.seraphine.GerenciadorDados;
import uff.tank.seraphine.LiderGin;
import uff.tank.seraphine.Pokemon;
import java.io.IOException;
import java.util.List;

public class TelaConfirmarBatalha extends Tela {
    private LiderGin oponente;

    public TelaConfirmarBatalha(TelaContext context, LiderGin oponente) {
        super(context);
        this.oponente = oponente;
    }

    @Override
    public void mostrarTela() {
        // Carrega os dados do Pokémon do líder
        try {
            GerenciadorDados gerenciador = new GerenciadorDados();
            List<Pokemon> pokemonsDisponiveis = gerenciador.carregarPokemonsDisponiveis();
            oponente.carregarPokemon(pokemonsDisponiveis);
        } catch (IOException e) {
            System.out.println("Erro ao carregar dados dos Pokémon: " + e.getMessage());
            this.trocarTela(new TelaEscolherGinasio(this.contexto)); // Volta em caso de erro
            return;
        }

        // **A CORREÇÃO ESTÁ AQUI**
        // Garante que o pokemon do oponente também tenha seus movimentos inicializados.
        if (oponente.getPokemonAtual() != null) {
            oponente.getPokemonAtual().inicializarMovimentos();
        } else {
            System.out.println("Erro: O Pokémon do oponente não pôde ser carregado. Verifique o nome no arquivo lideres.json.");
            this.trocarTela(new TelaEscolherGinasio(this.contexto));
            return;
        }

        // Exibe a mensagem de desafio dinâmica
        System.out.println("Saudações, " + this.contexto.getTreinador().getNome() + "!");
        System.out.println("Você chegou ao ginásio de " + oponente.getRegiao() + "!");
        System.out.println("O líder é " + oponente.getNome() + ", com seu astucioso " + oponente.getPokemonAtual().getNome() + ".");
        System.out.println("Tem certeza que consegue enfrentá-lo?\n");
        System.out.println("1 - Claro! Manda ver!");
        System.out.println("2 - Eh... Não tenho certeza, acho que vou voltar.\n");

        String escolha = this.contexto.getUserInput();

        switch (escolha) {
            case "1":
                // Cria a batalha e avança para a TelaBatalha
                Batalha batalha = new Batalha(this.contexto.getTreinador().getPokemonAtual(), oponente.getPokemonAtual());
                this.trocarTela(new TelaBatalha(this.contexto, oponente, batalha));
                break;
            case "2":
                this.trocarTela(new TelaEscolherGinasio(this.contexto));
                break;
            default:
                System.out.println("Por favor, insira um valor válido!");
                break;
        }
    }
}