package br.com.primecall.model;

/**
 *
 * @author filipe
 */
public class Chamado {

    private int id;
    private String descricao;
    private int idFuncionarioAbertura;
    private int idFuncionarioAtendimento;
    private int idPessoa;
    private String dataAbertura;
    private String dataFechamento;
    private int idProblema;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdFuncionarioAbertura() {
        return idFuncionarioAbertura;
    }

    public void setIdFuncionarioAbertura(int idFuncionarioAbertura) {
        this.idFuncionarioAbertura = idFuncionarioAbertura;
    }

    public int getIdFuncionarioAtendimento() {
        return idFuncionarioAtendimento;
    }

    public void setIdFuncionarioAtendimento(int idFuncionarioAtendimento) {
        this.idFuncionarioAtendimento = idFuncionarioAtendimento;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(String dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public String getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(String dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public int getIdProblema() {
        return idProblema;
    }

    public void setIdProblema(int idProblema) {
        this.idProblema = idProblema;
    }
}
