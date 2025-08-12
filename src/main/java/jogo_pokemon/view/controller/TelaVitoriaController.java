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
import jogo_pokemon.utils.GerenciadorDeMusica;
import jogo_pokemon.utils.ImageUtils;
import jogo_pokemon.view.GerenciadorDeTelas;

/**
 * Controlador para a tela de vitória.
 * É exibida quando o jogador vence uma batalha e gere a lógica de recompensa,
 * que consiste em adicionar um novo Pokémon à equipa do jogador.
 */
public class TelaVitoriaController {

    @FXML
    private Text textPokemonRecompensa; 
    
    @FXML
    private ImageView imgPokemonRecompensa;

    private GerenciadorDados gerenciador = new GerenciadorDados();

    /**
     * Inicializa a tela de vitória. Este método implementa a lógica de recompensa:
     * 1. Carrega os Pokémon do jogador e todos os Pokémon disponíveis.
     * 2. Filtra a lista para encontrar Pokémon que o jogador ainda não possui.
     * 3. Se houver Pokémon disponíveis, seleciona um aleatoriamente como recompensa.
     * 4. Exibe o Pokémon recompensa na tela.
     * 5. Adiciona o novo Pokémon à equipa do jogador e guarda o progresso.
     * 6. Se o jogador já tiver todos, exibe uma mensagem de conclusão.
     */
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
                textPokemonRecompensa.setText("");
                ImageUtils.carregarPokemonImage(imgPokemonRecompensa, "insignia.png");
                ImageUtils.aplicarSombra(imgPokemonRecompensa);
            }
        } catch (IOException e) {
            textPokemonRecompensa.setText("Erro ao obter recompensa.");
            e.printStackTrace();
        }
    }
    
    /**
     * Guarda o estado atual do treinador no ficheiro de dados.
     * @param jogador O objeto Treinador com a equipa atualizada.
     * @throws IOException se ocorrer um erro ao escrever no ficheiro.
     */
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

    /**
     * Handler do botão "Voltar ao Menu Principal".
     * Retoma a música de menu e navega o utilizador para a tela do menu principal.
     */
    @FXML
    void onVoltarAoMenuClick() {
        GerenciadorDeMusica.tocarMusicaMenu();
        GerenciadorDeTelas.irParaMenuPrincipal();
    }
}