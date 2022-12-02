package uff.tank.seraphine.telas;

import uff.tank.seraphine.telas.Tela;
import uff.tank.seraphine.telas.TelaContext;

import java.util.Scanner;

public class TelaSelecionaTreinador extends Tela {
    static void mudaTela(){
        boolean naTela = true;
        Scanner teclado = new Scanner(System.in);
        while (naTela){
            //TODO: Seleção de treinador
            naTela = false;
        }
        teclado.close();
    }

    public TelaSelecionaTreinador(TelaContext contexto){
        super(contexto);
    }
}
