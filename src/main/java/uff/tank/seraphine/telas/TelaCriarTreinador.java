package uff.tank.seraphine.telas;

import org.json.simple.JSONObject;

import uff.tank.seraphine.Treinador;
import uff.tank.seraphine.utils.ConsoleUtils;
import uff.tank.seraphine.utils.JSONUtils;
import uff.tank.seraphine.Treinador;

public class TelaCriarTreinador extends Tela {
        @Override
        public void mostrarTela() {
                JSONObject obetoJson = new JSONObject();

                System.out.println("---------- Tela Cadastro ----------\n");

                System.out.print("Nome Treinador: ");

                String nome = this.contexto.getUserInput();

                boolean treinadorExiste = false;

                try{
                        treinadorExiste = nome.toLowerCase().equals((JSONUtils.getObjectByName(
                                nome.toLowerCase(), "assets/dados.json"
                        ).get("Nome").toString()).toLowerCase());
                } catch(Exception e){
                        treinadorExiste = false;
                }

                if (treinadorExiste) {
                        System.out.println("\nTreinador já existente!\nPor favor, digite outro nome.");
                        try {
                                Thread.sleep(2000);
                        } catch (InterruptedException ex) {
                        }
                        return;
                }

                System.out.print("Região: ");

                String regiao = this.contexto.getUserInput();

                Treinador novoTreinador = new Treinador(nome, regiao);
                this.contexto.setTreinador(novoTreinador);

                this.trocarTela(new TelaPrimeiraEscolha(this.contexto));
        }

        public TelaCriarTreinador(TelaContext context) {
                super(context);
        }
}
