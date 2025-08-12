package jogo_pokemon.model;

import java.util.Random;

/**
 * Representa o estado e a lógica de uma batalha Pokémon entre dois treinadores.
 * Esta classe gere os turnos, o cálculo de dano e as condições de vitória/derrota.
 */
public class Batalha {
    private Pokemon pkmAmigo;
    private Pokemon pkmInimigo;
    private boolean emExecucao;
    private boolean vitoria;
    private int contEspecial;
    private int contEspecialLider;
    private final Random random;
    
    /**
     * Matriz de vantagens de tipo. Cada linha representa um tipo de ataque
     * e cada coluna um tipo de defesa. O valor é o multiplicador de dano.
     */
    private static final double[][] VANTAGENS = {
            { 1, 1, 1, 1, 1, 0.5, 1, 0, 0.5, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 2, 1, 0.5, 0.5, 1, 2, 0.5, 0, 2, 1, 1, 1, 1, 0.5, 2, 1, 2, 0.5 },
            { 1, 2, 1, 1, 1, 0.5, 2, 1, 0.5, 1, 1, 2, 0.5, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 0.5, 0.5, 0.5, 1, 0.5, 0, 1, 1, 2, 1, 1, 1, 1, 1, 2 },
            { 1, 1, 0, 2, 1, 2, 0.5, 1, 2, 2, 1, 0.5, 2, 1, 1, 1, 1, 1 },
            { 1, 0.5, 2, 1, 0.5, 1, 2, 1, 0.5, 2, 1, 1, 1, 1, 2, 1, 1, 1 },
            { 1, 0.5, 0.5, 0.5, 1, 1, 1, 0.5, 0.5, 0.5, 1, 2, 1, 2, 1, 1, 2, 0.5 },
            { 0, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 0.5, 1 },
            { 1, 1, 1, 1, 1, 2, 1, 1, 0.5, 0.5, 0.5, 1, 0.5, 1, 2, 1, 1, 2 },
            { 1, 1, 1, 1, 1, 0.5, 2, 1, 2, 0.5, 0.5, 2, 1, 1, 2, 0.5, 1, 1 },
            { 1, 1, 1, 1, 2, 2, 1, 1, 1, 2, 0.5, 0.5, 1, 1, 1, 0.5, 1, 1 },
            { 1, 1, 0.5, 0.5, 2, 2, 0.5, 1, 0.5, 0.5, 2, 0.5, 1, 1, 1, 0.5, 1, 1 },
            { 1, 1, 2, 1, 0, 1, 1, 1, 1, 1, 2, 0.5, 0.5, 1, 1, 0.5, 1, 1 },
            { 1, 2, 1, 2, 1, 1, 1, 1, 0.5, 1, 1, 1, 1, 0.5, 1, 1, 0, 1 },
            { 1, 1, 2, 1, 2, 1, 1, 1, 0.5, 0.5, 0.5, 2, 1, 1, 0.5, 2, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 0.5, 1, 1, 1, 1, 1, 1, 2, 1, 0 },
            { 1, 0.5, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 0.5, 0.5 },
            { 1, 2, 1, 0.5, 1, 1, 1, 1, 0.5, 0.5, 1, 1, 1, 1, 1, 2, 2, 1 }
    };

    /**
     * Construtor principal para uso na aplicação. Inicia a batalha com aleatoriedade padrão.
     * @param pkmAmigo O Pokémon do jogador.
     * @param pkmInimigo O Pokémon do oponente.
     */
    public Batalha(Pokemon pkmAmigo, Pokemon pkmInimigo) {
        // MODIFICADO: Chama o outro construtor, fornecendo um novo Random padrão.
        this(pkmAmigo, pkmInimigo, new Random());
    }

    /**
     * NOVO: Construtor para testes, permitindo a injeção de um gerador de números aleatórios.
     * Isso torna os testes de cálculo de dano previsíveis.
     * @param pkmAmigo O Pokémon do jogador.
     * @param pkmInimigo O Pokémon do oponente.
     * @param random A instância de Random a ser usada para o cálculo de variação de dano.
     */
    public Batalha(Pokemon pkmAmigo, Pokemon pkmInimigo, Random random) {
        this.pkmAmigo = pkmAmigo;
        this.pkmInimigo = pkmInimigo;
        this.emExecucao = true;
        this.vitoria = false;
        this.contEspecial = 2;
        this.contEspecialLider = 2;
        this.random = random;
    }

