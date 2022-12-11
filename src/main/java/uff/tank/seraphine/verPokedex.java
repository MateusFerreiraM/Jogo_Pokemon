package uff.tank.seraphine;

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

public class verPokedex {

    public static JSONObject mostrarPokedex() {
        // Lê treinador do arquivo
        JSONParser parser = new JSONParser();
        JSONObject obj = new JSONObject();
        try {

            obj = (JSONObject) parser.parse(new FileReader("assets/dados.json"));
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

        return obj;
    }

}
