package jogo_pokemon.telas;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import jogo_pokemon.GerenciadorDados;
import jogo_pokemon.Pokemon;
import jogo_pokemon.Treinador;
import jogo_pokemon.utils.ConsoleUtils;

public class TelaSelecionaTreinador extends Tela {

    private final GerenciadorDados gerenciador;

    public TelaSelecionaTreinador(TelaContext contexto) {
        super(contexto);
        this.gerenciador = new GerenciadorDados();
    }

    @Override
    public void mostrarTela() {
        try {
            List<Treinador> treinadores = gerenciador.carregarTreinadores();

            System.out.println("---------- Selecionar Treinador ----------\n");

            if (treinadores.isEmpty()) {
                System.out.println("Nenhum treinador cadastrado ainda.");
                System.out.println("Voltando ao menu inicial...");
                ConsoleUtils.sleep(2000);
                this.trocarTela(new TelaInicial(this.contexto));
                return;
            }

            System.out.println("Lista de Treinadores:");
            for (Treinador treinador : treinadores) {
                System.out.println(treinador.getId() + " - " + treinador.getNome());
            }

            System.out.println("\nDigite o ID do treinador para continuar.");
            System.out.println("V - Voltar ao menu inicial");
            System.out.println("X - Sair");
            System.out.print("\n>");

            String escolha = this.contexto.getUserInput();

            if (escolha.equalsIgnoreCase("X")) {
                this.contexto.sairPrograma();
                return;
            }

            if (escolha.equalsIgnoreCase("V")) {
                this.trocarTela(new TelaInicial(this.contexto));
                return;
            }

            try {
                int idEscolhido = Integer.parseInt(escolha);

                Optional<Treinador> treinadorEscolhido = treinadores.stream()
                        .filter(t -> t.getId() == idEscolhido)
                        .findFirst();

                if (treinadorEscolhido.isPresent()) {
                    Treinador treinador = treinadorEscolhido.get();

                    // **A CORREÇÃO ESTÁ AQUI**
                    // Para cada Pokémon do treinador carregado, garantimos que os movimentos sejam inicializados.
                    for (Pokemon p : treinador.getPokemons()) {
                        p.inicializarMovimentos();
                    }

                    this.contexto.setTreinador(treinador);
                    System.out.println("\nBem-vindo de volta, " + treinador.getNome() + "!");
                    ConsoleUtils.sleep(2000);
                    this.trocarTela(new TelaMenuPrincipal(this.contexto));
                } else {
                    System.out.println("ID de treinador não encontrado.");
                    ConsoleUtils.sleep(1500);
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, insira um valor válido.");
                ConsoleUtils.sleep(1500);
            }

        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao carregar os dados dos treinadores: " + e.getMessage());
            ConsoleUtils.sleep(3000);
            this.trocarTela(new TelaInicial(this.contexto));
        }
    }
}