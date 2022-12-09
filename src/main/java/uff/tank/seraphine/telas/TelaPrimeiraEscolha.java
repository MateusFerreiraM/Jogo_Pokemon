package uff.tank.seraphine.telas;

public class TelaPrimeiraEscolha extends Tela {
    @Override
    public void mostrarTela() {
        System.out
                .println(
                        "!!!INCOMPLETO, PRECISA ADICIONAR O POKEMON ESCOLHIDO NA POKEDEX DO TREINADOR E NO ARQUIVO JSON!!!\n");
        System.out.println("Olá, " + this.contexto.getTreinador().getNome()
                + "! Seja bem vindo(a) ao centro de escolha Pokémon!\nEscolha um dos Pokemons disponíveis em nosso laboratório para iniciar sua jornada.\n");
        System.out.println("1 - Charmander");
        System.out.println("2 - Squirtle");
        System.out.println("3 - Bulbasaur");
        System.out.println("4 - Pikachu\n");
        System.out.println("X - Sair");
        String escolha = contexto.getUserInput();

        switch (escolha) {
            case "1": // charmander

                this.trocarTela(new TelaMenuPrincipal(this.contexto));
                break;
            case "2": // squirtle

                this.trocarTela(new TelaMenuPrincipal(this.contexto));
                break;
            case "3": // bulbasaur

                this.trocarTela(new TelaMenuPrincipal(this.contexto));
                break;
            case "4": // pikachu

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

    public TelaPrimeiraEscolha(TelaContext context) {
        super(context);
    }
}
