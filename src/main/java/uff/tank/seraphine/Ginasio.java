package uff.tank.seraphine;

public class Ginasio extends Identificacao {

    public static int ginasiosTotal;
    private String regiao;
    private LiderGin lider;

    public Ginasio(String nome, String regiao) { // Cria um ginásio sem lider
        this.id = ginasiosTotal;
        this.nome = nome;
        this.regiao = regiao;
        this.lider = null;
        ginasiosTotal++;
    }

    public Ginasio(String nome, String regiao, LiderGin lider) { // Cria um ginásio com lider
        this.id = ginasiosTotal;
        this.nome = nome;
        this.regiao = regiao;
        this.lider = lider;
        ginasiosTotal++;
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

    public static int getGinasiosTotal() {
        return ginasiosTotal;
    }

}
