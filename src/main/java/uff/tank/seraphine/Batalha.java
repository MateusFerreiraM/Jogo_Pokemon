package uff.tank.seraphine;

public class Batalha {
    Pokemon pkmAmigo;
    Pokemon pkmInimigo;
    Movimentos atk;


    public Batalha(Pokemon pkmAmigo, Pokemon pkmInimigo) {
        this.pkmAmigo = pkmAmigo;
        this.pkmInimigo = pkmInimigo;
    }

    public void atacar(Movimentos atk){
        int dano = calculoVantagem(pkmInimigo, pkmAmigo, atk);
        pkmInimigo.perdeHp(dano);
    }

    public int calculoVantagem(Pokemon quemAtk, Pokemon alvo, Movimentos atk){
        int dano = (int) (quemAtk.vantagem(alvo, atk) * quemAtk.getAtaque()) - alvo.getDefesa();
        if (dano<= 0) {
            return 0;
        }
        return dano;
    }

    public void imprimeHP(int i){
        System.out.println("====================");
        System.out.println("- HP Usuario: " + this.pkmAmigo.getHpAtual());
        System.out.println("- HP Computador: " + this.pkmAmigo.getHpAtual());
        System.out.println("* Contagem Especiais: " + i);
        System.out.println("====================");
    }
    
}
