package jogo_pokemon.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import jogo_pokemon.*;

public class TelaVitoriaController {

    @FXML
    private Label labelPokemonRecompensa;
    
    // NOVO CAMPO LIGADO AO FXML
    @FXML
    private ImageView imgPokemonRecompensa;

    private GerenciadorDados gerenciador = new GerenciadorDados();

    @FXML
    public void initialize() {
        try {
            Treinador jogador = App.getTreinadorSessao();
            List<Pokemon> pokemonsDisponiveis = gerenciador.carregarPokemonsDisponiveis();
            
            List<String> nomesPokemonJogador = jogador.getPokemons().stream()
                    .map(Pokemon::getNome)
                    .collect(Collectors.toList());
            List<Pokemon> pokemonsDeRecompensa = pokemonsDisponiveis.stream()
                    .filter(p -> !nomesPokemonJogador.contains(p.getNome()))
                    .collect(Collectors.toList());

            if (!pokemonsDeRecompensa.isEmpty()) {
                Pokemon novoPokemon = pokemonsDeRecompensa.get(new Random().nextInt(pokemonsDeRecompensa.size()));
                
                // Exibe o nome e a imagem do novo Pokémon
                labelPokemonRecompensa.setText(novoPokemon.getNome() + "!");
                carregarImagem(novoPokemon.getImagePath());
                
                jogador.adicionarPokemon(novoPokemon);

                // Salva o progresso
                List<Treinador> todosOsTreinadores = gerenciador.carregarTreinadores();
                for (int i = 0; i < todosOsTreinadores.size(); i++) {
                    if (todosOsTreinadores.get(i).getId() == jogador.getId()) {
                        todosOsTreinadores.set(i, jogador);
                        break;
                    }
                }
                gerenciador.salvarTreinadores(todosOsTreinadores);
            } else {
                labelPokemonRecompensa.setText("Você já capturou todos os Pokémon!");
            }

        } catch (IOException e) {
            labelPokemonRecompensa.setText("Erro ao obter recompensa.");
            e.printStackTrace();
        }
    }
    
    // NOVO MÉTODO AUXILIAR PARA CARREGAR A IMAGEM
    private void carregarImagem(String nomeImagem) {
        if (nomeImagem != null && !nomeImagem.isEmpty()) {
            try {
                String caminhoDoRecurso = "images/" + nomeImagem;
                InputStream stream = App.class.getResourceAsStream(caminhoDoRecurso);
                if (stream != null) {
                    imgPokemonRecompensa.setImage(new Image(stream));
                } else {
                    System.err.println("Recurso de imagem não encontrado em: " + caminhoDoRecurso);
                }
            } catch (Exception e) {
                System.err.println("Ocorreu um erro ao carregar a imagem: " + nomeImagem);
                e.printStackTrace();
            }
        }
    }

    @FXML
    void onVoltarAoMenuClick() throws IOException {
        GerenciadorDeTelas.mudarTela("TelaMenuPrincipal.fxml");
    }
}