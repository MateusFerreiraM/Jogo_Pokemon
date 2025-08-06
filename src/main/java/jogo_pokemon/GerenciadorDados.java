package jogo_pokemon;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDados {

    // Caminhos para os ficheiros de recursos (apenas de leitura, dentro de src/main/resources)
    private static final String POKEMON_RESOURCE_PATH = "/assets/pokemon.json";
    private static final String LIDERES_RESOURCE_PATH = "/assets/lideres.json";

    // Caminho para o ficheiro de dados do utilizador (leitura e escrita, na raiz do projeto)
    private static final String SAVE_DATA_PATH = "assets/dados.json";

    private final ObjectMapper objectMapper;

    public GerenciadorDados() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    // --- MÉTODOS DE LEITURA DE RECURSOS ---

    private <T> List<T> carregarRecurso(String resourcePath, TypeReference<List<T>> typeReference) throws IOException {
        try (InputStream inputStream = GerenciadorDados.class.getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new IOException("Ficheiro de recurso não encontrado em: " + resourcePath);
            }
            return objectMapper.readValue(inputStream, typeReference);
        }
    }

    public List<Pokemon> carregarPokemonsDisponiveis() throws IOException {
        return carregarRecurso(POKEMON_RESOURCE_PATH, new TypeReference<List<Pokemon>>() {});
    }

    public List<LiderGin> carregarLideres() throws IOException {
        return carregarRecurso(LIDERES_RESOURCE_PATH, new TypeReference<List<LiderGin>>() {});
    }

    // --- MÉTODOS DE LEITURA E ESCRITA DOS DADOS DO UTILIZADOR (NA PASTA DO PROJETO) ---

    public List<Treinador> carregarTreinadores() throws IOException {
        File arquivoDeDados = new File(SAVE_DATA_PATH);

        if (!arquivoDeDados.exists() || arquivoDeDados.length() == 0) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(arquivoDeDados, new TypeReference<List<Treinador>>() {});
    }

    public void salvarTreinadores(List<Treinador> treinadores) throws IOException {
        File arquivoDeDados = new File(SAVE_DATA_PATH);
        // Garante que a pasta 'assets' na raiz do projeto existe
        File pastaAssets = arquivoDeDados.getParentFile();
        if (!pastaAssets.exists()) {
            pastaAssets.mkdirs();
        }
        objectMapper.writeValue(arquivoDeDados, treinadores);
    }
}