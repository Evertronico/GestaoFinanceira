package model;

import controller.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ClienteModel {

    // (CREATE/UPDATE)
    public String grave(Cliente c) {
        // declara a variável de retorno
        String msg = "";

        try {
            // abre a conexão com o banco de dados
            Conexao con = new Conexao();
            con.conecte();

            // Chamada da procedure p_salve_cliente
            String sql = "call p_salve_cliente(?, ?, ?, ?, ?, ?, ?, ?)"; // Usa a procedure fornecida

            PreparedStatement st = con.conexao.prepareStatement(sql);

            // Substituição dos parâmetros
            st.setInt(1, c.getClienteId());
            st.setString(2, c.getNome());
            st.setString(3, c.getCpf());
            st.setDate(4, c.getDataNascimento());
            st.setString(5, c.getEmail());
            st.setString(6, c.getSenha());
            st.setString(7, c.getNumeroTelefoneMovel());
            st.setString(8, c.getNumeroTelefoneFixo());

            st.executeUpdate();

            ResultSet rs = st.getResultSet();
            rs.next();
            msg = rs.getString(2); // Retorna a mensagem da procedure

            st.close();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return msg;
    }

    // READ
    public ArrayList<Cliente> liste() {
        // declara uma coleção de objetos do tipo Cliente
        ArrayList<Cliente> lista = new ArrayList<>();

        try {
            // conecta com o banco de dados
            Conexao con = new Conexao();
            con.conecte();

            // cria um objeto capaz de executar uma query no banco de dados
            Statement st = con.conexao.createStatement();
            
            // Query para listar clientes
            String sql = "SELECT c.cliente_id, c.nome, c.cpf, c.data_nascimento, c.email, c.senha, " +
                         "t_movel.numero AS numeroTelefoneMovel, t_fixo.numero AS numeroTelefoneFixo " +
                         "FROM cliente c " +
                         "LEFT JOIN telefone t_movel ON c.cliente_id = t_movel.cliente_id AND t_movel.tipo = 'm' " +
                         "LEFT JOIN telefone t_fixo ON c.cliente_id = t_fixo.cliente_id AND t_fixo.tipo = 'f' " +
                         "ORDER BY c.nome";

            // recupera os dados
            ResultSet rs = st.executeQuery(sql);

            // percorre todas as linhas dentro do ResultSet
            while (rs.next()) {
                // cria um objeto da classe Cliente (igual a como se faz no ObjetivoModel)
                Cliente c = new Cliente(
                    rs.getInt("cliente_id"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getDate("data_nascimento"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("numeroTelefoneMovel"),
                    rs.getString("numeroTelefoneFixo")
                );
                // adiciona o objeto na coleção
                lista.add(c);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        // retorna a lista
        return lista;
    }
    
    // Método para pesquisar clientes
    public ArrayList<Cliente> pesquise(String termo) {
        // determina uma coleção de objetos vazia
        ArrayList<Cliente> lista = new ArrayList<>();

        try {
            // cria uma conexão com o banco de dados
            Conexao con = new Conexao();
            con.conecte();
            
            // Query de pesquisa
            String sql = "SELECT c.cliente_id, c.nome, c.cpf, c.data_nascimento, c.email, c.senha, " +
                         "t_movel.numero AS numeroTelefoneMovel, t_fixo.numero AS numeroTelefoneFixo " +
                         "FROM cliente c " +
                         "LEFT JOIN telefone t_movel ON c.cliente_id = t_movel.cliente_id AND t_movel.tipo = 'm' " +
                         "LEFT JOIN telefone t_fixo ON c.cliente_id = t_fixo.cliente_id AND t_fixo.tipo = 'f' " +
                         "WHERE c.nome LIKE ? OR c.cpf LIKE ? OR c.email LIKE ? " + // Usa 3 parâmetros para a busca
                         "ORDER BY c.nome";

            PreparedStatement st = con.conexao.prepareStatement(sql);
            String termoFormatado = "%" + termo + "%";
            
            // substitui a ? pelo termo de busca (para os 3 campos)
            st.setString(1, termoFormatado);
            st.setString(2, termoFormatado);
            st.setString(3, termoFormatado);

            st.executeQuery();

            ResultSet rs = st.getResultSet();

            // percorre todos os resultados
            while (rs.next()) {
                // cria objeto da classe Cliente
                Cliente c = new Cliente(
                    rs.getInt("cliente_id"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getDate("data_nascimento"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("numeroTelefoneMovel"),
                    rs.getString("numeroTelefoneFixo")
                );
                // adiciona o objeto na coleção
                lista.add(c);
            }
        } catch (SQLException | ClassNotFoundException ex) { 
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        // retorna a coleção de objetos
        return lista;
    }


    // Método para remover um cliente (DELETE)
    public String remova(int clienteId) {
        // define uma mensagem inicial
        String msg = "";

        try {
            // conecta com o banco de dados
            Conexao con = new Conexao();
            con.conecte();

            // define a query que será executada no banco de dados
            String sql = "call p_delete_cliente(?)"; // Usa a procedure fornecida
            
            PreparedStatement st = con.conexao.prepareStatement(sql);
            // define o valor da chave primária
            st.setInt(1, clienteId);

            st.executeUpdate();
            
            // recupera a mensagem retornada pela procedure
            ResultSet rs = st.getResultSet();
            rs.next();
            msg = rs.getString(2);

            st.close();
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return msg;
    }
}