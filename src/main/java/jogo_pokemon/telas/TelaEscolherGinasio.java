package jogo_pokemon.telas;

import jogo_pokemon.GerenciadorDados;
import jogo_pokemon.LiderGin;
import jogo_pokemon.utils.ConsoleUtils;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class TelaEscolherGinasio extends Tela {

    private final GerenciadorDados gerenciador;

    public TelaEscolherGinasio(TelaContext context) {
        super(context);
        this.gerenciador = new GerenciadorDados();
    }

    @Override
    public void mostrarTela() {
        try {
            List<LiderGin> lideres = gerenciador.carregarLideres();

            System.out.println("---------- Ginásios Disponíveis ----------\n");
            lideres.forEach(lider -> 
                System.out.println(lider.getId() + " - Ginásio de " + lider.getRegiao() + " (Líder: " + lider.getNome() + ")")
            );

            System.out.println("\nDigite o ID do ginásio para desafiar.");
            System.out.println("V - Voltar ao menu principal");

            String escolha = contexto.getUserInput();

            if (escolha.equalsIgnoreCase("V")) {
                this.trocarTela(new TelaMenuPrincipal(this.contexto));
                return;
            }

            try {
                int idEscolhido = Integer.parseInt(escolha);
                Optional<LiderGin> liderEscolhido = lideres.stream()
                    .filter(l -> l.getId() == idEscolhido)
                    .findFirst();

                if (liderEscolhido.isPresent()) {
                    // Transiciona para a nova tela de confirmação genérica
                    this.trocarTela(new TelaConfirmarBatalha(this.contexto, liderEscolhido.get()));
                } else {
                    System.out.println("ID de ginásio não encontrado.");
                    ConsoleUtils.sleep(1500);
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, insira um valor válido.");
                ConsoleUtils.sleep(1500);
            }

        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao carregar os dados dos ginásios: " + e.getMessage());
            ConsoleUtils.sleep(3000);
            this.trocarTela(new TelaMenuPrincipal(this.contexto));
        }
    }
}