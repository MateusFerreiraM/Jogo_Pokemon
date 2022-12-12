package uff.tank.seraphine;

import org.json.simple.JSONObject;

import uff.tank.seraphine.Movimentos.Categoria;
import uff.tank.seraphine.utils.JSONUtils;

import java.util.*;

import static uff.tank.seraphine.utils.JSONUtils.tipoFromString;

public class Pokemon extends Identificacao {

    private List<Movimentos> movimentosList;
    private String regiao;
    private int hp;
    private int hpAtual;
    private int ataque;
    private int defesa;
    private boolean lendario;

    public Pokemon(String nome, List<Tipos> tipos) {

        this.nome = nome;
        this.tipos = tipos;
    }

    public Pokemon(String nome, int id, ArrayList<Tipos> tipos, int ataque, int defesa, int hp) {
        this.movimentosList = new ArrayList<Movimentos>();
        this.id = id;
        this.nome = nome;
        this.tipos = tipos;
        this.ataque = ataque;
        this.defesa = defesa;
        this.hp = hp;
        this.hpAtual = this.hp;
        this.movimentosList.add(new Movimentos(tipos.get(0), 1.0, Categoria.FISICO));
        if (tipos.size() == 1) {
            this.movimentosList.add(new Movimentos(tipos.get(0), 1.15, Categoria.ESPECIAL));
        } else {
            this.movimentosList.add(new Movimentos(tipos.get(1), 1.15, Categoria.ESPECIAL));
        }

    }

    public List<Movimentos> getMovimentosList() {
        return movimentosList;
    }

    public Movimentos getMovimento(int i) {
        return movimentosList.get(i);
    }

    public boolean estaVivo() {
        if (hpAtual > 0) {
            return true;
        }
        return false;
    }

    public String getRegiao() {
        return regiao;
    }

    public int getHp() {
        return hp;
    }

    public int getHpAtual() {
        return hpAtual;
    }

    public void setHpAtual(int hpAtual) {
        this.hpAtual = hpAtual;
    }

    public void perdeHp(double dano) {
        int danoX = (int) dano;
        if (this.estaVivo()) {
            this.hpAtual -= danoX;
        }
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefesa() {
        return defesa;
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

    public static Pokemon getPokemonAleatorio() {
        Random random = new Random();
        int id = random.nextInt(152);
        return getPokemonFromJSONObject(
                JSONUtils.getObjectByID(id, "assets/pokemon.json"));
    }

    public static Pokemon getPokemonFromJSONObject(JSONObject obj) {
        int id = Integer.parseInt(obj.get("Id").toString());

        ArrayList<Tipos> tipos = new ArrayList<Tipos>();

        tipos.add(tipoFromString(obj.get("Tipo 1").toString()));

        if (obj.get("Tipo 2").toString() != "") {
            tipos.add(tipoFromString(obj.get("Tipo 2").toString()));
        }

        int ataque = Integer.parseInt(obj.get("Ataque").toString());
        int defesa = Integer.parseInt(obj.get("Defesa").toString());
        int hp = Integer.parseInt(obj.get("HP").toString());

        String nome = obj.get("Nome").toString();
        return new Pokemon(nome, id, tipos, ataque, defesa, hp);
    }

}
