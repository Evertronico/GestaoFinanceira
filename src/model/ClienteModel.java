package model;

import controller.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ClienteModel {

    // Lista todos os clientes da tabela no banco de dados
    public ArrayList<Cliente> liste() {

        // Cria uma coleção de objetos vazia
        ArrayList<Cliente> lista = new ArrayList<>();

        try {
            // Cria uma conexão com o banco de dados
            Conexao c = new Conexao();
            c.conecte();

            Statement st = c.conexao.createStatement();
            
            // ✅ Query corrigida com CASE e GROUP BY
            String sql = """
                SELECT 
                    c.*, 
                    MAX(CASE WHEN t.tipo = 'm' THEN t.numero END) AS celular,
                    MAX(CASE WHEN t.tipo = 'f' THEN t.numero END) AS fixo
                FROM cliente AS c
                LEFT JOIN telefone AS t ON t.cliente_id = c.cliente_id
                GROUP BY c.cliente_id
                ORDER BY c.nome;
            """;

            ResultSet rs = st.executeQuery(sql);

            // Percorre todos os resultados (linhas) retornados pela query
            while (rs.next()) {
                // Cria um objeto da classe Cliente com os dados corretos
                Cliente C = new Cliente(
                    rs.getInt("cliente_id"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getDate("data_nascimento"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("celular"),
                    rs.getString("fixo")
                );
                lista.add(C);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return lista;
    }

    // Grava ou atualiza um cliente
    public String grave(Cliente c) {
        String msg = "";

        try {
            Conexao C = new Conexao();
            C.conecte();

            String sql = "call p_salve_cliente(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = C.conexao.prepareStatement(sql);

            // Substitui todas as ? por valores dos atributos do objeto Cliente
            st.setInt(1, c.getClienteId());
            st.setString(2, c.getNome());
            st.setString(3, c.getCpf());
            st.setDate(4, c.getDataNascimento());
            st.setString(5, c.getEmail());
            st.setString(6, c.getSenha());
            st.setString(7, c.getCelular());
            st.setString(8, c.getFixo());

            // Executa a procedure
            st.execute();

            ResultSet rs = st.getResultSet();
            rs.next();
            msg = rs.getString(2);

            st.close();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return msg;
    }

    // Remove um cliente
    public String remova(int clienteId) {
        String msg = "";
        try {
            Conexao c = new Conexao();
            c.conecte();
            String sql = "call p_delete_cliente(?)";
            PreparedStatement st = c.conexao.prepareStatement(sql);
            st.setInt(1, clienteId);
            st.executeUpdate();

            ResultSet rs = st.getResultSet();
            rs.next();
            msg = rs.getString(2);
            st.close();
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return msg;
    }

    // Pesquisa clientes pelo nome
    public ArrayList<Cliente> pesquise(String termo) {
        ArrayList<Cliente> clientes = new ArrayList<>();

        try {
            Conexao c = new Conexao();
            c.conecte();

            // ✅ Query de pesquisa também corrigida com CASE
            String sql = """
                SELECT 
                    c.*, 
                    MAX(CASE WHEN t.tipo = 'Celular' THEN t.numero END) AS celular,
                    MAX(CASE WHEN t.tipo = 'Fixo' THEN t.numero END) AS fixo
                FROM cliente AS c
                LEFT JOIN telefone AS t ON t.cliente_id = c.cliente_id
                WHERE c.nome LIKE ?
                GROUP BY c.cliente_id
                ORDER BY c.nome;
            """;

            PreparedStatement st = c.conexao.prepareStatement(sql);
            st.setString(1, "%" + termo + "%");

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Cliente C = new Cliente(
                    rs.getInt("cliente_id"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getDate("data_nascimento"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("celular"),
                    rs.getString("fixo")
                );
                clientes.add(C);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return clientes;
    }
}
