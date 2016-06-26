package br.com.primecall.dao;

import br.com.primecall.model.Funcionario;
import br.com.primecall.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class FuncionarioDAO {

    private Connection conexao;
    private PreparedStatement comando = null;
    private Statement comandoNormal;
    private ResultSet resultado;
    private ArrayList<SelectItem> listaFuncionarios = new ArrayList();
    private Funcionario funcionario = new Funcionario();

    public Boolean inserir(Funcionario novoFuncionario) throws SQLException {
        PessoaDAO pessoa = new PessoaDAO();
        int idPessoa = pessoa.inserir(novoFuncionario);

        conectar();
        String sql = "INSERT INTO funcionario (login, senha, perfil, habilitado, pessoa_id) VALUES  (?, ?, ?, ?, ?)";
        comando = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        comando.setString(1, novoFuncionario.getLogin());
        comando.setString(2, novoFuncionario.getSenha());
        comando.setInt(3, 1);
        comando.setBoolean(4, true);
        comando.setInt(5, idPessoa);

        try {
            comando.executeUpdate();
            fechar();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public ArrayList<Funcionario> listarFuncionarios() {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        UsuarioDAO u = new UsuarioDAO();

        ResultSet rs;

        String sql = "SELECT * FROM funcionario;";
        try {
            conectar();
            rs = comando.executeQuery(sql);

            while (resultado.next()) {
                Funcionario f = new Funcionario();
                f.setId(rs.getInt("id"));
                f.setHabilitado(rs.getBoolean("habilitado"));
                f.setIdUsuario(rs.getInt("usuario_id"));
                Usuario usuario = new Usuario();
                usuario = u.buscar(f);
                f.setLogin(usuario.getLogin());
                f.setCpf(usuario.getCpf());
                f.setIdPessoa(usuario.getIdPessoa());
                f.setNome(usuario.getNome());
                f.setSenha(usuario.getSenha());
                f.setSobrenome(usuario.getSobrenome());
                f.setDataCadastro(usuario.getDataCadastro());
                f.setPerfil(usuario.getPerfil());
                f.setTelefone(usuario.getTelefone());
                funcionarios.add(f);
            }

            return funcionarios;

        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public Funcionario buscarFuncionario(Funcionario f) {
        Funcionario funcionario = new Funcionario();
        UsuarioDAO u = new UsuarioDAO();

        ResultSet rs;

        String sql = "SELECT * FROM funcionario;";
        try {
            conectar();
            rs = comando.executeQuery(sql);

            resultado.next();
            f.setId(rs.getInt("id"));
            f.setHabilitado(rs.getBoolean("habilitado"));
            f.setIdUsuario(rs.getInt("usuario_id"));
            Usuario usuario = new Usuario();
            usuario = u.buscar(f);
            f.setLogin(usuario.getLogin());
            f.setCpf(usuario.getCpf());
            f.setIdPessoa(usuario.getIdPessoa());
            f.setNome(usuario.getNome());
            f.setSenha(usuario.getSenha());
            f.setSobrenome(usuario.getSobrenome());
            f.setDataCadastro(usuario.getDataCadastro());
            f.setPerfil(usuario.getPerfil());
            f.setTelefone(usuario.getTelefone());

            return funcionario;

        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    private void conectar() throws SQLException {
        try {
            conexao = Conexao.getConexao();
            comandoNormal = conexao.createStatement();
        } catch (SQLException e) {
        }
    }

    public boolean alterarFuncionario(Funcionario f) {
        ResultSet rs;
        String sql = "UPDATE funcionario SET habilitado = ? WHERE id = ?;";
        UsuarioDAO u = new UsuarioDAO();
        try {
            u.alterarusuario(f);
            comando.setBoolean(1, f.isHabilitado());
            comando.executeUpdate(sql);
        } catch (Exception e) {
        }

        return false;
    }

    private void fechar() {
        try {
            comando.close();
            comandoNormal.close();
            conexao.close();
        } catch (SQLException e) {
        }
    }
}
