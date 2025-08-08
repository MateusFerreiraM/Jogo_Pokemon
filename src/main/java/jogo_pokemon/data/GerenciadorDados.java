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
 * Centraliza o acesso aos dados do jogo.
 */
public class GerenciadorDados {

    // Caminhos para TODOS os ficheiros de dados, agora na pasta 'assets'
    private static final String POKEMON_DATA_PATH = "assets/pokemon.json";
    private static final String LIDERES_DATA_PATH = "assets/lideres.json";
    private static final String SAVE_DATA_PATH = "assets/dados.json";

    private final ObjectMapper objectMapper;

    public GerenciadorDados() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    // --- MÉTODOS DE LEITURA (AGORA TODOS IGUAIS) ---

    public List<Pokemon> carregarPokemonsDisponiveis() throws IOException {
        File arquivo = new File(POKEMON_DATA_PATH);
        if (!arquivo.exists()) {
            throw new IOException("Ficheiro de dados não encontrado em: " + POKEMON_DATA_PATH);
        }
        return objectMapper.readValue(new FileReader(arquivo), new TypeReference<List<Pokemon>>() {});
    }

    public List<LiderGin> carregarLideres() throws IOException {
        File arquivo = new File(LIDERES_DATA_PATH);
        if (!arquivo.exists()) {
            throw new IOException("Ficheiro de dados não encontrado em: " + LIDERES_DATA_PATH);
        }
        return objectMapper.readValue(new FileReader(arquivo), new TypeReference<List<LiderGin>>() {});
    }

    public List<Treinador> carregarTreinadores() throws IOException {
        File arquivoDeDados = new File(SAVE_DATA_PATH);
        if (!arquivoDeDados.exists() || arquivoDeDados.length() == 0) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(new FileReader(arquivoDeDados), new TypeReference<List<Treinador>>() {});
    }

    // --- MÉTODO DE ESCRITA ---

    public void salvarTreinadores(List<Treinador> treinadores) throws IOException {
        File arquivoDeDados = new File(SAVE_DATA_PATH);
        File pastaAssets = arquivoDeDados.getParentFile();
        if (!pastaAssets.exists()) {
            pastaAssets.mkdirs();
        }
        objectMapper.writeValue(arquivoDeDados, treinadores);
    }
}