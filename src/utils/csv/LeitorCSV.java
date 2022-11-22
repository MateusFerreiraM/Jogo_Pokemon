package utils.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class LeitorCSV {
    // TODO: Criar metodos para leitura do CSV
    public static HashMap<String, String> getPokemonById(String filePath, int id) throws FileNotFoundException {
        HashMap<String, String> item = new HashMap<String, String>();

        Scanner scan = new Scanner(new File(filePath));// Permite que a gente leia o CSV
        scan.useDelimiter(",");
        boolean found = false;
        while (scan.hasNext()) { // Compara o id linha a linha
            String fileId = scan.next();
            if (fileId == String.valueOf(id)) {
                found = true;
                item.put("id", fileId);
                item.put(filePath, fileId);
            } else {
                scan.nextLine();
            }
        }
        scan.close();

        return item;
    }
}