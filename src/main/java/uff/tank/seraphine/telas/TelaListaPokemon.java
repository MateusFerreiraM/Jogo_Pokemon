package uff.tank.seraphine.telas;

public class TelaListaPokemon extends Tela {
    @Override
    public void mostrarTela() {
        System.out.println("\n\n\n\n---------- Selecione seu Pokemon ----------");
        System.out.println("Lista de Pokemons: ");
        //TODO: Criar função que retorna a lista de todos os pokemon do usuário.
        //TODO: Criar função que adiciona paginação à essa lista
        System.out.println("V- Voltar à Pokédex");
        System.out.println("X- Sair");

        String escolha = this.contexto.getUserInput();

        switch (escolha){
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
    public TelaListaPokemon(TelaContext context){
        super(context);
    }
}
