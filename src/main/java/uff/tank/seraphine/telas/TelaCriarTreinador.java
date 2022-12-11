package uff.tank.seraphine.telas;

<<<<<<< HEAD
import org.json.simple.JSONObject;

import uff.tank.seraphine.Treinador;
import uff.tank.seraphine.utils.ConsoleUtils;
import uff.tank.seraphine.utils.JSONUtils;
=======
import uff.tank.seraphine.Treinador;
>>>>>>> 7c396a327dedc009bd40a3c5838b40055d949381

public class TelaCriarTreinador extends Tela {
    @Override
    public void mostrarTela() {
<<<<<<< HEAD
        JSONObject obetoJson = new JSONObject();

=======
>>>>>>> 7c396a327dedc009bd40a3c5838b40055d949381
        System.out.println("---------- Tela Cadastro ----------\n");

        System.out.print("Nome Treinador: ");

        String nome = this.contexto.getUserInput();

<<<<<<< HEAD
        if ((nome.toLowerCase())
                .equals((JSONUtils.getObjectByName(nome.toLowerCase(), "assets/dados.json").get("Nome").toString())
                        .toLowerCase())) {
            System.out.print("Treinador já existente!");
            this.trocarTela(new TelaCriarTreinador(this.contexto));
        }

=======
>>>>>>> 7c396a327dedc009bd40a3c5838b40055d949381
        System.out.print("Região: ");

        String regiao = this.contexto.getUserInput();

        Treinador novoTreinador = new Treinador(nome, regiao);
<<<<<<< HEAD

=======
>>>>>>> 7c396a327dedc009bd40a3c5838b40055d949381
        this.contexto.setTreinador(novoTreinador);

        this.trocarTela(new TelaPrimeiraEscolha(this.contexto));
    }

    public TelaCriarTreinador(TelaContext context) {
        super(context);
    }
}
