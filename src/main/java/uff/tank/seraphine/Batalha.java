package uff.tank.seraphine;

<<<<<<< HEAD
import uff.tank.seraphine.Movimentos.Categoria;

=======
>>>>>>> 7c396a327dedc009bd40a3c5838b40055d949381
public class Batalha {
    Pokemon pkmAmigo;
    Pokemon pkmInimigo;
    Movimentos atk;
<<<<<<< HEAD
    private int nTurnos;
    private boolean emExecucao;
    private boolean vitoria;
=======

>>>>>>> 7c396a327dedc009bd40a3c5838b40055d949381

    public Batalha(Pokemon pkmAmigo, Pokemon pkmInimigo) {
        this.pkmAmigo = pkmAmigo;
        this.pkmInimigo = pkmInimigo;
<<<<<<< HEAD
        nTurnos = 0;
        this.emExecucao = true;
        this.vitoria = false;
    }

    public void incrementaTurnos() {
        this.nTurnos++;
    }

    public int getTurnos() {
        return nTurnos;
    }

    public void atacar(Pokemon quemAtk, Pokemon alvo, Movimentos atk) {
        int dano = calculoVantagem(quemAtk, alvo, atk);
        pkmInimigo.perdeHp(dano);

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
            dano = (int) (quemAtk.vantagem(alvo, atk) * quemAtk.getAtaque() * atk.forca) - alvo.getDefesa();
        } else {
            dano = (int) (quemAtk.vantagem(alvo, atk) * quemAtk.getAtaque() * atk.forca) - alvo.getDefesa();
        }
        if (dano <= 0) {
            return 2;
=======
    }

    public void atacar(Movimentos atk){
        int dano = calculoVantagem(pkmInimigo, pkmAmigo, atk);
        pkmInimigo.perdeHp(dano);
    }

    public int calculoVantagem(Pokemon quemAtk, Pokemon alvo, Movimentos atk){
        int dano = (int) (quemAtk.vantagem(alvo, atk) * quemAtk.getAtaque()) - alvo.getDefesa();
        if (dano<= 0) {
            return 0;
>>>>>>> 7c396a327dedc009bd40a3c5838b40055d949381
        }
        return dano;
    }

<<<<<<< HEAD
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

    public void imprimeHP(int i) {
=======
    public void imprimeHP(int i){
>>>>>>> 7c396a327dedc009bd40a3c5838b40055d949381
        System.out.println("====================");
        System.out.println("- HP Usuario: " + this.pkmAmigo.getHpAtual());
        System.out.println("- HP Computador: " + this.pkmAmigo.getHpAtual());
        System.out.println("* Contagem Especiais: " + i);
        System.out.println("====================");
    }
<<<<<<< HEAD

=======
    
>>>>>>> 7c396a327dedc009bd40a3c5838b40055d949381
}
