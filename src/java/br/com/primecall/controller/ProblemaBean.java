package br.com.primecall.controller;

import br.com.primecall.model.Problema;
import br.com.primecall.dao.ProblemaDAO;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author filipe
 */
@ManagedBean
public class ProblemaBean {

    private Problema novoProblema = new Problema();
    private ProblemaDAO banco = new ProblemaDAO();

    public void inserir() {

        try {
            banco.inserir(novoProblema);
            FacesMessage mensagem = new FacesMessage("Cadastrado com sucesso!");
            FacesContext.getCurrentInstance().addMessage("inserido", mensagem);
            limpar();
        } catch (Exception e) {
            System.out.println("Estourou a exceção ao inserir o problema.");
        }
    }

    public ArrayList<SelectItem> getBuscarTodos() {
        ArrayList<SelectItem> listaProblemas = new ArrayList();
        ArrayList<Problema> problemas = new ArrayList<>();
        ProblemaDAO p = new ProblemaDAO();

        problemas = p.buscarTodos();

        for (Problema problema : problemas) {
            listaProblemas.add(new SelectItem(problema, problema.getDescricao()));
        }
        return listaProblemas;
    }

    private void limpar() {
        novoProblema = new Problema();
    }

    public Problema getNovoProblema() {
        return novoProblema;
    }

    public void setNovoProblema(Problema novoProblema) {
        this.novoProblema = novoProblema;
    }
}
