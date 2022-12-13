package uff.tank.seraphine;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class verPokedex {

    public static JSONObject mostrarPokedex() {
        // Lê treinador do arquivo
        JSONParser parser = new JSONParser();
        JSONObject obj = new JSONObject();
        try {

            obj = (JSONObject) parser.parse(new FileReader("assets/dados.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return obj;
    }

}
