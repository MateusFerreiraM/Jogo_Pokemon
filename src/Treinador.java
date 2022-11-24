import java.util.ArrayList;

public class Treinador extends Identificacao {
    private static int TotalTreinadores = 0;
    private String regiao;
    public int qtdPokemon;
    public ArrayList<Pokemon> time;

    public Treinador(String nome, String regiao) {
        this.nome = nome;
        this.regiao = regiao;
        this.id = TotalTreinadores;
        this.time = new ArrayList<Pokemon>();
        TotalTreinadores++;
    }

    public int getQtdPokemon() {
        return qtdPokemon;
    }

    public String getRegiao() {
        return regiao;
    }

    public ArrayList<Pokemon> getTime() {
        return time;
    }

    public static int getTotalTreinadores(){
        return TotalTreinadores;
    }

    public void addPokemon(Pokemon pokemon){
        this.time.add(pokemon);
    }
}