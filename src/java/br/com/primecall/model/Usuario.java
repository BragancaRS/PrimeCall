package br.com.primecall.model;

/**
 *
 * @author root
 */
public class Usuario extends Pessoa {

    private String login;
    private String senha;
    private int idUsuario;
    private int perfil;

    public Usuario() {
        super();
        this.idUsuario = 0;
        this.login = "";
        this.senha = "";
        this.perfil = 0;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getPerfil() {
        return perfil;
    }

    public void setPerfil(int perfil) {
        this.perfil = perfil;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

}
