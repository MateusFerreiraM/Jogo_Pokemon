package jogo_pokemon.telas;

//Menu pós login/criação de treinador

public class TelaMenuPrincipal extends Tela {

    public void mostrarTela() {
        System.out.println("---------- Menu Principal ----------\n");
        System.out.println("Olá, " + this.contexto.getTreinador().getNome() + "!\n");
        System.out.println("1 - Selecionar Pokémon atual");
        System.out.println("2 - Abrir Pokédex");
        System.out.println("3 - Batalhar Ginásio");
        System.out.println("X - Sair");

        String escolha = this.contexto.getUserInput();

        switch (escolha) {
            case "1":
                this.trocarTela(new TelaSelecionarPokemon(this.contexto));
                break;

            case "2":
                this.trocarTela(new TelaPokedex(this.contexto));
                break;

            case "3":
                this.trocarTela(new TelaEscolherGinasio(this.contexto));
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

    public TelaMenuPrincipal(TelaContext context) {
        super(context);
    }
}
