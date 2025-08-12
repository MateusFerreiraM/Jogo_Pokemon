package jogo_pokemon.utils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.net.URL;

/**
 * Classe utilitária para gerir a reprodução de músicas de fundo e efeitos sonoros.
 * Centraliza todo o controlo de áudio da aplicação.
 */
public class GerenciadorDeMusica {

    private static MediaPlayer menuPlayer;
    private static MediaPlayer batalhaPlayer;
    private static MediaPlayer vitoriaSfxPlayer;
    private static MediaPlayer derrotaSfxPlayer;

    // --- Constantes de Configuração de Áudio ---
    private static final double VOLUME_MUSICA = 0.05; 
    private static final double VOLUME_SFX = 0.1; 
    private static final String MUSICA_MENU_PATH = "/jogo_pokemon/audio/game.wav";
    private static final String MUSICA_BATALHA_PATH = "/jogo_pokemon/audio/battle.wav";
    private static final String SFX_VITORIA_PATH = "/jogo_pokemon/audio/win.wav";
    private static final String SFX_DERROTA_PATH = "/jogo_pokemon/audio/lose.wav";

    /**
     * Carrega todos os ficheiros de áudio (músicas e SFX) e prepara os MediaPlayers.
     * Este método deve ser chamado uma única vez no arranque do jogo.
     */
    public static void carregarMusicas() {
        try {
            // Carrega Músicas de Fundo (com loop)
            URL menuUrl = GerenciadorDeMusica.class.getResource(MUSICA_MENU_PATH);
            if (menuUrl != null) {
                menuPlayer = new MediaPlayer(new Media(menuUrl.toExternalForm()));
                menuPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                menuPlayer.setVolume(VOLUME_MUSICA);
            } else {
                System.err.println("Erro: Ficheiro de música do menu não encontrado: " + MUSICA_MENU_PATH);
            }

            URL batalhaUrl = GerenciadorDeMusica.class.getResource(MUSICA_BATALHA_PATH);
            if (batalhaUrl != null) {
                batalhaPlayer = new MediaPlayer(new Media(batalhaUrl.toExternalForm()));
                batalhaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                batalhaPlayer.setVolume(VOLUME_MUSICA);
            } else {
                System.err.println("Erro: Ficheiro de música de batalha não encontrado: " + MUSICA_BATALHA_PATH);
            }

            // Carrega Efeitos Sonoros (sem loop)
            URL vitoriaUrl = GerenciadorDeMusica.class.getResource(SFX_VITORIA_PATH);
            if (vitoriaUrl != null) {
                vitoriaSfxPlayer = new MediaPlayer(new Media(vitoriaUrl.toExternalForm()));
                vitoriaSfxPlayer.setVolume(VOLUME_SFX);
            } else {
                System.err.println("Erro: Efeito sonoro de vitória não encontrado: " + SFX_VITORIA_PATH);
            }

            URL derrotaUrl = GerenciadorDeMusica.class.getResource(SFX_DERROTA_PATH);
            if (derrotaUrl != null) {
                derrotaSfxPlayer = new MediaPlayer(new Media(derrotaUrl.toExternalForm()));
                derrotaSfxPlayer.setVolume(VOLUME_SFX);
            } else {
                System.err.println("Erro: Efeito sonoro de derrota não encontrado: " + SFX_DERROTA_PATH);
            }

        } catch (Exception e) {
            System.err.println("Ocorreu um erro ao carregar as músicas ou efeitos sonoros.");
            e.printStackTrace();
        }
    }

    /**
     * Para qualquer áudio em reprodução e inicia a música do menu.
     */
    public static void tocarMusicaMenu() {
        pararTudo();
        if (menuPlayer != null) {
            menuPlayer.play();
        }
    }

    /**
     * Para qualquer áudio em reprodução e inicia a música de batalha.
     */
    public static void tocarMusicaBatalha() {
        pararTudo();
        if (batalhaPlayer != null) {
            batalhaPlayer.play();
        }
    }
    
    /**
     * Para qualquer áudio em reprodução e toca o efeito sonoro de vitória uma vez.
     */
    public static void tocarSfxVitoria() {
        pararTudo();
        if (vitoriaSfxPlayer != null) {
            vitoriaSfxPlayer.seek(Duration.ZERO);
            vitoriaSfxPlayer.play();
        }
    }

    /**
     * Para qualquer áudio em reprodução e toca o efeito sonoro de derrota uma vez.
     */
    public static void tocarSfxDerrota() {
        pararTudo();
        if (derrotaSfxPlayer != null) {
            derrotaSfxPlayer.seek(Duration.ZERO);
            derrotaSfxPlayer.play();
        }
    }

    /**
     * Para a reprodução de todos os MediaPlayers (músicas e SFX).
     */
    public static void pararTudo() {
        if (menuPlayer != null && menuPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            menuPlayer.stop();
        }
        if (batalhaPlayer != null && batalhaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            batalhaPlayer.stop();
        }
        if (vitoriaSfxPlayer != null && vitoriaSfxPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            vitoriaSfxPlayer.stop();
        }
        if (derrotaSfxPlayer != null && derrotaSfxPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            derrotaSfxPlayer.stop();
        }
    }
}