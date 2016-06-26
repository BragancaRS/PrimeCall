/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.primecall.controller;

import br.com.primecall.dao.ChamadoDAO;
import br.com.primecall.model.Chamado;
import java.sql.SQLException;
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
public class ChamadoBean {

    private Chamado novoChamado = new Chamado();
    private ChamadoDAO banco = new ChamadoDAO();

    public void inserir() throws SQLException {
        try {
            banco.inserir(novoChamado);
            FacesMessage mensagem = new FacesMessage("Chamado aberto com sucesso!");
            FacesContext.getCurrentInstance().addMessage("inserido", mensagem);
            limpar();
        } catch (Exception e) {
            System.out.println("Estourou a exceção ao inserir o chamado.");
        }
    }

    public ArrayList<Chamado> getBuscarTodos() {
        ArrayList<Chamado> listaChamados = new ArrayList();
        listaChamados = banco.buscarTodos();
        return listaChamados;
    }

    private void limpar() {
        novoChamado = new Chamado();
    }

    public Chamado getNovoChamado() {
        return novoChamado;
    }
}
