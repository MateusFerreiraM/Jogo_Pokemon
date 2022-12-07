package uff.tank.seraphine.telas;
import java.util.Scanner;

public class TelaSelecionaTreinador extends Tela {
    @Override
    public void mostrarTela(){
        System.out.println("===== Selecionar Treinador =====");
        //TODO: Exibir lista treinadores
        System.out.println("X- Sair");
    }

    public TelaSelecionaTreinador(TelaContext contexto){
        super(contexto);
    }
}
