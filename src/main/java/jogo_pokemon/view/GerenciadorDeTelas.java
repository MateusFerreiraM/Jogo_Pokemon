package jogo_pokemon.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import jogo_pokemon.App;

import java.io.IOException;

/**
 * Classe utilitária para gerir a navegação entre as diferentes telas (cenas) da aplicação.
 * Centraliza todos os caminhos dos ficheiros FXML para facilitar a manutenção.
 */
public class GerenciadorDeTelas {

    private static Scene cenaPrincipal;

    /**
     * Inicializa o gerenciador com a cena principal da aplicação.
     * Deve ser chamado uma única vez no arranque do jogo.
     * @param scene A cena principal da Stage.
     */
    public static void inicializar(Scene scene) {
        cenaPrincipal = scene;
    }

    /**
     * Método interno privado que carrega o FXML e o define como a raiz da cena principal.
     * @param fxml O nome do ficheiro .fxml a ser carregado (ex: "TelaInicial.fxml").
     * @throws IOException se o ficheiro FXML não for encontrado.
     */
    private static void mudarTela(String fxml) throws IOException {
        Parent root = FXMLLoader.load(App.class.getResource("view/" + fxml));
        cenaPrincipal.setRoot(root);
    }

    // --- MÉTODOS PÚBLICOS PARA NAVEGAÇÃO ---

    public static void irParaTelaInicial() {
        try {
            mudarTela("TelaInicial.fxml");
        } catch (IOException e) {
            System.err.println("Falha ao carregar a TelaInicial.fxml");
            e.printStackTrace();
        }
    }

    public static void irParaTelaSelecionarTreinador() {
        try {
            mudarTela("TelaSelecionarTreinador.fxml");
        } catch (IOException e) {
            System.err.println("Falha ao carregar a TelaSelecionarTreinador.fxml");
            e.printStackTrace();
        }
    }

    public static void irParaTelaCriarTreinador() {
        try {
            mudarTela("TelaCriarTreinador.fxml");
        } catch (IOException e) {
            System.err.println("Falha ao carregar a TelaCriarTreinador.fxml");
            e.printStackTrace();
        }
    }
    
    public static void irParaTelaPrimeiraEscolha() {
        try {
            mudarTela("TelaPrimeiraEscolha.fxml");
        } catch (IOException e) {
            System.err.println("Falha ao carregar a TelaPrimeiraEscolha.fxml");
            e.printStackTrace();
        }
    }
    
    public static void irParaMenuPrincipal() {
        try {
            mudarTela("TelaMenuPrincipal.fxml");
        } catch (IOException e) {
            System.err.println("Falha ao carregar a TelaMenuPrincipal.fxml");
            e.printStackTrace();
        }
    }

    public static void irParaPokedex() {
        try {
            mudarTela("TelaPokedex.fxml");
        } catch (IOException e) {
            System.err.println("Falha ao carregar a TelaPokedex.fxml");
            e.printStackTrace();
        }
    }

    public static void irParaPokedexList() {
        try {
            mudarTela("TelaPokedexList.fxml");
        } catch (IOException e) {
            System.err.println("Falha ao carregar a TelaPokedexList.fxml");
            e.printStackTrace();
        }
    }

    public static void irParaMeusPokemon() {
        try {
            mudarTela("TelaMeusPokemon.fxml");
        } catch (IOException e) {
            System.err.println("Falha ao carregar a TelaMeusPokemon.fxml");
            e.printStackTrace();
        }
    }
    
    public static void irParaTelaIdentidade() {
        try {
            mudarTela("TelaIdentidade.fxml");
        } catch (IOException e) {
            System.err.println("Falha ao carregar a TelaIdentidade.fxml");
            e.printStackTrace();
        }
    }

    public static void irParaTelaEscolherGinasio() {
        try {
            mudarTela("TelaEscolherGinasio.fxml");
        } catch (IOException e) {
            System.err.println("Falha ao carregar a TelaEscolherGinasio.fxml");
            e.printStackTrace();
        }
    }
    
    public static void irParaTelaConfirmarBatalha() {
        try {
            mudarTela("TelaConfirmarBatalha.fxml");
        } catch (IOException e) {
            System.err.println("Falha ao carregar a TelaConfirmarBatalha.fxml");
            e.printStackTrace();
        }
    }
    
    public static void irParaTelaDeBatalha() {
        try {
            mudarTela("TelaBatalha.fxml");
        } catch (IOException e) {
            System.err.println("Falha ao carregar a TelaBatalha.fxml");
            e.printStackTrace();
        }
    }
    
    public static void irParaTelaDeVitoria() {
        try {
            mudarTela("TelaVitoria.fxml");
        } catch (IOException e) {
            System.err.println("Falha ao carregar a TelaVitoria.fxml");
            e.printStackTrace();
        }
    }
    
    public static void irParaTelaDeDerrota() {
        try {
            mudarTela("TelaDerrota.fxml");
        } catch (IOException e) {
            System.err.println("Falha ao carregar a TelaDerrota.fxml");
            e.printStackTrace();
        }
    }
}