package br.com.primecall.dao;

import br.com.primecall.model.Chamado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author filipe
 */
public class ChamadoDAO {

    private Connection conexao;
    private PreparedStatement comando;
    private Statement comandoNormal;

    private Chamado chamado = new Chamado();
    private ArrayList<Chamado> listaChamados = new ArrayList();
    private int idChamado = 0;

    Calendar c = Calendar.getInstance();
    Date data = c.getTime();
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date date = new Date();
    DateFormat formataData = DateFormat.getDateInstance();

    public int inserir(Chamado chamado) throws SQLException {

        String dataAtual = dateFormat.format(date);
        //formataData.format(data);
        conectar();
        String sql = "INSERT INTO chamado (descricao, idFuncionarioAbertura, idFuncionarioAtendimento, cliente_pessoa_id, "
                + "dataAbertura, dataFechamento, problema_id) VALUES  (?, ?, ?, ?, ?, ?, ?)";
        comando = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        comando.setString(1, chamado.getDescricao());
        comando.setInt(2, chamado.getIdFuncionarioAbertura());
        comando.setInt(3, 0);
        comando.setInt(4, chamado.getIdPessoa());
        comando.setString(5, dataAtual);
        comando.setString(6, "null");
        comando.setInt(7, chamado.getIdProblema());

        try {
            comando.executeUpdate();
            ResultSet rs = comando.getGeneratedKeys();

            rs.next();
            idChamado = rs.getInt(1);

        } catch (SQLException ex) {

        } finally {
            fechar();
            return idChamado;
        }
    }

    public ArrayList<Chamado> buscarTodos() {

        String sql = "SELECT * FROM chamado";
 
        try {
            conectar();
            ResultSet resultado = comandoNormal.executeQuery(sql);

            while (resultado.next()) {
                chamado.setId(resultado.getInt("id"));
                chamado.setDescricao(resultado.getString("descricao"));
                chamado.setIdFuncionarioAbertura(resultado.getInt("idFuncionarioAbertura"));
                chamado.setIdFuncionarioAtendimento(resultado.getInt("idFuncionarioAtendimento"));
                chamado.setIdPessoa(resultado.getInt("cliente_pessoa_id"));
                chamado.setDataAbertura(resultado.getString("dataAbertura"));
                chamado.setDataFechamento(resultado.getString("dataFechamento"));
                chamado.setIdProblema(resultado.getInt("problema_id"));
                listaChamados.add(chamado);
            }

//            fechar();
            return listaChamados;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private void conectar() {
        try {
            conexao = Conexao.getConexao();
            comandoNormal = conexao.createStatement();
        } catch (SQLException ex) {
        }
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
