package jogo_pokemon.view.controller;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import jogo_pokemon.App;
import jogo_pokemon.data.GerenciadorDados;
import jogo_pokemon.model.Pokemon;
import jogo_pokemon.model.Treinador;
import jogo_pokemon.utils.GerenciadorDeMusica; // NOVO: Importa o gerenciador de música
import jogo_pokemon.utils.ImageUtils;
import jogo_pokemon.view.GerenciadorDeTelas;

public class TelaVitoriaController {

    @FXML
    private Text textPokemonRecompensa; 
    
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
                
                novoPokemon.inicializarMovimentos();
                
                textPokemonRecompensa.setText(novoPokemon.getNome() + "!");
                ImageUtils.carregarPokemonImage(imgPokemonRecompensa, novoPokemon.getImagePath());
                ImageUtils.aplicarSombra(imgPokemonRecompensa);
                
                jogador.adicionarPokemon(novoPokemon);
                salvarProgresso(jogador);
            } else {
                textPokemonRecompensa.setText("Você já capturou todos os Pokémon!");
            }
        } catch (IOException e) {
            textPokemonRecompensa.setText("Erro ao obter recompensa.");
            e.printStackTrace();
        }
    }
    
    private void salvarProgresso(Treinador jogador) throws IOException {
        List<Treinador> todosOsTreinadores = gerenciador.carregarTreinadores();
        for (int i = 0; i < todosOsTreinadores.size(); i++) {
            if (todosOsTreinadores.get(i).getId() == jogador.getId()) {
                todosOsTreinadores.set(i, jogador);
                break;
            }
        }
        gerenciador.salvarTreinadores(todosOsTreinadores);
    }

    @FXML
    void onVoltarAoMenuClick() throws IOException {
        // NOVO: Para a música de batalha e volta a tocar a música de menu
        GerenciadorDeMusica.tocarMusicaMenu();
        
        GerenciadorDeTelas.mudarTela("TelaMenuPrincipal.fxml");
    }
}