package uff.tank.seraphine;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;
import javax.print.DocFlavor.STRING;
import org.json.*;

public class CadastroTreinador{
   
    public static void cadastrarTreinador(Treinador treinador){
        // Salvar treinador no arquivo
        FileWriter writeFile = null;
        JSONObject obetoJson = new JSONObject();

        obetoJson.put("id", treinador.getId());
        obetoJson.put("nome", treinador.getNome());
        obetoJson.put("regiao", treinador.getRegiao());

        try {
            writeFile = new FileWriter("assets/dados.json");
            writeFile.write(obetoJson.toJSONString());
            writeFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(obetoJson.toJSONString());        
    }
    
    public void lerTreinador(){
        // Lê treinador do arquivo  
        JSONObject obetoJson;
        JSONParser parser = new JSONParser();

        try{

            obetoJson = (JSONObject) parser.parse(new FileReader("assets/dados.json"));
            int id = Integer.parseInt((String) obetoJson.get("id"));
            Treinador treinador = new Treinador((String) obetoJson.get("nome"), (String) obetoJson.get("regiao"), id);

            System.out.println("Treinador criado = " + treinador.toString());

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
