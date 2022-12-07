package uff.tank.seraphine;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

import org.json.*;


public class CadastroTreinador{
    public static void main(String[] args) {
        /*
        // Salvar treinador no arquivo
        FileWriter writeFile = null;
        JSONObject obetoJson = new JSONObject();
    
        obetoJson.put("id", "01");
        obetoJson.put("nome", "Mateus");
        obetoJson.put("regiao", "Guadalupe");
    
        try {
            writeFile = new FileWriter("assets/dados.json");
            writeFile.write(obetoJson.toJSONString());
            writeFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        System.out.println(obetoJson.toJSONString());
         */
        
        // Ler treinador do arquivo
         /* 
        JSONObject obetoJson;
        JSONParser parser = new JSONParser();

        Treinador treinador = new Treinador();

        try{

            obetoJson = (JSONObject) parser.parse(new FileReader("assets/dados.json"));

            treinador.setNome((String) obetoJson.get("nome"));
            treinador.setId((String) obetoJson.get("id"));
            treinador.setRegiao((String) obetoJson.get("regiao"));

            System.out.println("Treinador criado = " + treinador.toString());

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }catch (ParseException e) {
            e.printStackTrace();
        }
        */
    }
}