    /**
     * Processa um turno de ataque de um Pokémon contra outro.
     * @param quemAtaca O Pokémon que está a atacar.
     * @param alvo O Pokémon que está a ser atacado.
     * @param atk O movimento utilizado.
     */
    public void atacar(Pokemon quemAtaca, Pokemon alvo, Movimentos atk) {
        int dano = calculoDano(quemAtaca, alvo, atk);
        
        int novoHp = alvo.getHpAtual() - dano;
        alvo.setHpAtual(Math.max(0, novoHp));

        if (!alvo.estaVivo()) {
            this.emExecucao = false;
            this.vitoria = alvo.equals(this.pkmInimigo);
        }
    }

    /**
     * Calcula o dano final de um ataque, considerando estatísticas, tipo e um fator de variação.
     * @param quemAtaca O Pokémon atacante.
     * @param alvo O Pokémon defensor.
     * @param atk O movimento usado.
     * @return O dano final a ser infligido.
     */
    public int calculoDano(Pokemon quemAtaca, Pokemon alvo, Movimentos atk) {
        double vantagem = getVantagem(atk, alvo);
        double poderMovimento = 40.0;
        double fatorDeBalanceamento = 5.0;

        double danoCalculado = ((quemAtaca.getAtaque() * poderMovimento / alvo.getDefesa()) / fatorDeBalanceamento + 2) * vantagem * atk.getForca();

        double variacao = 0.85 + (1.0 - 0.85) * random.nextDouble();
        int danoFinal = (int) (danoCalculado * variacao);
        
        return Math.max(5, danoFinal);
    }

    /**
     * Mapeia um Enum do tipo Tipos para o seu índice correspondente na matriz de vantagens.
     * @param tipo O tipo do Pokémon ou do ataque.
     * @return O índice inteiro (0-17).
     */
    private int associaTipo(Tipos tipo) {
        switch (tipo) {
            case NORMAL: return 0;
            case LUTADOR: return 1;
            case VOADOR: return 2;
            case VENENO: return 3;
            case TERRA: return 4;
            case PEDRA: return 5;
            case INSETO: return 6;
            case FANTASMA: return 7;
            case ACO: return 8;
            case FOGO: return 9;
            case AGUA: return 10;
            case GRAMA: return 11;
            case ELETRICO: return 12;
            case PSIQUICO: return 13;
            case GELO: return 14;
            case DRAGAO: return 15;
            case SOMBRIO: return 16;
            case FADA: return 17;
            default: return 0;
        }
    }

    /**
     * Calcula o multiplicador de vantagem de um ataque contra um alvo.
     * @param ataque O movimento a ser usado.
     * @param alvo O Pokémon defensor.
     * @return O multiplicador de dano final (ex: 2.0, 0.5, 0).
     */
    private double getVantagem(Movimentos ataque, Pokemon alvo) {
        int indiceAtaque = associaTipo(ataque.getTipo());
        double multiplicadorFinal = 1.0;
        for (Tipos tipoDefensor : alvo.getTipos()) {
            int indiceDefesa = associaTipo(tipoDefensor);
            multiplicadorFinal *= VANTAGENS[indiceAtaque][indiceDefesa];
        }
        return multiplicadorFinal;
    }

    // Getters e Setters
    public Pokemon getPkmAmigo() { return pkmAmigo; }
    public Pokemon getPkmInimigo() { return pkmInimigo; }
    public boolean getEmExecucao() { return this.emExecucao; }
    public boolean getVitoria() { return this.vitoria; }
    public int getContEspecial() { return this.contEspecial; }
    public int getContEspecialLider() { return this.contEspecialLider; }
    public void decrementarContEspecial() { this.contEspecial--; }
    public void decrementarContEspecialLider() { this.contEspecialLider--; }
}