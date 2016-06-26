package br.com.primecall.dao;

import br.com.primecall.model.Cliente;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author filipe
 */
public class ClienteDAO {

    private Connection conexao;
    private Statement comando;

    private Cliente cliente = new Cliente();
    private ArrayList<Cliente> listaClientes = new ArrayList();
    private ArrayList<Cliente> listaClientesTable = new ArrayList();

    public Boolean inserir(Cliente novoCliente) throws SQLException {

        UsuarioDAO u = new UsuarioDAO();
        int idUsuario = u.inserir(novoCliente);
        System.out.println("ID --- " + idUsuario);
        String sql = "INSERT INTO cliente (usuario_id) VALUES (" + idUsuario + "; )";
        try {
            conectar();
            comando.executeUpdate(sql);
            fechar();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public ArrayList<Cliente> buscarTodos() {

        String sql = "SELECT * FROM pessoa,cliente,usuario WHERE cliente.usuario_id = usuario.id AND "
                + "usuario.pessoa_id = pessoa.id;";
        System.out.println(sql);
        try {
            conectar();
            ResultSet resultado = comando.executeQuery(sql);

            while (resultado.next()) {
                cliente = new Cliente();
                cliente.setId(resultado.getInt("cliente.id"));
                cliente.setNome(resultado.getString("pessoa.nome"));
                cliente.setSobrenome(resultado.getString("pessoa.sobrenome"));
                cliente.setCpf(resultado.getString("pessoa.cpf"));
                cliente.setTelefone(resultado.getString("pessoa.telefone"));
                cliente.setDataCadastro(resultado.getString("pessoa.dataCadastro"));
                cliente.setIdUsuario(resultado.getInt("cliente.usuario_id"));
                cliente.setIdPessoa(resultado.getInt("pessoa.id"));
                cliente.setLogin(resultado.getString("usuario.login"));
                cliente.setSenha(resultado.getString("usuario.senha"));
                cliente.setPerfil(resultado.getInt("usuario.perfil"));
                listaClientes.add(cliente);
            }

            fechar();
            return listaClientes;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Cliente buscar(Cliente c) {

        String sql = "SELECT * FROM pessoa, cliente, usuario WHERE " + c.getId()
                + " = cliente.id AND cliente.usuario_id = usuario.id AND usuario.pessoa_id = pessoa.id; ";

        System.out.println(sql);
        try {
            conectar();
            ResultSet resultado = comando.executeQuery(sql);

            while (resultado.next()) {
                cliente.setId(resultado.getInt("id"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setSobrenome(resultado.getString("sobrenome"));
                cliente.setCpf(resultado.getString("cpf"));
                cliente.setTelefone(resultado.getString("telefone"));
                cliente.setDataCadastro(resultado.getString("dataCadastro"));
                cliente.setIdPessoa(resultado.getInt("pessoa.id"));
                cliente.setIdUsuario(resultado.getInt("usuario.id"));
                cliente.setLogin(resultado.getString("usuario.login"));
                cliente.setPerfil(resultado.getInt("perfil"));
                cliente.setSenha(resultado.getString("usuario.senha"));
            }

            fechar();
            return cliente;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private void conectar() {
        try {
            conexao = Conexao.getConexao();
            comando = conexao.createStatement();
        } catch (SQLException ex) {
        }
    }

    private void fechar() {
        try {
            conexao.close();
            comando.close();
        } catch (SQLException ex) {
        }
    }
}
