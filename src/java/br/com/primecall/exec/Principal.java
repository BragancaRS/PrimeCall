package br.com.primecall.exec;

import br.com.primecall.dao.ClienteDAO;
import br.com.primecall.model.Cliente;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class Principal {

    public static void main(String[] args) {
        ClienteDAO c = new ClienteDAO();
        ArrayList<Cliente> c2 = new ArrayList();

        Cliente cliente = new Cliente();
        cliente.setNome("bbb");
        cliente.setLogin("abbbaa.aa");
        cliente.setId(1);
        cliente.setLogin("spdkfmnspdkfmsdpfkm.sdfsdf");
        try {
            c.inserir(cliente);
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

        // System.out.println("Nome: " + cliente.getNome() + " - Usuario " + cliente.getLogin() + " usuarioID " + cliente.getIdUsuario());
    }

}
