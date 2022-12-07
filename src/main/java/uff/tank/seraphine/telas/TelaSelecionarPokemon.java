package uff.tank.seraphine.telas;

public class TelaSelecionarPokemon extends Tela{
    @Override
    public void mostrarTela(){
        System.out.println("=====Selecionar Pokemon=====");
        //TODO: Metodo para mostrar pokemon disponiveis para troca
        //TODO: Troca de pokemon
        System.out.println("INCOMPLETO");
        System.out.println("V- Voltar ao menu principal");
        System.out.println("X- Sair");

        String escolha = this.contexto.getUserInput();

        switch (escolha){
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

    public TelaSelecionarPokemon(TelaContext context){
        super(context);
    }
}
