package jogo_pokemon.telas;
//Classe abstrata que define as telas que o usuário vai interagir

//A estrutura das telas segue o padrão de design chamado states: https://refactoring.guru/design-patterns/state
//Essa classe serve a função de "State" ou estado.
public abstract class Tela {
    TelaContext contexto;

    public void mostrarTela() {
        // Toda tela impementa essa função
    }

    public void trocarTela(Tela novaTela) {
        this.contexto.atualizarTela(novaTela);
    }

    public Tela(TelaContext contexto) {
        this.contexto = contexto;
    }
}
