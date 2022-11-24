public class Ginasio extends Identificacao {

    public static int ginasiosTotal;
    private String regiao;
    private LiderGin lider;
    private Tipos especialidade;//A especialidade de um ginásio é a especialidade de seu lider

    public Ginasio(String nome, String regiao) { //Cria um ginásio sem lider
        this.id = ginasiosTotal;
        this.nome = nome;
        this.regiao = regiao;
        this.lider = null;
        this.especialidade = null;
        ginasiosTotal++;
    }

    public Ginasio(String nome, String regiao, LiderGin lider) { //Cria um ginásio com lider
        this.id = ginasiosTotal;
        this.nome = nome;
        this.regiao = regiao;
        this.lider = lider;
        this.especialidade = lider.getEspecialidade();
        ginasiosTotal++;
    }

    public String getRegiao() {
        return regiao;
    }

    public LiderGin getLider() {
        return lider;
    }

    public void setLider(LiderGin lider){
        this.lider = lider;
        this.especialidade = lider.getEspecialidade();
    }

    public static int getGinasiosTotal() {
        return ginasiosTotal;
    }

    public Tipos getEspecialidade() {
        return especialidade;
    }
}
