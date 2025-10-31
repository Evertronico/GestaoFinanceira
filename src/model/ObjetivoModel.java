package model;

import controller.Objetivo;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ObjetivoModel {

    // lista todos os objetivos da tabela no banco de dados
    public ArrayList<Objetivo> liste() {
        // cria uma coleção de objetos vazia
        ArrayList<Objetivo> lista = new ArrayList<>();

        try {
            // cria uma conexão com o banco de dados
            Conexao c = new Conexao();
            c.conecte();

            Statement st = c.conexao.createStatement();
            String sql = "select * from objetivo order by nome";

            ResultSet rs = st.executeQuery(sql);
            // percorre todos os resultados (linhas) retornados pela query (sql)
            while (rs.next()) {
                // cria um objeto da classe objetivo
                Objetivo o = new Objetivo(
                    rs.getInt("objetivo_id"),
                    rs.getString("nome"),
                    rs.getBigDecimal("valor_integralizacao"),
                    rs.getDate("data_realizacao"),
                    rs.getBigDecimal("juros_atraso_diario"),
                    rs.getBigDecimal("multa_atraso"),
                    rs.getInt("numero_parcelas"),
                    rs.getInt("cotas")
                );
                lista.add(o);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return lista;
    }

    // método responsável por registrar um objetivo no banco de dados
    public String grave(Objetivo o) {
        String msg = "";

        try {
            Conexao c = new Conexao();
            c.conecte();

            String sql = "call p_salve_objetivo(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = c.conexao.prepareStatement(sql);

            // substitui todas as ? por valores dos atributos do objeto de Objetivo
            st.setInt(1, o.getObjetivoId());
            st.setString(2, o.getNome());
            st.setBigDecimal(3, o.getValorIntegralizacao());
            st.setDate(4, o.getDataRealizacao());
            st.setBigDecimal(5, o.getJurosAtrasoDiario());
            st.setBigDecimal(6, o.getMultaAtraso());
            st.setInt(7, o.getNumeroParcelas());
            st.setInt(8, o.getCotas());

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

    // método para remoção de objetivo no banco de dados
    public String remova(int objetivoId) {

        // declara uma string para armazenar a resposta da procedure
        String msg = "";

        // tenta
        try {
            // cria uma conexão com o banco de dados
            Conexao c = new Conexao();
            c.conecte();

            // determina a query que será exutada
            String sql = "call p_delete_objetivo(?)";

            // prepara a query substituindo a ? pelo valor da chave primária do registro
            PreparedStatement st = c.conexao.prepareStatement(sql);
            st.setInt(1, objetivoId);

            // executa a procedure
            st.executeUpdate();

            // recupera a resposta da chamada da procedure
            ResultSet rs = st.getResultSet();
            rs.next();

            // atribui à variável mensagem o texto de resposta da procedure
            msg = rs.getString(2);

            // fecha a conexão
            st.close();
        } catch (SQLException | ClassNotFoundException ex) { // captura uma exceção
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        // retorna a mensagem da interação com o banco de dados
        return msg;
    }

    // método responsável por pesquisar objetivos que correpondem a um termo de busca
    public ArrayList<Objetivo> pesquise(String termo) {
        // determina uma coleção de objetos vazia
        ArrayList<Objetivo> lista = new ArrayList<>();

        // tenta
        try {
            // cria uma conexão com o banco de dados
            Conexao c = new Conexao();
            c.conecte();

            // determina uma query para ser executada no banco de dados
            String sql = "select * from objetivo where nome like ? order by nome";
            PreparedStatement st = c.conexao.prepareStatement(sql);

            // substitui a ? pelo termo de busca
            st.setString(1, "%" + termo + "%");

            // executa a query
            st.executeQuery();

            // recupera os dados retornadas
            ResultSet rs = st.getResultSet();

            // enquanto há uma nova linha
            while (rs.next()) {
                // cria um novo objeto da classe Objetivo
                Objetivo o = new Objetivo(
                    rs.getInt("objetivo_id"),
                    rs.getString("nome"),
                    rs.getBigDecimal("valor_integralizacao"),
                    rs.getDate("data_realizacao"),
                    rs.getBigDecimal("juros_atraso_diario"),
                    rs.getBigDecimal("multa_atraso"),
                    rs.getInt("numero_parcelas"),
                    rs.getInt("cotas")
                );
                // adiciona o objeto na coleção
                lista.add(o);
            }
        } catch (SQLException | ClassNotFoundException ex) { // captura exceção
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        // retorna a coleção de objetos
        return lista;
    }
}
