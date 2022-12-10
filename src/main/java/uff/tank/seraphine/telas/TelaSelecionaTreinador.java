package uff.tank.seraphine.telas;

import java.io.FileReader;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TelaSelecionaTreinador extends Tela {
    @Override
    public void mostrarTela() {
        JSONArray objArray = null;
        JSONParser parser = new JSONParser();
        JSONObject obj = new JSONObject();

        try {

            System.out.println("---------- Selecionar Treinador ----------\n");
            System.out.println("Lista de Treinadores (pelo ID): ");

            objArray = (JSONArray) parser.parse(new FileReader("assets/dados"));

            for (Object i : objArray) {
                obj = (JSONObject) i;
                System.out.println(obj.toString());
            }

            System.out.println("\nV - Voltar ao menu principal");
            System.out.println("X - Sair");

        } catch (Exception e) {
            // TODO: handle exception
        }

        String escolha = this.contexto.getUserInput();
        String idEscolhido;

        if (escolha != "x" && escolha != "v") {
            idEscolhido = escolha;
        }
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

    public static void passarParaInt(String id) {
        int idInt = Integer.valueOf(id);
        // Treinador.setIdAtual(idInt);
    }

    public TelaSelecionaTreinador(TelaContext contexto) {
        super(contexto);
    }
}
