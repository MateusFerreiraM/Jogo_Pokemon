package uff.tank.seraphine.telas;

import java.io.FileReader;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import uff.tank.seraphine.Treinador;
import uff.tank.seraphine.utils.JSONUtils;

public class TelaSelecionaTreinador extends Tela {
    @Override
    public void mostrarTela() {
        JSONArray objArray = null;
        JSONParser parser = new JSONParser();
        JSONObject obj = new JSONObject();

        System.out.println("---------- Selecionar Treinador ----------\n");
        System.out.println("Lista de Treinadores (pelo ID): ");


        try {
            objArray = (JSONArray) parser.parse(new FileReader("assets/dados.json"));

            for (Object i : objArray) {
                obj = (JSONObject) i;
                //System.out.println(obj.toString());
                System.out.println(
                        obj.get("Id").toString() + " - " + obj.get("nome").toString()
                );
            }

            System.out.println("\nV - Voltar ao menu principal");
            System.out.println("X - Sair");

            System.out.print(">");

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-3);
            // TODO: handle exception
        }

        String escolha = this.contexto.getUserInput();
        boolean isNumber = false;
        String idEscolhido;

        if (escolha != "x" && escolha != "v") {
            idEscolhido = escolha;
        }

        try {
            //Tenta ver se o input é um número que pode ser Id
            //Se o parse falhar, a string contém letras, logo, não é um ID
            Integer.parseInt(escolha);
            isNumber = true;
        } catch (NumberFormatException e){
            isNumber = false;
        }
        if(isNumber){
            this.contexto.setTreinador(
                    Treinador.getTreinadorFromJSONObject(
                            JSONUtils.getObjectByID(Integer.parseInt(escolha), "assets/dados.json")
                    )
            );
            this.trocarTela(new TelaMenuPrincipal(this.contexto));
        } else{
            switch (escolha) {
                case "A":
                    this.trocarTela(new TelaMenuPrincipal(this.contexto));
                    break;
                case "v":
                case "V":
                    this.trocarTela(new TelaInicial(this.contexto));
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
    }

    public static void passarParaInt(String id) {
        int idInt = Integer.valueOf(id);
        // Treinador.setIdAtual(idInt);
    }

    public TelaSelecionaTreinador(TelaContext contexto) {
        super(contexto);
    }
}
