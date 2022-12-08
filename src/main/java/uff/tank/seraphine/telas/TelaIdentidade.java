package uff.tank.seraphine.telas;

public class TelaIdentidade extends Tela {
    @Override
    public void mostrarTela() {
        System.out.println("---------- Identidade ----------");
        System.out.println("Nome: " + this.contexto.getTreinador().getNome());
        System.out.println("Regiao: " + this.contexto.getTreinador().getRegiao());
        System.out.println("ID: " + this.contexto.getTreinador().getId());
        System.out.println("Número de Pokémon obtidos: " + this.contexto.getTreinador().qtdPokemon);
        System.out.println("V- Voltar à pokédex");
        System.out.println("X- Sair");

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

    public TelaIdentidade(TelaContext context) {
        super(context);
    }
}
