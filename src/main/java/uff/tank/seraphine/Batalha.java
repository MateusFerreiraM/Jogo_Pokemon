package uff.tank.seraphine;

import uff.tank.seraphine.Movimentos.Categoria;

public class Batalha {
    Pokemon pkmAmigo;
    Pokemon pkmInimigo;
    Movimentos atk;
    private boolean emExecucao;
    private boolean vitoria;
    int contEspecial;
    int contEspecialLider;

    public Batalha(Pokemon pkmAmigo, Pokemon pkmInimigo) {
        this.pkmAmigo = pkmAmigo;
        this.pkmInimigo = pkmInimigo;
        this.pkmAmigo.setHpAtual(this.pkmAmigo.getHp());
        this.emExecucao = true;
        this.vitoria = false;
        this.contEspecial = 2;
        this.contEspecialLider = 2;
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

    public int associaTipo(Tipos tipo) {
        if (tipo == Tipos.NORMAL)
            return 0;
        else if (tipo == Tipos.LUTADOR)
            return 1;
        else if (tipo == Tipos.VOADOR)
            return 2;
        else if (tipo == Tipos.VENENO)
            return 3;
        else if (tipo == Tipos.TERRA)
            return 4;
        else if (tipo == Tipos.PEDRA)
            return 5;
        else if (tipo == Tipos.INSETO)
            return 6;
        else if (tipo == Tipos.FANTASMA)
            return 7;
        else if (tipo == Tipos.ACO)
            return 8;
        else if (tipo == Tipos.FOGO)
            return 9;
        else if (tipo == Tipos.AGUA)
            return 10;
        else if (tipo == Tipos.GRAMA)
            return 11;
        else if (tipo == Tipos.ELETRICO)
            return 12;
        else if (tipo == Tipos.PSIQUICO)
            return 13;
        else if (tipo == Tipos.GELO)
            return 14;
        else if (tipo == Tipos.DRAGAO)
            return 15;
        else if (tipo == Tipos.SOMBRIO)
            return 16;
        else if (tipo == Tipos.FADA)
            return 17;
        else
            return 0;
    }

    public double vantagem(Pokemon alvo, Movimentos ataque) {
        double m = 0.5; // Constante "1/2"
        double[][] vantagem = { { 1, 1, 1, 1, 1, m, 1, 0, m, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 2, 1, m, m, 1, 2, m, 0, 2, 1, 1, 1, 1, m, 2, 1, 2, m },
                { 1, 2, 1, 1, 1, m, 2, 1, m, 1, 1, 2, m, 1, 1, 1, 1, 1 },
                { 1, 1, 1, m, m, m, 1, m, 0, 1, 1, 2, 1, 1, 1, 1, 1, 2 },
                { 1, 1, 0, 2, 1, 2, m, 1, 2, 2, 1, m, 2, 1, 1, 1, 1, 1 },
                { 1, m, 2, 1, m, 1, 2, 1, m, 2, 1, 1, 1, 1, 2, 1, 1, 1 },
                { 1, m, m, m, 1, 1, 1, m, m, m, 1, 2, 1, 2, 1, 1, 2, m },
                { 0, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, m, 1 },
                { 1, 1, 1, 1, 1, 2, 1, 1, m, m, m, 1, m, 1, 2, 1, 1, 2 },
                { 1, 1, 1, 1, 1, m, 2, 1, 2, m, m, 2, 1, 1, 2, m, 1, 1 },
                { 1, 1, 1, 1, 2, 2, 1, 1, 1, 2, m, m, 1, 1, 1, m, 1, 1 },
                { 1, 1, m, m, 2, 2, m, 1, m, m, 2, m, 1, 1, 1, m, 1, 1 },
                { 1, 1, 2, 1, 0, 1, 1, 1, 1, 1, 2, m, m, 1, 1, m, 1, 1 },
                { 1, 2, 1, 2, 1, 1, 1, 1, m, 1, 1, 1, 1, m, 1, 1, 0, 1 },
                { 1, 1, 2, 1, 2, 1, 1, 1, m, m, m, 2, 1, 1, m, 2, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, m, 1, 1, 1, 1, 1, 1, 2, 1, 0 },
                { 1, m, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, m, m },
                { 1, 2, 1, m, 1, 1, 1, 1, m, m, 1, 1, 1, 1, 1, 2, 2, 1 }
        };
        int atk = associaTipo(ataque.getTipo()), dsf = associaTipo(alvo.getTipos().get(0));
        return vantagem[atk][dsf];

    }

    public int calculoVantagem(Pokemon quemAtk, Pokemon alvo, Movimentos atk) {
        int dano = 0;
        if (atk.categoria == Categoria.FISICO) {
            dano = (int) (vantagem(alvo, atk) * quemAtk.getAtaque() * atk.forca) - alvo.getDefesa() / 2;
        } else {
            dano = (int) (vantagem(alvo, atk) * quemAtk.getAtaque() * atk.forca) - alvo.getDefesa() / 2;
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
