package jogo_pokemon;

import jogo_pokemon.data.GerenciadorDados;
import jogo_pokemon.model.Treinador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes de unidade para a classe GerenciadorDados.
 * Utiliza uma pasta temporária para testar a leitura e escrita de ficheiros
 * sem afetar os dados reais do jogo.
 */
class GerenciadorDadosTest {

    /**
     * O JUnit 5 cria e apaga automaticamente esta pasta temporária para cada teste.
     */
    @TempDir
    Path tempDir;

    private GerenciadorDados gerenciador;
    private String caminhoSalvarTeste;

    /**
     * Método de setup executado antes de cada teste.
     * Cria uma instância do GerenciadorDados que aponta para um ficheiro dentro da pasta temporária.
     */
    @BeforeEach
    void setUp() {
        // Define o caminho para um ficheiro de gravação que não existe, dentro da pasta temporária
        caminhoSalvarTeste = tempDir.resolve("dados_teste.json").toString();
        
        // Usamos o construtor de teste, passando null para os caminhos que não vamos testar agora
        gerenciador = new GerenciadorDados(null, null, caminhoSalvarTeste);
    }

    /**
     * Testa o ciclo completo de salvar e carregar uma lista de treinadores.
     * Garante que os dados escritos são os mesmos que os lidos.
     */
    @Test
    void testeSalvarECarregarTreinadores() throws IOException {
        // Arrange (Preparar)
        Treinador t1 = new Treinador("Ash", "Pallet", 1);
        Treinador t2 = new Treinador("Misty", "Cerulean", 2);
        List<Treinador> treinadoresOriginais = Arrays.asList(t1, t2);

        // Act (Agir)
        gerenciador.salvarTreinadores(treinadoresOriginais);
        List<Treinador> treinadoresCarregados = gerenciador.carregarTreinadores();

        // Assert (Verificar)
        assertNotNull(treinadoresCarregados);
        assertEquals(2, treinadoresCarregados.size(), "A lista carregada deve ter 2 treinadores.");
        assertEquals("Ash", treinadoresCarregados.get(0).getNome(), "O nome do primeiro treinador deve ser Ash.");
        assertEquals("Cerulean", treinadoresCarregados.get(1).getRegiao(), "A região do segundo treinador deve ser Cerulean.");
    }

    /**
     * Testa o comportamento de carregar treinadores quando o ficheiro de dados não existe.
     * O método deve retornar uma lista vazia em vez de lançar um erro.
     */
    @Test
    void testeCarregarTreinadoresDeArquivoInexistente() throws IOException {
        // Arrange
        // O ficheiro em `caminhoSalvarTeste` não existe porque o setUp o cria, mas o @TempDir o apaga.

        // Act
        List<Treinador> treinadores = gerenciador.carregarTreinadores();

        // Assert
        assertNotNull(treinadores, "A lista não deve ser nula.");
        assertTrue(treinadores.isEmpty(), "A lista deve estar vazia quando o ficheiro não existe.");
    }
}