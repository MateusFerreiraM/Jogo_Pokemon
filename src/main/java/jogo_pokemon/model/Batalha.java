package jogo_pokemon.model;

import java.util.Random;

/**
 * Representa o estado e a lógica de uma batalha entre dois Pokémon.
 * Esta classe gere os turnos, o cálculo de dano e as condições de vitória.
 */
public class Batalha {
    private Pokemon pkmAmigo;
    private Pokemon pkmInimigo;
    private boolean emExecucao;
    private boolean vitoria;
    private int contEspecial;
    private int contEspecialLider;
    private Random random = new Random();

    // Matriz de vantagens de tipo (Ataque x Defesa)
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

    public Batalha(Pokemon pkmAmigo, Pokemon pkmInimigo) {
        this.pkmAmigo = pkmAmigo;
        this.pkmInimigo = pkmInimigo;
        this.pkmAmigo.setHpAtual(this.pkmAmigo.getHp());
        this.pkmInimigo.setHpAtual(this.pkmInimigo.getHp());
        this.emExecucao = true;
        this.vitoria = false;
        this.contEspecial = 2;
        this.contEspecialLider = 2;
    }

    /**
     * Processa um ataque de um Pokémon contra outro, calculando o dano e atualizando o HP.
     * @param quemAtaca O Pokémon que está a atacar.
     * @param alvo O Pokémon que está a ser atacado.
     * @param atk O movimento utilizado.
     */
    public void atacar(Pokemon quemAtaca, Pokemon alvo, Movimentos atk) {
        int dano = calculoDano(quemAtaca, alvo, atk);
        
        // NOVO: Lógica para garantir que o HP não fique negativo
        // Em vez de chamar alvo.perdeHp(dano), calculamos o novo HP aqui
        int novoHp = alvo.getHpAtual() - dano;
        // Usamos Math.max para garantir que o menor valor possível para o HP seja 0
        alvo.setHpAtual(Math.max(0, novoHp));
        
        // A linha original 'alvo.perdeHp(dano);' foi substituída pela lógica acima.

        if (!alvo.estaVivo()) {
            this.emExecucao = false;
            this.vitoria = alvo.equals(this.pkmInimigo);
        }
    }

    /**
     * Calcula o dano de um ataque usando uma fórmula balanceada para batalhas mais longas.
     * @return O dano final a ser infligido.
     */
    public int calculoDano(Pokemon quemAtaca, Pokemon alvo, Movimentos atk) {
        double vantagem = getVantagem(atk, alvo);
        // MODIFICADO: Aumentei um pouco o poder base dos movimentos para batalhas mais rápidas
        double poderMovimento = 40.0;
        double fatorDeBalanceamento = 5.0; // Ajustado para compensar o aumento de poder

        double danoCalculado = ((quemAtaca.getAtaque() * poderMovimento / alvo.getDefesa()) / fatorDeBalanceamento + 2) * vantagem * atk.getForca();

        double variacao = 0.85 + (1.0 - 0.85) * random.nextDouble();
        int danoFinal = (int) (danoCalculado * variacao);

        if (vantagem >= 2.0) {
            System.out.println("Super eficaz!");
            return Math.max(10, danoFinal);
        } else if (vantagem > 0 && vantagem < 1.0) {
            System.out.println("Pouco eficaz...");
        } else if (vantagem == 0) {
            System.out.println("Não tem efeito!");
        }
        
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
     * Calcula o multiplicador de vantagem de um ataque contra um alvo, considerando todos os tipos do alvo.
     * @param ataque O movimento a ser usado.
     * @param alvo O Pokémon defensor.
     * @return O multiplicador de dano final (ex: 2.0 para super eficaz, 0.5 para pouco eficaz).
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

    // --- Getters e Setters para o estado da batalha ---
    public Pokemon getPkmAmigo() { return pkmAmigo; }
    public Pokemon getPkmInimigo() { return pkmInimigo; }
    public boolean getEmExecucao() { return this.emExecucao; }
    public boolean getVitoria() { return this.vitoria; }
    public int getContEspecial() { return this.contEspecial; }
    public int getContEspecialLider() { return this.contEspecialLider; }
    public void decrementarContEspecial() { this.contEspecial--; }
    public void decrementarContEspecialLider() { this.contEspecialLider--; }
}