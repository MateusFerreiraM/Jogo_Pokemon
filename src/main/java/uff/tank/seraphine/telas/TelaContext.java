package uff.tank.seraphine.telas;

//Essa é a classe responsável por mostrar a tela
//A estrutura das telas segue o padrão de design chamado states: https://refactoring.guru/design-patterns/state
//Essa tela serve como contexto
public class TelaContext {
    Tela telaAtual;

    public void atualizarTela(Tela novaTela){
        this.telaAtual = novaTela;
    }

    public void mostrarTela(){
        telaAtual.mostrarTela();
    }
}
