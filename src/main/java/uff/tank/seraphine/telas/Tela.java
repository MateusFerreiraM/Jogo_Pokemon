package uff.tank.seraphine.telas;
//Classe abstrata que define as telas que o usuário vai interagir


import uff.tank.seraphine.Treinador;
import uff.tank.seraphine.utils.ConsoleUtils;

//A estrutura das telas segue o padrão de design chamado states: https://refactoring.guru/design-patterns/state
//Essa classe serve a função de "State" ou estado.
public abstract class Tela {
    TelaContext contexto;
    protected Treinador usuario;
    public void mostrarTela(){
        ConsoleUtils.clearConsole();//Sempre limpa a tela ao mostrar uma nova tela
    }
    public void trocarTela(Tela novaTela){
        contexto.telaAtual = novaTela;
    }

    public Tela(TelaContext contexto){
        this.contexto = contexto;
    }
}
