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

    // MODIFICADO: Os caminhos agora não são finais para permitir a alteração nos testes.
    private String pokemonDataPath = "assets/pokemon.json";
    private String lideresDataPath = "assets/lideres.json";
    private String saveDataPath = "assets/dados.json";

    private final ObjectMapper objectMapper;

    /**
     * Construtor padrão para uso na aplicação principal.
     */
    public GerenciadorDados() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * NOVO: Construtor para uso exclusivo em testes.
     * Permite definir caminhos de ficheiros personalizados para não afetar os dados reais.
     * @param pokemonPath Caminho para o ficheiro de pokemons de teste.
     * @param lideresPath Caminho para o ficheiro de líderes de teste.
     * @param savePath Caminho para o ficheiro de gravação de teste.
     */
    public GerenciadorDados(String pokemonPath, String lideresPath, String savePath) {
        this(); // Chama o construtor padrão para inicializar o objectMapper
        this.pokemonDataPath = pokemonPath;
        this.lideresDataPath = lideresPath;
        this.saveDataPath = savePath;
    }

    public List<Pokemon> carregarPokemonsDisponiveis() throws IOException {
        File arquivo = new File(pokemonDataPath);
        if (!arquivo.exists()) {
            throw new IOException("Ficheiro de dados não encontrado em: " + pokemonDataPath);
        }
        return objectMapper.readValue(new FileReader(arquivo), new TypeReference<List<Pokemon>>() {});
    }

    public List<LiderGin> carregarLideres() throws IOException {
        File arquivo = new File(lideresDataPath);
        if (!arquivo.exists()) {
            throw new IOException("Ficheiro de dados não encontrado em: " + lideresDataPath);
        }
        return objectMapper.readValue(new FileReader(arquivo), new TypeReference<List<LiderGin>>() {});
    }

    public List<Treinador> carregarTreinadores() throws IOException {
        File arquivoDeDados = new File(saveDataPath);
        if (!arquivoDeDados.exists() || arquivoDeDados.length() == 0) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(new FileReader(arquivoDeDados), new TypeReference<List<Treinador>>() {});
    }

    public void salvarTreinadores(List<Treinador> treinadores) throws IOException {
        File arquivoDeDados = new File(saveDataPath);
        File pastaAssets = arquivoDeDados.getParentFile();
        if (pastaAssets != null && !pastaAssets.exists()) {
            pastaAssets.mkdirs();
        }
        objectMapper.writeValue(arquivoDeDados, treinadores);
    }
}