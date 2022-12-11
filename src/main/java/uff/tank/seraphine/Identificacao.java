package uff.tank.seraphine;

import java.util.*;

public class Identificacao {

    protected int id;
    protected String nome;
    protected List<Tipos> tipos;

    /*
     * public void setId(int id) {
     * this.id = id;
     * }
     * 
     * public void setNome(String nome) {
     * this.nome = nome;
     * }
     * 
     * public void setTipo(Tipos tipo) {
     * this.tipo = tipo;
     * }
     */

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<Tipos> getTipos() {
        return tipos;
    }

}
