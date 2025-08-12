package jogo_pokemon.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jogo_pokemon.model.LiderGin;
import jogo_pokemon.model.Pokemon;
import jogo_pokemon.model.Treinador;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por toda a leitura e escrita de ficheiros de dados (JSON).
 * Centraliza o acesso aos dados do jogo, abstraindo a manipulação de ficheiros.
 */
public class GerenciadorDados {

    private static final String POKEMON_DATA_PATH = "assets/pokemon.json";
    private static final String LIDERES_DATA_PATH = "assets/lideres.json";
    private static final String SAVE_DATA_PATH = "assets/dados.json";

    /**
     * Objeto da biblioteca Jackson para serializar e deserializar JSON.
     */
    private final ObjectMapper objectMapper;

    /**
     * Construtor do GerenciadorDados.
     * Inicializa o ObjectMapper e configura-o para formatar o JSON de saída (pretty-print).
     */
    public GerenciadorDados() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * Carrega a lista de todos os Pokémon disponíveis a partir do ficheiro pokemon.json.
     * @return Uma lista de objetos Pokemon.
     * @throws IOException se o ficheiro não for encontrado ou ocorrer um erro de leitura.
     */
    public List<Pokemon> carregarPokemonsDisponiveis() throws IOException {
        File arquivo = new File(POKEMON_DATA_PATH);
        if (!arquivo.exists()) {
            throw new IOException("Ficheiro de dados não encontrado em: " + POKEMON_DATA_PATH);
        }
        return objectMapper.readValue(new FileReader(arquivo), new TypeReference<List<Pokemon>>() {});
    }

    /**
     * Carrega a lista de todos os Líderes de Ginásio a partir do ficheiro lideres.json.
     * @return Uma lista de objetos LiderGin.
     * @throws IOException se o ficheiro não for encontrado ou ocorrer um erro de leitura.
     */
    public List<LiderGin> carregarLideres() throws IOException {
        File arquivo = new File(LIDERES_DATA_PATH);
        if (!arquivo.exists()) {
            throw new IOException("Ficheiro de dados não encontrado em: " + LIDERES_DATA_PATH);
        }
        return objectMapper.readValue(new FileReader(arquivo), new TypeReference<List<LiderGin>>() {});
    }

    /**
     * Carrega a lista de treinadores guardados a partir do ficheiro dados.json.
     * Se o ficheiro não existir ou estiver vazio, retorna uma lista nova e vazia.
     * @return Uma lista de objetos Treinador.
     * @throws IOException se ocorrer um erro de leitura.
     */
    public List<Treinador> carregarTreinadores() throws IOException {
        File arquivoDeDados = new File(SAVE_DATA_PATH);
        if (!arquivoDeDados.exists() || arquivoDeDados.length() == 0) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(new FileReader(arquivoDeDados), new TypeReference<List<Treinador>>() {});
    }

    /**
     * Guarda (serializa) a lista de treinadores para o ficheiro dados.json.
     * Cria a pasta 'assets' se ela não existir.
     * @param treinadores A lista de treinadores a ser guardada.
     * @throws IOException se ocorrer um erro de escrita no ficheiro.
     */
    public void salvarTreinadores(List<Treinador> treinadores) throws IOException {
        File arquivoDeDados = new File(SAVE_DATA_PATH);
        File pastaAssets = arquivoDeDados.getParentFile();
        if (!pastaAssets.exists()) {
            pastaAssets.mkdirs();
        }
        objectMapper.writeValue(arquivoDeDados, treinadores);
    }
}