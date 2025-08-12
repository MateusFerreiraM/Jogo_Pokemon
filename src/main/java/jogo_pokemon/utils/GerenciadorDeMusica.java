package jogo_pokemon.utils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration; // NOVO: Import necessário para o seek
import java.net.URL;

public class GerenciadorDeMusica {

    private static MediaPlayer menuPlayer;
    private static MediaPlayer batalhaPlayer;
    // NOVO: MediaPlayers para os efeitos sonoros
    private static MediaPlayer vitoriaSfxPlayer;
    private static MediaPlayer derrotaSfxPlayer;

    // Volume para músicas de fundo
    private static final double VOLUME_MUSICA = 0.05; 
    // NOVO: Volume para efeitos sonoros (geralmente mais alto)
    private static final double VOLUME_SFX = 0.1; 

    // Caminhos para as músicas
    private static final String MUSICA_MENU_PATH = "/jogo_pokemon/audio/game.mp3";
    private static final String MUSICA_BATALHA_PATH = "/jogo_pokemon/audio/battle.mp3";
    // NOVO: Caminhos para os efeitos sonoros
    private static final String SFX_VITORIA_PATH = "/jogo_pokemon/audio/win.mp3";
    private static final String SFX_DERROTA_PATH = "/jogo_pokemon/audio/lose.mp3";

    public static void carregarMusicas() {
        try {
            // Carrega Músicas de Fundo
            URL menuUrl = GerenciadorDeMusica.class.getResource(MUSICA_MENU_PATH);
            if (menuUrl != null) {
                menuPlayer = new MediaPlayer(new Media(menuUrl.toExternalForm()));
                menuPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Repetir
                menuPlayer.setVolume(VOLUME_MUSICA);
            } else {
                System.err.println("Erro: Ficheiro de música do menu não encontrado: " + MUSICA_MENU_PATH);
            }

            URL batalhaUrl = GerenciadorDeMusica.class.getResource(MUSICA_BATALHA_PATH);
            if (batalhaUrl != null) {
                batalhaPlayer = new MediaPlayer(new Media(batalhaUrl.toExternalForm()));
                batalhaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Repetir
                batalhaPlayer.setVolume(VOLUME_MUSICA);
            } else {
                System.err.println("Erro: Ficheiro de música de batalha não encontrado: " + MUSICA_BATALHA_PATH);
            }

            // NOVO: Carrega Efeitos Sonoros (sem repetir)
            URL vitoriaUrl = GerenciadorDeMusica.class.getResource(SFX_VITORIA_PATH);
            if (vitoriaUrl != null) {
                vitoriaSfxPlayer = new MediaPlayer(new Media(vitoriaUrl.toExternalForm()));
                vitoriaSfxPlayer.setVolume(VOLUME_SFX);
                // Não configuramos CycleCount, para tocar apenas uma vez
            } else {
                System.err.println("Erro: Efeito sonoro de vitória não encontrado: " + SFX_VITORIA_PATH);
            }

            URL derrotaUrl = GerenciadorDeMusica.class.getResource(SFX_DERROTA_PATH);
            if (derrotaUrl != null) {
                derrotaSfxPlayer = new MediaPlayer(new Media(derrotaUrl.toExternalForm()));
                derrotaSfxPlayer.setVolume(VOLUME_SFX);
                // Não configuramos CycleCount, para tocar apenas uma vez
            } else {
                System.err.println("Erro: Efeito sonoro de derrota não encontrado: " + SFX_DERROTA_PATH);
            }

        } catch (Exception e) {
            System.err.println("Ocorreu um erro ao carregar as músicas ou efeitos sonoros.");
            e.printStackTrace();
        }
    }

    public static void tocarMusicaMenu() {
        pararTudo(); // Para tudo antes de começar uma nova música de fundo
        if (menuPlayer != null) {
            menuPlayer.play();
        }
    }

    public static void tocarMusicaBatalha() {
        pararTudo(); // Para tudo antes de começar uma nova música de fundo
        if (batalhaPlayer != null) {
            batalhaPlayer.play();
        }
    }
    
    // NOVO: Método para tocar o SFX de vitória
    public static void tocarSfxVitoria() {
        pararTudo(); // Para a música de fundo
        if (vitoriaSfxPlayer != null) {
            vitoriaSfxPlayer.seek(Duration.ZERO); // Volta o áudio para o início
            vitoriaSfxPlayer.play();
        }
    }

    // NOVO: Método para tocar o SFX de derrota
    public static void tocarSfxDerrota() {
        pararTudo(); // Para a música de fundo
        if (derrotaSfxPlayer != null) {
            derrotaSfxPlayer.seek(Duration.ZERO); // Volta o áudio para o início
            derrotaSfxPlayer.play();
        }
    }

    public static void pararTudo() {
        if (menuPlayer != null && menuPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            menuPlayer.stop();
        }
        if (batalhaPlayer != null && batalhaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            batalhaPlayer.stop();
        }
        // MODIFICADO: Garante que os SFX também parem se necessário
        if (vitoriaSfxPlayer != null && vitoriaSfxPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            vitoriaSfxPlayer.stop();
        }
        if (derrotaSfxPlayer != null && derrotaSfxPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            derrotaSfxPlayer.stop();
        }
    }
}