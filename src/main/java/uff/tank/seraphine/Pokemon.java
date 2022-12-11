package uff.tank.seraphine;

import org.json.simple.JSONObject;

<<<<<<< HEAD
import uff.tank.seraphine.Movimentos.Categoria;
import uff.tank.seraphine.utils.JSONUtils;

=======
>>>>>>> 7c396a327dedc009bd40a3c5838b40055d949381
import java.util.*;

import static uff.tank.seraphine.utils.JSONUtils.tipoFromString;

<<<<<<< HEAD
public class Pokemon extends Identificacao {

    private List<Movimentos> movimentosList;
    private String regiao;
=======

public class Pokemon extends Identificacao {

    public static final int NIVEL_EVOLUIR = 50;
    private List<Movimentos> movimentosList;
    private String regiao;
    private int nivel;
>>>>>>> 7c396a327dedc009bd40a3c5838b40055d949381
    private int hp;
    private int hpAtual;
    private int ataque;
    private int defesa;
    private boolean lendario;

<<<<<<< HEAD
    public Pokemon(String nome, List<Tipos> tipos) {

        this.nome = nome;
        this.tipos = tipos;
    }

    public Pokemon(String nome, int id, List<Tipos> tipos, int ataque, int defesa, int hp) {
        this.movimentosList = new List<Movimentos>();
=======
    public Pokemon(String nome, List<Tipos> tipos, List<Movimentos> lista) {

        this.nome = nome;
        this.tipos = tipos;
        this.movimentosList = lista;
    }
    public Pokemon(String nome, int id, List<Tipos> tipos, int ataque, int defesa, int hp) {
>>>>>>> 7c396a327dedc009bd40a3c5838b40055d949381
        this.id = id;
        this.nome = nome;
        this.tipos = tipos;
        this.ataque = ataque;
        this.defesa = defesa;
        this.hp = hp;
<<<<<<< HEAD
        this.movimentosList.set(0, new Movimentos(tipos.get(0), 1.0, Categoria.FISICO));
        this.movimentosList.set(1, new Movimentos(tipos.get(1), 1.25, Categoria.ESPECIAL));

=======
>>>>>>> 7c396a327dedc009bd40a3c5838b40055d949381
    }

    public List<Movimentos> getMovimentosList() {
        return movimentosList;
    }

<<<<<<< HEAD
    public Movimentos getMovimento(int i) {
=======
    public Movimentos getMovimento(int i){
>>>>>>> 7c396a327dedc009bd40a3c5838b40055d949381
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

<<<<<<< HEAD
=======
    public int getNivel() {
        return nivel;
    }

>>>>>>> 7c396a327dedc009bd40a3c5838b40055d949381
    public int getHp() {
        return hp;
    }

    public int getHpAtual() {
        return hpAtual;
    }

    public void setHpAtual(int hpAtual) {
        this.hpAtual = hpAtual;
    }

<<<<<<< HEAD
    public void perdeHp(double dano) {
=======
    public void perdeHp (double dano){
>>>>>>> 7c396a327dedc009bd40a3c5838b40055d949381
        int danoX = (int) dano;
        if (this.estaVivo()) {
            this.hpAtual -= danoX;
        }
    }

<<<<<<< HEAD
    public int getAtaque() {
        return ataque;
    }
=======
    public int getAtaque(){return ataque;}
>>>>>>> 7c396a327dedc009bd40a3c5838b40055d949381

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
        else if (tipo == Tipos.PEDRA)
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
            return -1;
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
<<<<<<< HEAD
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
=======
        int pkm1 = associaTipo(ataque.getTipo()), pkm2 = associaTipo(alvo.getTipos().get(0));
        return vantagem[pkm1][pkm2];

    }

    public static Pokemon getPokemonFromJSONObject(JSONObject obj){
>>>>>>> 7c396a327dedc009bd40a3c5838b40055d949381
        int id = Integer.parseInt(obj.get("Id").toString());

        ArrayList<Tipos> tipos = new ArrayList<Tipos>();

        tipos.add(tipoFromString(obj.get("Tipo 1").toString()));

<<<<<<< HEAD
        if (obj.get("Tipo 2").toString() != "") {
=======
        if(obj.get("Tipo 2").toString() != ""){
>>>>>>> 7c396a327dedc009bd40a3c5838b40055d949381
            tipos.add(tipoFromString(obj.get("Tipo 2").toString()));
        }

        int ataque = Integer.parseInt(obj.get("Ataque").toString());
        int defesa = Integer.parseInt(obj.get("Defesa").toString());
        int hp = Integer.parseInt(obj.get("HP").toString());

        String nome = obj.get("Nome").toString();
        return new Pokemon(nome, id, tipos, ataque, defesa, hp);
    }

}
