package uff.tank.seraphine;

import uff.tank.seraphine.Movimentos.Categoria;

public class Batalha {
    Pokemon pkmAmigo;
    Pokemon pkmInimigo;
    Movimentos atk;
    private int nTurnos;
    private boolean emExecucao;
    private boolean vitoria;
    int contEspecial;
    int contEspecialLider;

    public Batalha(Pokemon pkmAmigo, Pokemon pkmInimigo) {
        this.pkmAmigo = pkmAmigo;
        this.pkmInimigo = pkmInimigo;
        nTurnos = 0;
        this.pkmAmigo.setHpAtual(this.pkmAmigo.getHp());
        this.emExecucao = true;
        this.vitoria = false;
        this.contEspecial = 2;
        this.contEspecialLider = 2;
    }

    public void incrementaTurnos() {
        this.nTurnos++;
    }

    public int getTurnos() {
        return nTurnos;
    }

    public void atacar(Pokemon quemAtk, Pokemon alvo, Movimentos atk) {
        int dano = calculoVantagem(quemAtk, alvo, atk);
        alvo.perdeHp(dano);

        if (alvo.getHpAtual() <= 0) {
            // Termina a batalha e declara a vitoria ou derrota
            if (alvo.equals(this.pkmAmigo)) {
                this.vitoria = false;
            } else {
                this.vitoria = true;
            }
            this.emExecucao = false;
        }

    }

    public int calculoVantagem(Pokemon quemAtk, Pokemon alvo, Movimentos atk) {
        int dano = 0;
        if (atk.categoria == Categoria.FISICO) {
            dano = (int) (quemAtk.vantagem(alvo, atk) * quemAtk.getAtaque() * atk.forca) - alvo.getDefesa() / 2;
        } else {
            dano = (int) (quemAtk.vantagem(alvo, atk) * quemAtk.getAtaque() * atk.forca) - alvo.getDefesa() / 2;
        }
        if (dano <= 0) {
            return 5;
        }
        return dano;
    }

    public Pokemon getPkmAmigo() {
        return pkmAmigo;
    }

    public Pokemon getPkmInimigo() {
        return pkmInimigo;
    }

    public boolean getEmExecucao() {
        return this.emExecucao;
    }

    public boolean getVitoria() {
        return this.vitoria;
    }

    public int getContEspecial() {
        return this.contEspecial;
    }

    public int getContEspecialLider() {
        return this.contEspecialLider;
    }

    public void decrementarContEspecial() {
        this.contEspecial--;
    }

    public void decrementarContEspecialLider() {
        this.contEspecialLider--;
    }
}
