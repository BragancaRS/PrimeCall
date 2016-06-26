package br.com.primecall.model;

/**
 *
 * @author filipe
 */
public class Cliente extends Usuario {

    private int id;
    

    public Cliente() {
        super();
        this.id = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
