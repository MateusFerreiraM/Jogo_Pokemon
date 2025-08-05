package jogo_pokemon.telas;

import jogo_pokemon.Treinador;

public class TelaIdentidade extends Tela {

    public TelaIdentidade(TelaContext context) {
        super(context);
    }

    @Override
    public void mostrarTela() {
        // Obtém o objeto Treinador diretamente do contexto. Não é preciso ler o ficheiro!
        Treinador jogador = this.contexto.getTreinador();

        System.out.println("---------- Identidade ----------\n");
        // Usa os métodos "get" do objeto Treinador que já temos em memória
        System.out.println("Nome: " + jogador.getNome());
        System.out.println("Regiao: " + jogador.getRegiao());
        System.out.println("ID: " + jogador.getId());
        System.out.println("Número de Pokémon obtidos: " + jogador.getPokemons().size());
        System.out.println("\nV - Voltar à pokédex");
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
}