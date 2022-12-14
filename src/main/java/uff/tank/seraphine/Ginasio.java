package uff.tank.seraphine;

public class Ginasio {
    private String nome;
    private String regiao;
    private LiderGin lider;

    public Ginasio(String nome, String regiao) { // Cria um ginásio sem lider
        this.nome = nome;
        this.regiao = regiao;
        this.lider = null;
    }

    public Ginasio(String nome, String regiao, LiderGin lider) { // Cria um ginásio com lider
        this.nome = nome;
        this.regiao = regiao;
        this.lider = lider;
    }

    public String getRegiao() {
        return regiao;
    }

    public LiderGin getLider() {
        return lider;
    }

    public void setLider(LiderGin lider) {
        this.lider = lider;
    }

}
