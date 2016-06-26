package br.com.primecall.model;

/**
 *
 * @author filipe
 */
public class Pessoa {

    private int idPessoa;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String telefone;
    private String dataCadastro;

    public Pessoa() {
        this.idPessoa = 0;
        this.nome = "";
        this.sobrenome = "";
        this.cpf = "";
        this.telefone = "";
        this.dataCadastro = "";
    }

    public Pessoa(int idPessoa, String nome, String sobrenome, String cpf, String telefone, String dataCadastro) {
        this.idPessoa = idPessoa;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataCadastro = dataCadastro;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

}
