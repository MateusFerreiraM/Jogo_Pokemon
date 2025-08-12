package jogo_pokemon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jogo_pokemon.model.Batalha;
import jogo_pokemon.model.Movimentos;
import jogo_pokemon.model.Pokemon;
import jogo_pokemon.model.Tipos;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes de unidade para a classe Batalha.
 * Foca-se em verificar se a lógica de cálculo de dano e os efeitos do ataque estão corretos.
 */
class BatalhaTest {

    private Pokemon charmander;
    private Pokemon bulbasaur;
    private Pokemon squirtle;
    private Random randomParaTestes;

    /**
     * Método de setup, executado antes de cada teste.
     * Cria os Pokémon e o Random previsível para garantir consistência nos testes.
     */
    @BeforeEach
    void setUp() {
        charmander = new Pokemon("Charmander", 4, Arrays.asList(Tipos.FOGO), 52, 43, 39);
        bulbasaur = new Pokemon("Bulbasaur", 1, Arrays.asList(Tipos.GRAMA, Tipos.VENENO), 49, 49, 45);
        squirtle = new Pokemon("Squirtle", 7, Arrays.asList(Tipos.AGUA), 48, 65, 44);
        
        charmander.inicializarMovimentos();

        // Usamos um Random com uma semente fixa para tornar os testes previsíveis.
        randomParaTestes = new Random(123L);
    }

    /**
     * Testa o cálculo de dano quando o atacante tem vantagem de tipo (super eficaz).
     */
    @Test
    void testeCalculoDanoComVantagem() {
        // Arrange
        Batalha batalha = new Batalha(charmander, bulbasaur, randomParaTestes);
        Movimentos ataqueDeFogo = charmander.getMovimentosList().get(0);

        // Act
        int danoCalculado = batalha.calculoDano(charmander, bulbasaur, ataqueDeFogo);

        // Assert
        assertEquals(20, danoCalculado, "O dano com vantagem de tipo (FOGO vs GRAMA) deve ser 20.");
    }

    /**
     * Testa o cálculo de dano quando o atacante tem desvantagem de tipo (pouco eficaz).
     */
    @Test
    void testeCalculoDanoComDesvantagem() {
        // Arrange
        Batalha batalha = new Batalha(charmander, squirtle, randomParaTestes);
        Movimentos ataqueDeFogo = charmander.getMovimentosList().get(0);

        // Act
        int danoCalculado = batalha.calculoDano(charmander, squirtle, ataqueDeFogo);

        // Assert
        assertEquals(5, danoCalculado, "O dano com desvantagem (FOGO vs AGUA) deve ser o dano mínimo, 5.");
    }

    /**
     * NOVO: Testa se o método atacar reduz corretamente o HP do alvo.
     */
    @Test
    void testeAtaqueReduzHpCorretamente() {
        // Arrange
        Batalha batalha = new Batalha(charmander, bulbasaur, randomParaTestes);
        Movimentos ataqueDeFogo = charmander.getMovimentosList().get(0);
        int hpInicialDoAlvo = bulbasaur.getHpAtual(); // HP inicial = 45
        int danoEsperado = 20; // Sabemos pelo teste de vantagem que o dano será 20

        // Act
        batalha.atacar(charmander, bulbasaur, ataqueDeFogo);

        // Assert
        assertEquals(hpInicialDoAlvo - danoEsperado, bulbasaur.getHpAtual(), "O HP do alvo deve ser reduzido pelo dano calculado.");
    }

    /**
     * NOVO: Testa se o HP de um Pokémon derrotado é definido como 0 e nunca como um valor negativo.
     */
    @Test
    void testePokemonDerrotadoHpNaoFicaNegativo() {
        // Arrange
        bulbasaur.setHpAtual(15); // HP baixo, menor que o dano do ataque
        Batalha batalha = new Batalha(charmander, bulbasaur, randomParaTestes);
        Movimentos ataqueDeFogo = charmander.getMovimentosList().get(0); // Dano será 20

        // Act
        batalha.atacar(charmander, bulbasaur, ataqueDeFogo);

        // Assert
        assertEquals(0, bulbasaur.getHpAtual(), "O HP do Pokémon derrotado deve ser 0, nunca negativo.");
    }

    /**
     * NOVO: Testa se o estado da batalha é atualizado corretamente após um Pokémon ser derrotado.
     */
    @Test
    void testeFimDeBatalhaAoDerrotarOponente() {
        // Arrange
        bulbasaur.setHpAtual(15); // Garante que o próximo ataque será fatal
        Batalha batalha = new Batalha(charmander, bulbasaur, randomParaTestes);
        Movimentos ataqueDeFogo = charmander.getMovimentosList().get(0);

        // Act
        batalha.atacar(charmander, bulbasaur, ataqueDeFogo);

        // Assert
        assertFalse(batalha.getEmExecucao(), "A batalha deve terminar quando um Pokémon é derrotado.");
        assertTrue(batalha.getVitoria(), "Deve ser uma vitória para o jogador quando o oponente é derrotado.");
    }
}