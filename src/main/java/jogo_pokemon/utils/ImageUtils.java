package jogo_pokemon.utils;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import jogo_pokemon.App;
import java.io.InputStream;

/**
 * Classe utilitária para operações comuns com imagens, como carregar sprites e aplicar efeitos.
 */
public class ImageUtils {

    /**
     * Carrega uma imagem a partir da pasta de recursos e a aplica a um ImageView.
     * Limpa qualquer imagem ou efeito anterior antes de carregar a nova.
     * @param imageView O componente ImageView a ser atualizado.
     * @param nomeImagem O nome do ficheiro da imagem (ex: "bulbasaur.png").
     */
    public static void carregarPokemonImage(ImageView imageView, String nomeImagem) {
        imageView.setImage(null);
        imageView.setEffect(null);

        if (nomeImagem != null && !nomeImagem.isEmpty()) {
            try {
                String caminhoDoRecurso = "/jogo_pokemon/images/" + nomeImagem;
                InputStream stream = App.class.getResourceAsStream(caminhoDoRecurso);

                if (stream != null) {
                    imageView.setImage(new Image(stream));
                } else {
                    System.err.println("Recurso de imagem não encontrado em: " + caminhoDoRecurso);
                }
            } catch (Exception e) {
                System.err.println("Ocorreu um erro ao carregar a imagem: " + nomeImagem);
                e.printStackTrace();
            }
        }
    }

    /**
     * Aplica um efeito de sombra padrão a um ImageView para dar profundidade.
     * @param imageView O componente ImageView a receber o efeito.
     */
    public static void aplicarSombra(ImageView imageView) {
        DropShadow shadow = new DropShadow();
        shadow.setRadius(20);
        shadow.setOffsetY(5);
        shadow.setColor(Color.color(0, 0, 0, 0.5));
        imageView.setEffect(shadow);
    }
}