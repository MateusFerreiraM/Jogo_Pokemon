package uff.tank.seraphine.telas;

import uff.tank.seraphine.Treinador;
import uff.tank.seraphine.utils.ConsoleUtils;

import java.util.Scanner;

//Essa é a classe responsável por mostrar a tela e receber Inputs
//A estrutura das telas segue o padrão de design chamado states: https://refactoring.guru/design-patterns/state
//Essa tela serve como contexto.

//TODO: Delegar o recebimento do input a outra classe?
public class TelaContext {
    private Tela telaAtual;

    private Treinador usuario;

    private boolean rodando;

    private Scanner input;

    public void atualizarTela(Tela novaTela){
        this.telaAtual = novaTela;
    }

    public void mostrarTela(){
        ConsoleUtils.clearConsole();
        telaAtual.mostrarTela();
    }

    public Treinador getTreinador(){
        return this.usuario;
    }

    void setTreinador(Treinador treinador){
        this.usuario = treinador;
    }

    public TelaContext(Scanner input){
        //Por padrão, um novo contexto é criado com a tela inicial
        this.usuario = null;
        this.telaAtual = new TelaInicial(this);
        this.rodando = true;
        this.input = input;
    }

    public TelaContext(Tela tela, Scanner input){
        //Caso seja necessário criar com uma tela diferente
        this.usuario = null;
        this.telaAtual = tela;
        this.rodando = true;
        this.input = input;
    }

    public boolean isRodando(){
        return this.rodando;
    }

    void sairPrograma(){
        this.rodando = false;
    }

    Scanner getInput(){
        return this.input;
    }

}
