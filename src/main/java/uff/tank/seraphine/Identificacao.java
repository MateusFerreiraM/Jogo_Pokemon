package uff.tank.seraphine;

import java.util.*;

public abstract class Identificacao {

    protected int id;
    protected String nome;
    protected List<Tipos> tipos;

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
