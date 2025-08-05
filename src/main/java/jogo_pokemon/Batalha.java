package jogo_pokemon;

import jogo_pokemon.Movimentos.Categoria;

public class Batalha {
    Pokemon pkmAmigo;
    Pokemon pkmInimigo;
    private boolean emExecucao;
    private boolean vitoria;
    int contEspecial;
    int contEspecialLider;

    // Tabela de vantagens, agora como uma constante estática
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
        // Garante que o HP dos pokémons seja resetado no início de cada batalha
        this.pkmAmigo.setHpAtual(this.pkmAmigo.getHp());
        this.pkmInimigo.setHpAtual(this.pkmInimigo.getHp());
        this.emExecucao = true;
        this.vitoria = false;
        this.contEspecial = 2;
        this.contEspecialLider = 2;
    }

    public void atacar(Pokemon quemAtaca, Pokemon alvo, Movimentos atk) {
        int dano = calculoDano(quemAtaca, alvo, atk);
        alvo.perdeHp(dano);

        if (!alvo.estaVivo()) {
            this.emExecucao = false;
            // Se o alvo derrotado for o inimigo, o jogador venceu.
            this.vitoria = alvo.equals(this.pkmInimigo);
        }
    }

    private double getVantagem(Movimentos ataque, Pokemon alvo) {
        // Usa o ordinal() do enum, que já é um número. É mais seguro e limpo.
        int indiceAtaque = ataque.getTipo().ordinal();
        int indiceDefesa = alvo.getTipos().get(0).ordinal();
        return VANTAGENS[indiceAtaque][indiceDefesa];
    }

    public int calculoDano(Pokemon quemAtaca, Pokemon alvo, Movimentos atk) {
        double vantagem = getVantagem(atk, alvo);
        int dano;

        // CORREÇÃO: Fórmulas de dano diferenciadas
        if (atk.getCategoria() == Categoria.FISICO) {
            // Ataque físico foca mais no ataque do atacante
            dano = (int) ((vantagem * quemAtaca.getAtaque() * atk.getForca()) - (alvo.getDefesa() * 0.5));
        } else {
            // Ataque especial ignora uma parte maior da defesa, mas tem um dano base ligeiramente menor
            dano = (int) ((vantagem * quemAtaca.getAtaque() * (atk.getForca() * 0.8)) - (alvo.getDefesa() * 0.25));
        }

        // Garante que o ataque sempre cause um dano mínimo
        return Math.max(5, dano);
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