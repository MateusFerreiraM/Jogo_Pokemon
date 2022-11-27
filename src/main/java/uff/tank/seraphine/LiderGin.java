package uff.tank.seraphine;

public class LiderGin extends Treinador {// Lider de Ginásio é um tipo especial de treinador

    private Tipos especialidade;// Tipo de pokemon q o lider foca

    public LiderGin(String nome, String regiao, Tipos especialidade) {
        super(nome, regiao);
        this.especialidade = especialidade;
    }

    public Tipos getEspecialidade() {
        return this.especialidade;
    }

    public void setEspecialidade(Tipos especialidade) {
        this.especialidade = especialidade;
    }

    /*
     * TODO: Apenas pokémon com o tipo da especialidade do lider podem
     * ser parte de seu time.
     */

    /* TODO: Insignia de ginásio */
}
