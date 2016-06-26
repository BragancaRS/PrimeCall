package br.com.primecall.model;

/**
 *
 * @author filipe
 */
public class Funcionario extends Usuario {

    private int id;
    private boolean habilitado;

    public Funcionario() {
        super();
        this.habilitado = false;
        this.id = 0;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
