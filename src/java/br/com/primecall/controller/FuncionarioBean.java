package br.com.primecall.controller;

import br.com.primecall.model.Funcionario;
import br.com.primecall.dao.FuncionarioDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

/**
 *
 * @author filipe
 */
@ManagedBean
public class FuncionarioBean {

    private Funcionario usuario = new Funcionario();
    private FuncionarioDAO banco = new FuncionarioDAO();
    private Boolean result;

    public String inserir() throws SQLException {
        if (banco.inserir(usuario)) {
            return "funcionarioTrue";
        }
        return "erro";
    }

    public ArrayList<SelectItem> getBuscarTodos() {
        ArrayList<SelectItem> listaFuncionarios = new ArrayList();
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        FuncionarioDAO f = new FuncionarioDAO();
        
        funcionarios = f.listarFuncionarios();
        
        for (Funcionario funcionario : funcionarios) {
            listaFuncionarios.add(new SelectItem(funcionario,funcionario.getLogin()));
        }
        return listaFuncionarios;
    }

    public String autenticar() {
        return "index.xhtml";
    }

    public String limpar() {
        usuario.setLogin(new String());
        usuario.setSenha(new String());
        return "login";
    }

    public Funcionario getUsuario() {
        return usuario;
    }

    public void setUsuario(Funcionario usuario) {
        this.usuario = usuario;
    }
}
