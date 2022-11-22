import java.util.List;

public class Treinador extends Identificacao {
    private static int treinadoresCriados = 0;
    public String regiao;
    public int qtdPokemon;
    public List<Pokemon> time;

    public Treinador(String nome, String regiao) {
        this.nome = nome;
        this.regiao = regiao;
        this.id = treinadoresCriados;
        treinadoresCriados++;
        // TODO: times
    }

    public int getQtdPokemon() {
        return qtdPokemon;
    }

    public String getRegiao() {
        return regiao;
    }

    public List<Pokemon> getTime() {
        return time;
    }
}