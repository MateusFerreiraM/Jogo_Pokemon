package uff.tank.seraphine.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import uff.tank.seraphine.Tipos;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class JSONUtils {
<<<<<<< HEAD
    public static JSONObject getObjectByID(int id, String filePath) {
        // Dado uma Id e o caminho do arquivo, obtem um objeto do arquivo JSON
        JSONParser parser = new JSONParser();
        JSONObject obj = new JSONObject();
        JSONArray objArray = null;

        try {
            objArray = (JSONArray) parser.parse(new FileReader(filePath));

            for (Object i : objArray) {
                obj = (JSONObject) i;
                int objId = Integer.parseInt(obj.get("Id").toString());
                if (objId == id) {
                    break;
                }
            }
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

    public static JSONObject getObjectByName(String nome, String filePath) {
        // Dado um nome e o caminho do arquivo, obtem um objeto do arquivo JSON
=======
    public static JSONObject getObjectByID(int id, String filePath){
            //Dado uma Id e o caminho do arquivo, obtem um objeto do arquivo JSON
            JSONParser parser = new JSONParser();
            JSONObject obj = new JSONObject();
            JSONArray objArray = null;

            try {
                objArray = (JSONArray) parser.parse(new FileReader(filePath));

                for (Object i : objArray) {
                    obj = (JSONObject) i;
                    int objId = Integer.parseInt(obj.get("Id").toString());
                    if (objId == id) {
                        System.out.println(obj);
                        break;
                    }
                }
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

    public static JSONObject getObjectByName(String nome, String filePath){
        //Dado um nome e o caminho do arquivo, obtem um objeto do arquivo JSON
>>>>>>> 7c396a327dedc009bd40a3c5838b40055d949381
        JSONParser parser = new JSONParser();
        JSONObject obj = new JSONObject();
        JSONArray objArray = null;

        try {
            objArray = (JSONArray) parser.parse(new FileReader(filePath));

            for (Object i : objArray) {
                obj = (JSONObject) i;
                if (obj.get("Nome").toString().equals(nome)) {
<<<<<<< HEAD
=======
                    System.out.println(obj);
>>>>>>> 7c396a327dedc009bd40a3c5838b40055d949381
                    break;
                }
            }
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

<<<<<<< HEAD
    public static int getLastId(String filePath) {
=======
    public static int getLastId(String filePath){
>>>>>>> 7c396a327dedc009bd40a3c5838b40055d949381
        JSONParser parser = new JSONParser();
        JSONObject obj = new JSONObject();
        JSONArray objArray = null;
        int lastId = 0;
        try {
            objArray = (JSONArray) parser.parse(new FileReader(filePath));

            for (Object i : objArray) {
                obj = (JSONObject) i;
                int objId = Integer.parseInt(obj.get("Id").toString());
                if (objId > lastId) {
                    lastId = objId;
                }
            }
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
        return lastId;
    }

<<<<<<< HEAD
    public static int getTotalObjects(String filePath) {
=======

    public static int getTotalObjects(String filePath){
>>>>>>> 7c396a327dedc009bd40a3c5838b40055d949381
        JSONParser parser = new JSONParser();
        JSONObject obj = new JSONObject();
        JSONArray objArray = null;
        int count = 0;
        try {
            objArray = (JSONArray) parser.parse(new FileReader("assets/pokemon.json"));

            for (Object i : objArray) {
                count++;
            }
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
        return count;
    }
<<<<<<< HEAD

    public static Tipos tipoFromString(String value) {
        Tipos tipo = null;
        switch (value) {
=======
    public static Tipos tipoFromString(String value){
        Tipos tipo = null;
        switch (value){
>>>>>>> 7c396a327dedc009bd40a3c5838b40055d949381
            case "Agua":
                tipo = Tipos.AGUA;
                break;
            case "Aco":
                tipo = Tipos.ACO;
                break;
            case "Dragao":
                tipo = Tipos.DRAGAO;
                break;
            case "Eletrico":
                tipo = Tipos.ELETRICO;
                break;
            case "Fada":
                tipo = Tipos.FADA;
                break;
            case "Fantasma":
                tipo = Tipos.FANTASMA;
                break;
            case "Fogo":
                tipo = Tipos.FOGO;
                break;
            case "Gelo":
                tipo = Tipos.GELO;
                break;
            case "Grama":
                tipo = Tipos.GRAMA;
                break;
            case "Inseto":
                tipo = Tipos.INSETO;
                break;
            case "Lutador":
                tipo = Tipos.LUTADOR;
                break;
            case "Normal":
                tipo = Tipos.NORMAL;
                break;
            case "Pedra":
                tipo = Tipos.PEDRA;
                break;
            case "Psiquico":
                tipo = Tipos.PSIQUICO;
                break;
            case "Sombrio":
                tipo = Tipos.SOMBRIO;
                break;
            case "Terra":
                tipo = Tipos.TERRA;
                break;
            case "Voador":
                tipo = Tipos.VOADOR;
                break;
            case "Veneno":
                tipo = Tipos.VENENO;
                break;
        }
        return tipo;
    }
}
