package br.com.primecall.dao;

import br.com.primecall.model.Empresa;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author filipe
 */
public class EmpresaDAO {

    private Connection conexao;
    private Statement comando;
    private ResultSet resultado;

    public Boolean inserir(Empresa empresa) {

        String sql = "INSERT INTO empresa VALUES (null, '" + empresa.getNome() + "', '" + empresa.getCnpj() + "', '" + empresa.getResponsavel() + "')";

        try {
            conectar();
            comando.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public Boolean alterar(Empresa empresa) {

        String sql = "UPDATE empresa SET nome = '" + empresa.getNome() + "', cnpj = '" + empresa.getCnpj() + "', responsavel = '" + empresa.getResponsavel() + "' WHERE id = " + empresa.getId() + "  ";

        try {
            conectar();
            comando.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public Empresa buscar(int id) {
        Empresa empresa = new Empresa();
        String sql = "SELECT * FROM empresa WHERE id = " + id + " ";
        try {
            conectar();
            resultado = comando.executeQuery(sql);

            while (resultado.next()) {
                empresa.setId(resultado.getInt("id"));
                empresa.setNome(resultado.getString("nome"));
                empresa.setCnpj(resultado.getString("cnpj"));
                empresa.setResponsavel(resultado.getString("responsavel"));
            }
            return empresa;

        } catch (SQLException e) {
            return null;
        }
    }

    private void conectar() throws SQLException {
        try {
            conexao = Conexao.getConexao();
            comando = conexao.createStatement();
        } catch (SQLException e) {
        }
    }

    private void fechar() {
        try {
            comando.close();
            conexao.close();
        } catch (SQLException e) {
        }
    }
}
