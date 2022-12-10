package uff.tank.seraphine;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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

            id = objArray.size() + 1;

            treinador.setIdAtual(id);

            obetoJson.put("Id", treinador.getId());
            obetoJson.put("nome", treinador.getNome());
            obetoJson.put("regiao", treinador.getRegiao());
            obetoJson.put("pokemons", treinador.getPokemons());
            obetoJson.put("pokemons", pkmnNomes);

            objArray.add(obetoJson);

            writeFile = new FileWriter("assets/dados.json");
            writeFile.write(objArray.toJSONString());
            writeFile.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        System.out.println(obetoJson.toJSONString());
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
