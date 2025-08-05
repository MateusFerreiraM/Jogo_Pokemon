package uff.tank.seraphine.telas;

import uff.tank.seraphine.GerenciadorDados;
import uff.tank.seraphine.Treinador;
import java.io.IOException;
import java.util.List;

public class TelaCriarTreinador extends Tela {

    private final GerenciadorDados gerenciador;

    public TelaCriarTreinador(TelaContext context) {
        super(context);
        this.gerenciador = new GerenciadorDados(); // Instancia o gerenciador
    }

    @Override
    public void mostrarTela() {
        System.out.println("---------- Tela Cadastro ----------\n");
        System.out.print("Nome Treinador: ");
        String nome = this.contexto.getUserInput();

        try {
            List<Treinador> todosOsTreinadores = gerenciador.carregarTreinadores();

            // Verifica se o nome já existe na lista carregada (muito mais rápido)
            boolean treinadorExiste = todosOsTreinadores.stream()
                    .anyMatch(t -> t.getNome().equalsIgnoreCase(nome));

            if (treinadorExiste) {
                System.out.println("\nTreinador já existente!\nPor favor, digite outro nome.");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) { /* Ignora */}
                return; // Volta para o menu anterior
            }

            System.out.print("Região: ");
            String regiao = this.contexto.getUserInput();

            // Calcula o próximo ID disponível
            int novoId = todosOsTreinadores.stream()
                    .mapToInt(Treinador::getId)
                    .max()
                    .orElse(0) + 1;

            // Cria o objeto Treinador, mas AINDA NÃO SALVA NO ARQUIVO
            Treinador novoTreinador = new Treinador(nome, regiao, novoId);
            this.contexto.setTreinador(novoTreinador);

            // Transiciona para a tela de escolha do primeiro Pokémon
            this.trocarTela(new TelaPrimeiraEscolha(this.contexto));

        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao acessar os dados dos treinadores: " + e.getMessage());
            // Pausa para o usuário ler o erro
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) { /* Ignora */}
        }
    }
}