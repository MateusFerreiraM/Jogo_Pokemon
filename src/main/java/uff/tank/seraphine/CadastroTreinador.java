package uff.tank.seraphine;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import uff.tank.seraphine.utils.JSONUtils;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.print.DocFlavor.STRING;
import org.json.*;

public class CadastroTreinador {

    public static void cadastrarTreinador(Treinador treinador) {
        // Salvar treinador no arquivo
        JSONParser parser = new JSONParser();
        FileWriter writeFile = null;
        JSONObject obetoJson = new JSONObject();
        JSONArray objArray = null;
        int id;

        ArrayList<String> pkmnNomes = new ArrayList<String>();
        for (Pokemon pkmn : treinador.getPokemons()) {
            pkmnNomes.add(pkmn.getNome());
        }

        try {
            objArray = (JSONArray) parser.parse(new FileReader("assets/dados.json"));

            obetoJson.put("Id", treinador.getId());
            obetoJson.put("Nome", treinador.getNome());
            obetoJson.put("Regiao", treinador.getRegiao());
            // obetoJson.put("Pokemons", treinador.getPokemons());
            obetoJson.put("Pokemons", pkmnNomes);

            objArray.add(obetoJson);

            writeFile = new FileWriter("assets/dados.json");
            writeFile.write(objArray.toJSONString());
            writeFile.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static void atualizarTreinador(Treinador treinador) {
        // Salvar treinador no arquivo
        JSONParser parser = new JSONParser();
        FileWriter writeFile = null;
        JSONArray objArray = null;

        try {
            objArray = (JSONArray) parser.parse(new FileReader("assets/dados.json"));

            for (Object obj : objArray) {
                JSONObject i = (JSONObject) obj;
                int jsonId = Integer.parseInt(i.get("Id").toString());
                if (jsonId == treinador.getId()) {
                    ArrayList<String> pkmnNomes = new ArrayList<String>();
                    for (Pokemon pkmn : treinador.getPokemons()) {
                        pkmnNomes.add(pkmn.getNome());
                    }
                    i.put("Pokemons", pkmnNomes);
                    break;
                }
            }

            writeFile = new FileWriter("assets/dados.json");
            writeFile.write(objArray.toJSONString());
            writeFile.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }

    public static JSONArray lerTreinador() {
        // Lê treinador do arquivo
        JSONParser parser = new JSONParser();
        JSONArray objArray = null;
        try {

            objArray = (JSONArray) parser.parse(new FileReader("assets/dados.json"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return objArray;
    }
}
