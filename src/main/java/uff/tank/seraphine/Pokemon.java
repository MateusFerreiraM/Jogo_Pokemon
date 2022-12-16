package uff.tank.seraphine;

import org.json.simple.JSONObject;
import uff.tank.seraphine.Movimentos.Categoria;
import uff.tank.seraphine.utils.JSONUtils;

import javax.security.auth.login.CredentialException;
import java.util.*;
import static uff.tank.seraphine.utils.JSONUtils.tipoFromString;

public class Pokemon {
    private String nome;

    private int id;
    private List<Tipos> tipos;
    private List<Movimentos> movimentosList;
    private String regiao;
    private int hp;
    private int hpAtual;
    private int ataque;
    private int defesa;

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

    public List<Tipos> getTipos(){
        return  this.tipos;
    }

    public String getNome(){
        return this.nome;
    }

    public int getId(){
        return this.id;
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
