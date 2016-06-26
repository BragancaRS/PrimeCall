package br.com.primecall.controller;

import br.com.primecall.model.Empresa;
import br.com.primecall.dao.EmpresaDAO;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author filipe
 */
@ManagedBean
public class EmpresaBean {

    private Empresa empresa = new Empresa();
    private EmpresaDAO banco = new EmpresaDAO();
    int idEmpresa;

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String cadastrarEmpresa() {

        Boolean resposta = banco.inserir(empresa);
        if (resposta) {
            return "sucessoEmpresa";
        }
        return null;
    }

    public String buscarEmpresa() {
        empresa.setId(idEmpresa);
        empresa = banco.buscar(empresa.getId());
        return "";
    }

    public void alterarEmpresa() {
        banco.alterar(empresa);
        limpar();
    }

    private void limpar() {
        empresa = new Empresa();
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

}
