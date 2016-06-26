package br.com.primecall.dao;

import br.com.primecall.model.Problema;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.model.SelectItem;

/**
 *
 * @author filipe
 */
public class ProblemaDAO {

    private Statement comando;
    private Connection conexao;
    private Statement comandoNormal;

    public Boolean inserir(Problema problema) {
        String sql = "INSERT INTO problema VALUES (null, '" + problema.getDescricao() + "')";

        conectar();
        try {
            comando.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProblemaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Problema> buscarTodos() {

        String sql = "SELECT * FROM problema";
        ArrayList<Problema> listaProblemas = new ArrayList();

        try {
            conectar();
            ResultSet resultado = comando.executeQuery(sql);

            while (resultado.next()) {
                Problema problema = new Problema();
                problema.setId(resultado.getInt("id"));
                problema.setDescricao(resultado.getString("descricao"));
                listaProblemas.add(problema);
            }

            fechar();
            return listaProblemas;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Problema buscar(Problema p) {
        Problema problema = new Problema();
        String sql = "SELECT * FROM problema WHERE id = " + p.getId() + ";";
        ResultSet resultado = null;
        try {
            conectar();
            resultado = comandoNormal.executeQuery(sql);

            resultado.next();
            problema.setDescricao(resultado.getString("descricao"));
            problema.setId(resultado.getInt("id"));
            return p;

        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public void conectar() {
        try {
            conexao = Conexao.getConexao();
            comando = conexao.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ProblemaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fechar() {
        try {
            conexao.close();
            comando.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProblemaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
