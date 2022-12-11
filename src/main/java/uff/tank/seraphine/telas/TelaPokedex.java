package uff.tank.seraphine.telas;

public class TelaPokedex extends Tela {
    @Override
    public void mostrarTela() {
        System.out.println("---------- Pokedex ----------\n");
        System.out.println("1 - Ver Pokémon");
        System.out.println("2 - Ver Informações do Treinador");
        System.out.println("V - Voltar ao menu principal");
        System.out.println("X - Sair");

        String escolha = this.contexto.getUserInput();

        switch (escolha) {
            case "1":
                this.trocarTela(new TelaListaPokemon(this.contexto));
                break;
            case "2":
                this.trocarTela(new TelaIdentidade(this.contexto));
                break;
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
                break;
        }
    }

    public TelaPokedex(TelaContext context) {
        super(context);
    }
}
