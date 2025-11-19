package model;

import controller.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ClienteModel {
    // lista todos os objetivos da tabela no banco de dados
    public ArrayList<Cliente> liste() {
        
        // cria uma coleção de objetos vazia
        ArrayList<Cliente> lista = new ArrayList<>();

        try {
            // cria uma conexão com o banco de dados
            Conexao c = new Conexao();
            c.conecte();

            Statement st = c.conexao.createStatement();
            // case e group by
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
            // percorre todos os resultados (linhas) retornados pela query (sql)
            while (rs.next()) {
                // cria um objeto da classe objetivo
                Cliente C = new Cliente(
                    rs.getInt       ("cliente_id"),
                    rs.getString    ("nome"),
                    rs.getString    ("cpf"),
                    rs.getDate      ("data_nascimento"),
                    rs.getString    ("email"),
                    rs.getString    ("senha"),
                    rs.getString    ("celular"),
                    rs.getString    ("fixo")
                );
                lista.add(C);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return lista;
    }

    public String grave(Cliente c) {
        String msg = "";

        try {
            Conexao C = new Conexao();
            C.conecte();

            String sql = "call p_salve_cliente(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = C.conexao.prepareStatement(sql);

            // substitui todas as ? por valores dos atributos do objeto de Objetivo
            st.setInt   (1, c.getClienteId());
            st.setString(2, c.getNome());
            st.setString(3, c.getCpf());
            st.setDate  (4, c.getDataNascimento());
            st.setString(5, c.getEmail());
            st.setString(6, c.getSenha());
            st.setString(7, c.getCelular());
            st.setString(8, c.getFixo());

            // executa a procedure
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
   
     //metodo para remover um objetivo do banco de dados
    public String remova(int clienteId) { 
        //define a mensagem inicial
        String msg = "";
        //tentativa
        try {
            //conecta com o banco de dados
            Conexao c = new Conexao();
            c.conecte();
            //define a query que será executada no banco de dados
            String sql = "call p_delete_cliente(?)";
            
            //objeto capaz de substituir a ? por um valor 
            PreparedStatement st = c.conexao.prepareStatement(sql);
            //define o valor da chave primaria do objetvo que será removido
            st.setInt(1, clienteId);
            //executa a query no vanco de dados
            st.executeUpdate();
            //recupera a mensagem retornada pela procedure
            ResultSet rs = st.getResultSet();
            rs.next();
            //atribui  amensagem a variavel declarada
            msg = rs.getString(2);
            st.close();
             
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return msg;       
    }

    public ArrayList<Cliente> pesquise(String termo) {
        // cria uma coleção de objetos vazia
        ArrayList<Cliente> clientes = new ArrayList<>();

        try {
            // cria uma conexão com o banco de dados
            Conexao c = new Conexao();
            c.conecte();

            // query que será executada com CASE
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

            // prepara a query para receber o termo de busca na ?
            PreparedStatement st = c.conexao.prepareStatement(sql);
            st.setString(1, "%" + termo + "%");

            st.executeQuery();

            ResultSet rs = st.getResultSet();

            // percorre todos os resultados
            while (rs.next()) {
                // cria objeto da classe objetivo
                Cliente C = new Cliente(
                    rs.getInt       ("cliente_id"),
                    rs.getString    ("nome"),
                    rs.getString    ("cpf"),
                    rs.getDate      ("data_nascimento"),
                    rs.getString    ("email"),
                    rs.getString    ("senha"),
                    rs.getString("celular"),
                    rs.getString("fixo")
                );
                // adiciona o objeto na coleção
                clientes.add(C);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        // retorna a coleção de objetos criada
        return clientes;
    }
}