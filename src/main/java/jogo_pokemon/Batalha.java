package jogo_pokemon;

import jogo_pokemon.Movimentos.Categoria;

public class Batalha {
    Pokemon pkmAmigo;
    Pokemon pkmInimigo;
    private boolean emExecucao;
    private boolean vitoria;
    int contEspecial;
    int contEspecialLider;

    // A matriz de vantagens continua a mesma
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

    // O construtor continua igual
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

    private int associaTipo(Tipos tipo) {
        // O método de associação continua igual
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

    // *** A CORREÇÃO PRINCIPAL ESTÁ AQUI ***
    private double getVantagem(Movimentos ataque, Pokemon alvo) {
        int indiceAtaque = associaTipo(ataque.getTipo());
        double multiplicadorFinal = 1.0;

        // Itera sobre TODOS os tipos do Pokémon alvo
        for (Tipos tipoDefensor : alvo.getTipos()) {
            int indiceDefesa = associaTipo(tipoDefensor);
            // Multiplica as vantagens (ex: 2x contra um tipo e 0.5x contra outro)
            multiplicadorFinal *= VANTAGENS[indiceAtaque][indiceDefesa];
        }

        return multiplicadorFinal;
    }

    // O resto da classe (atacar, calculoDano, getters, etc.) continua igual
    public void atacar(Pokemon quemAtaca, Pokemon alvo, Movimentos atk) {
        int dano = calculoDano(quemAtaca, alvo, atk);
        alvo.perdeHp(dano);

        if (!alvo.estaVivo()) {
            this.emExecucao = false;
            this.vitoria = alvo.equals(this.pkmInimigo);
        }
    }

    public int calculoDano(Pokemon quemAtaca, Pokemon alvo, Movimentos atk) {
        double vantagem = getVantagem(atk, alvo);
        int dano;

        if (atk.getCategoria() == Categoria.FISICO) {
            dano = (int) ((vantagem * quemAtaca.getAtaque() * atk.getForca()) - (alvo.getDefesa() * 0.5));
        } else {
            dano = (int) ((vantagem * quemAtaca.getAtaque() * (atk.getForca() * 0.8)) - (alvo.getDefesa() * 0.25));
        }

        return Math.max(5, dano);
    }

    public Pokemon getPkmAmigo() { return pkmAmigo; }
    public Pokemon getPkmInimigo() { return pkmInimigo; }
    public boolean getEmExecucao() { return this.emExecucao; }
    public boolean getVitoria() { return this.vitoria; }
    public int getContEspecial() { return this.contEspecial; }
    public int getContEspecialLider() { return this.contEspecialLider; }
    public void decrementarContEspecial() { this.contEspecial--; }
    public void decrementarContEspecialLider() { this.contEspecialLider--; }
}