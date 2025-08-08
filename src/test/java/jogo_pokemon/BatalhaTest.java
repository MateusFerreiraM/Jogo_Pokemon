package jogo_pokemon;

import org.junit.jupiter.api.Test;

import jogo_pokemon.model.Batalha;
import jogo_pokemon.model.Movimentos;
import jogo_pokemon.model.Pokemon;
import jogo_pokemon.model.Tipos;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

class BatalhaTest {

    @Test
    void testeCalculoDanoComVantagem() {
        // ARRANGE (Preparar)
        // Criamos um atacante de FOGO e um alvo de GRAMA (FOGO tem vantagem sobre GRAMA)
        Pokemon charmander = new Pokemon("Charmander", 4, Arrays.asList(Tipos.FOGO), 52, 43, 39);
        Pokemon bulbasaur = new Pokemon("Bulbasaur", 1, Arrays.asList(Tipos.GRAMA, Tipos.VENENO), 49, 49, 45);
        charmander.inicializarMovimentos(); // Garante que os movimentos existem

        Batalha batalha = new Batalha(charmander, bulbasaur);
        Movimentos ataqueDeFogo = charmander.getMovimentosList().get(0); // Ataque físico de FOGO

        // ACT (Agir)
        // Calculamos o dano que o Charmander causaria no Bulbasaur
        int danoComVantagem = batalha.calculoDano(charmander, bulbasaur, ataqueDeFogo);

        // ASSERT (Verificar)
        // A vantagem de FOGO vs GRAMA é 2.0. A fórmula é (vantagem * ataque * força) - (defesa * 0.5)
        // (2.0 * 52 * 1.0) - (49 * 0.5) = 104 - 24.5 = 79.5, que como int é 79.
        assertEquals(79, danoComVantagem, "O dano com vantagem de tipo deve ser super eficaz.");
        System.out.println("Teste de Dano com Vantagem: Sucesso! Dano calculado: " + danoComVantagem);
    }

    @Test
    void testeCalculoDanoSemVantagem() {
        // ARRANGE
        // Criamos um atacante Normal e um alvo Normal (sem vantagem)
        Pokemon raticate = new Pokemon("Raticate", 20, Arrays.asList(Tipos.NORMAL), 81, 60, 55);
        Pokemon snorlax = new Pokemon("Snorlax", 143, Arrays.asList(Tipos.NORMAL), 110, 65, 160);
        raticate.inicializarMovimentos();

        Batalha batalha = new Batalha(raticate, snorlax);
        Movimentos ataqueNormal = raticate.getMovimentosList().get(0);

        // ACT
        int danoSemVantagem = batalha.calculoDano(raticate, snorlax, ataqueNormal);

        // ASSERT
        // A vantagem de NORMAL vs NORMAL é 1.0. A fórmula é (vantagem * ataque * força) - (defesa * 0.5)
        // (1.0 * 81 * 1.0) - (65 * 0.5) = 81 - 32.5 = 48.5, que como int é 48.
        assertEquals(48, danoSemVantagem, "O dano sem vantagem de tipo deve ser normal.");
        System.out.println("Teste de Dano Sem Vantagem: Sucesso! Dano calculado: " + danoSemVantagem);
    }
}