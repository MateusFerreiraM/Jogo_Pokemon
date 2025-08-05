package uff.tank.seraphine;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDados {

    private static final String DADOS_TREINADORES = "assets/dados.json";
    private final ObjectMapper objectMapper;

    public GerenciadorDados() {
        this.objectMapper = new ObjectMapper();
        // Configura o Jackson para 'pretty print' o JSON, facilitando a leitura
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * Carrega a lista de treinadores do arquivo dados.json.
     * @return Uma lista de Treinadores. Se o arquivo não existir, retorna uma lista vazia.
     */
    public List<Treinador> carregarTreinadores() throws IOException {
        File arquivo = new File(DADOS_TREINADORES);
        if (!arquivo.exists() || arquivo.length() == 0) {
            return new ArrayList<>(); // Retorna lista vazia se não existir ou estiver vazio
        }
        // Converte o array de JSON diretamente para uma Lista de Treinadores
        return objectMapper.readValue(arquivo, new TypeReference<List<Treinador>>() {});
    }

    /**
     * Salva a lista completa de treinadores no arquivo dados.json, sobrescrevendo o conteúdo.
     * @param treinadores A lista de treinadores a ser salva.
     */
    public void salvarTreinadores(List<Treinador> treinadores) throws IOException {
        // Converte a lista de objetos Java para JSON e salva no arquivo
        objectMapper.writeValue(new File(DADOS_TREINADORES), treinadores);
    }

    private static final String POKEMON_PATH = "assets/pokemon.json";

    /**
     * Carrega a lista de todos os Pokémons disponíveis do arquivo pokemon.json.
     * @return Uma lista de Pokémons.
     */
    public List<Pokemon> carregarPokemonsDisponiveis() throws IOException {
        File arquivo = new File(POKEMON_PATH);
        if (!arquivo.exists()) {
            throw new IOException("Arquivo de Pokémons não encontrado em: " + POKEMON_PATH);
        }
        return objectMapper.readValue(arquivo, new TypeReference<List<Pokemon>>() {});
    }

    private static final String LIDERES_PATH = "assets/lideres.json";

    /**
     * Carrega a lista de todos os Líderes de Ginásio do arquivo lideres.json.
     * @return Uma lista de LiderGin.
     */
    public List<LiderGin> carregarLideres() throws IOException {
        File arquivo = new File(LIDERES_PATH);
        if (!arquivo.exists()) {
            throw new IOException("Arquivo de Líderes não encontrado em: " + LIDERES_PATH);
        }
        return objectMapper.readValue(arquivo, new TypeReference<List<LiderGin>>() {});
    }

}