package br.com.primecall.dao;

import br.com.primecall.model.Pessoa;
import br.com.primecall.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class UsuarioDAO {

    private Connection conexao;
    private PreparedStatement comando = null;
    private Statement comandoNormal;
    private ResultSet resultado;

    public int inserir(Usuario usuario) throws SQLException {
        int idUsuario = 0;
        PessoaDAO p = new PessoaDAO();
        usuario.setIdPessoa(p.inserir(usuario));
        conectar();
        String sql = "INSERT INTO usuario (login, senha, pessoa_id, perfil) VALUES  (?, ?, ?, ?)";
        comando = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        comando.setString(1, usuario.getLogin());
        comando.setString(2, usuario.getSenha());
        comando.setInt(3, usuario.getIdPessoa());
        comando.setInt(4, usuario.getPerfil());

        try {
            comando.executeUpdate();
            ResultSet rs = comando.getGeneratedKeys();
            idUsuario = rs.getInt(1);

        } catch (SQLException ex) {

        } finally {
            fechar();
        }
        return idUsuario;
    }

    public Usuario buscar(Usuario u) {
        Usuario usuario = new Usuario();
        String sql = "SELECT * FROM usuario WHERE id = " + u.getIdUsuario() + " ";
        PessoaDAO p = new PessoaDAO();
        try {
            Pessoa pessoa = new Pessoa();
            pessoa = p.buscar(u);
            usuario.setNome(pessoa.getNome());
            usuario.setSobrenome(pessoa.getSobrenome());
            usuario.setCpf(pessoa.getCpf());
            usuario.setTelefone(pessoa.getTelefone());
            usuario.setDataCadastro(pessoa.getDataCadastro());
            usuario.setIdPessoa(pessoa.getIdPessoa());

            conectar();

            resultado = comando.executeQuery(sql);
            resultado.next();

            usuario.setLogin(resultado.getString("login"));
            usuario.setSenha(resultado.getString("senha"));
            usuario.setPerfil(resultado.getInt("perfil"));
            usuario.setIdUsuario(resultado.getInt("id"));

            return usuario;

        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public boolean alterarusuario(Usuario u) {
        String sql = "UPDATE usuario SET login = ?, senha = ?, perfil = ? WHERE id = ?;";
        PessoaDAO p = new PessoaDAO();
        try {
            p.alterarPessoa(u);
            comando.setString(1, u.getLogin());
            comando.setString(2, u.getSenha());
            comando.setInt(3, u.getPerfil());
            comando.setInt(4, u.getIdUsuario());
            comando.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        return false;
    }

    private void conectar() {
        try {
            conexao = Conexao.getConexao();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fechar() {
        try {
            conexao.close();
            comando.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
