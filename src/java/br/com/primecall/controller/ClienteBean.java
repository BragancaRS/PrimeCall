package br.com.primecall.controller;

import br.com.primecall.model.Cliente;
import br.com.primecall.dao.ClienteDAO;
import br.com.primecall.model.Chamado;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author filipe
 */
@ManagedBean
@ViewScoped
public class ClienteBean {

    private Cliente novoCliente = new Cliente();
    private ClienteDAO banco = new ClienteDAO();

    public String inserir() throws SQLException {
        if (banco.inserir(novoCliente)) {

            return "clienteTrue";
        }
        return "erro";
    }

    public ArrayList<SelectItem> getBuscarTodos() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<SelectItem> listaClientes = new ArrayList();
        ClienteDAO c = new ClienteDAO();

        clientes = c.buscarTodos();

        for (Cliente cliente : clientes) {
            listaClientes.add(new SelectItem(cliente, cliente.getLogin()));
        }
        return listaClientes;
    }

    public ArrayList<Cliente> getBuscarTodosTable() {
        ArrayList<Cliente> clientes = new ArrayList();
        ClienteDAO c = new ClienteDAO();
        clientes = c.buscarTodos();
        return clientes;
    }

    public Cliente getNovoCliente() {
        return novoCliente;
    }
}
