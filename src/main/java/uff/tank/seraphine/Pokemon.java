package uff.tank.seraphine;

import org.json.simple.JSONObject;

import java.util.*;

import static uff.tank.seraphine.utils.JSONUtils.tipoFromString;


public class Pokemon extends Identificacao {

    public static final int NIVEL_EVOLUIR = 50;
    private List<Movimentos> movimentosList;
    private boolean statusVivo = true;
    private String regiao;
    private int nivel;
    private int hp;
    private int ataque;
    private int defesa;

    private boolean lendario;

    public Pokemon(String nome, List<Tipos> tipos, List<Movimentos> lista) {

        this.nome = nome;
        this.tipos = tipos;
        this.movimentosList = lista;
    }
    public Pokemon(String nome, int id, List<Tipos> tipos, int ataque, int defesa, int hp) {
        this.id = id;
        this.nome = nome;
        this.tipos = tipos;
        this.ataque = ataque;
        this.defesa = defesa;
        this.hp = hp;
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

    public int getAtaque(){return ataque;}

    public int getDefesa() {
        return defesa;
    }

    public static Pokemon getPokemonFromJSONObject(JSONObject obj){
        int id = Integer.parseInt(obj.get("Id").toString());

        ArrayList<Tipos> tipos = new ArrayList<Tipos>();

        tipos.add(tipoFromString(obj.get("Tipo 1").toString()));

        if(obj.get("Tipo 2").toString() != ""){
            tipos.add(tipoFromString(obj.get("Tipo 2").toString()));
        }

        int ataque = Integer.parseInt(obj.get("Ataque").toString());
        int defesa = Integer.parseInt(obj.get("Defesa").toString());
        int hp = Integer.parseInt(obj.get("HP").toString());

        String nome = obj.get("Nome").toString();
        return new Pokemon(nome, id, tipos, ataque, defesa, hp);
    }

}
