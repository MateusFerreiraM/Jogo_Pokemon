import java.util.List;

public class Ginasio extends Identificacao {

    public static int ginasiosTotal;
    private List<Pokemon> pokemonList;
    private String regiao;
    private String liderDoGinasio;

    public Ginasio(String nome, List<Tipos> tipos, List<Pokemon> lista, String regiao, String lider) {

        ginasiosTotal++;
        this.id = ginasiosTotal;
        this.nome = nome;
        this.tipos = tipos;
        this.pokemonList = lista;
        this.regiao = regiao;
        this.liderDoGinasio = lider;
    }

    public List<Pokemon> getPokemonList() {
        return pokemonList;
    }

    public String getRegiao() {
        return regiao;
    }

    public String getLiderDoGinasio() {
        return liderDoGinasio;
    }
}
