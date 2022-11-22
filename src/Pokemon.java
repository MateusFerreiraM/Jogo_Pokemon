import java.util.*;

public class Pokemon extends Identificacao {

    public static final int NIVEL_EVOLUIR = 50;
    private List<Movimentos> movimentosList;
    private boolean statusVivo = true;
    private String regiao;
    private int nivel;
    private int hp;
    private int defesa;

    public Pokemon(String nome, List<Tipos> tipos, List<Movimentos> lista) {

        this.nome = nome;
        this.tipos = tipos;
        this.movimentosList = lista;
    }

    public List<Movimentos> getMovimentosList() {
        return movimentosList;
    }

    public boolean getStatusVivo() {
        return statusVivo;
    }

    public String getRegiao() {
        return regiao;
    }

    public int getNivel() {
        return nivel;
    }

    public int getHp() {
        return hp;
    }

    public int getDefesa() {
        return defesa;
    }

}
