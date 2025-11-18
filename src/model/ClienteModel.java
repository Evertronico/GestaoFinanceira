package model;

import controller.Cliente;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ClienteModel {

    // lista todos os clientes
    public ArrayList<Cliente> liste() {
        ArrayList<Cliente> lista = new ArrayList<>();
        try {
            Conexao c = new Conexao();
            c.conecte();
            Statement st = c.conexao.createStatement();
            String sql = "SELECT c.*, " +
                         "MAX(CASE WHEN t.tipo = 'm' THEN t.numero END) AS celular, " +
                         "MAX(CASE WHEN t.tipo = 'f' THEN t.numero END) AS telefone_fixo " +
                         "FROM cliente c " +
                         "LEFT JOIN telefone t ON c.cliente_id = t.cliente_id " +
                         "GROUP BY c.cliente_id ORDER BY c.nome";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Cliente cli = new Cliente(
                    rs.getInt("cliente_id"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getDate("data_nascimento"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("celular"),
                    rs.getString("telefone_fixo")
                );
                lista.add(cli);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return lista;
    }

    // grava ou atualiza cliente via procedure
    public String grave(Cliente cte) {
        String msg = "";
        try {
            Conexao c = new Conexao();
            c.conecte();
            String sql = "call p_salve_cliente(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = c.conexao.prepareStatement(sql);
            st.setInt(1, cte.getClienteId());
            st.setString(2, cte.getNome());
            st.setString(3, cte.getCpf());
            st.setDate(4, cte.getDataNascimento());
            st.setString(5, cte.getEmail());
            st.setString(6, cte.getSenha());
            st.setString(7, cte.getCelular());
            st.setString(8, cte.getTelefoneFixo());
            st.execute();
            ResultSet rs = st.getResultSet();
            if (rs.next()) msg = rs.getString(2);
            st.close();
        } catch (ClassNotFoundException | SQLException ex) {
            msg = ex.getMessage();
            JOptionPane.showMessageDialog(null, msg);
        }
        return msg;
    }

    // remove cliente
    public String remova(int clienteId) {
        String msg = "";
        try {
            Conexao c = new Conexao();
            c.conecte();
            PreparedStatement st = c.conexao.prepareStatement("call p_delete_cliente(?)");
            st.setInt(1, clienteId);
            st.execute();
            ResultSet rs = st.getResultSet();
            if (rs.next()) msg = rs.getString(2);
            st.close();
        } catch (ClassNotFoundException | SQLException ex) {
            msg = ex.getMessage();
            JOptionPane.showMessageDialog(null, msg);
        }
        return msg;
    }

    // pesquisa cliente
    public ArrayList<Cliente> pesquise(String termo) {
        ArrayList<Cliente> lista = new ArrayList<>();
        try {
            Conexao c = new Conexao();
            c.conecte();
            String sql = "SELECT c.*, " +
                         "MAX(CASE WHEN t.tipo = 'm' THEN t.numero END) AS celular, " +
                         "MAX(CASE WHEN t.tipo = 'f' THEN t.numero END) AS telefone_fixo " +
                         "FROM cliente c " +
                         "LEFT JOIN telefone t ON c.cliente_id = t.cliente_id " +
                         "WHERE c.nome LIKE ? GROUP BY c.cliente_id ORDER BY c.nome";
            PreparedStatement st = c.conexao.prepareStatement(sql);
            st.setString(1, "%" + termo + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Cliente cli = new Cliente(
                    rs.getInt("cliente_id"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getDate("data_nascimento"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("celular"),
                    rs.getString("telefone_fixo")
                );
                lista.add(cli);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return lista;
    }
}