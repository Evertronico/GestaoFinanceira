package model;

import controller.FormaPagamento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class FormaPagamentoModel {

    // registra forma de pagamento no banco de dados (create/update)
    public String grave(FormaPagamento formaPagamento) {
        // declara a variável de retorno
        String msg = "";

        try {
            // abre a conexão com o banco de dados
            Conexao c = new Conexao();
            c.conecte();

            String sql = "call p_salve_forma_pagamento(?, ?)";

            PreparedStatement st = c.conexao.prepareStatement(sql);

            st.setInt(1, formaPagamento.getFormaPagamentoId());
            st.setString(2, formaPagamento.getDescricao());

            st.executeUpdate();

            ResultSet rs = st.getResultSet();
            rs.next();
            msg = rs.getString(2);

            st.close();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        // retorna a mensagem
        return msg;
    }

    // lista todas as formas de pagamento do banco de dados (read)
    public ArrayList liste() {
        // declara uma coleção de objetos do tipo FormaPagamento
        ArrayList<FormaPagamento> formasPagamento = new ArrayList<>();

        // comunicação com o banco de dados
        try {
            // conecta com o banco de dados
            Conexao c = new Conexao();
            c.conecte();

            // cria um objeto capaz de executar uma query no banco de dados
            Statement st = c.conexao.createStatement();

            // cria a sentença SQL
            String sql = "select * from forma_pagamento order by descricao";

            // recupera os dados
            ResultSet rs = st.executeQuery(sql);

            // percorre todas as linhas dentro do ResultSet
            while (rs.next()) {
                // cria um objeto da classe FormaPagamento
                FormaPagamento f = new FormaPagamento(
                    rs.getInt(1), 
                    rs.getString(2)
                );
                // adiciona o objeto à coleção de objetos
                formasPagamento.add(f);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        // retorna a lista
        return formasPagamento;
    }

    // método que remove uma forma de pagamento do banco de dados
    public String remova(int formaPagamentoId) {
        // define uma mensagem inicial
        String msg = "";
        // tenta
        try {
            // conecta com o banco de dados
            Conexao c = new Conexao();
            c.conecte();
            // define a query que será executada no banco de dados
            String sql = "call p_delete_forma_pagamento(?)";
            // objeto capaz de substituir a ? por um valor
            PreparedStatement st = c.conexao.prepareStatement(sql);
            // define o valor da chave primária da forma de pagamento que será removida
            st.setInt(1, formaPagamentoId);
            // executa a query no banco de dados
            st.executeUpdate();
            // recupera a mensagem retornada pela procedure
            ResultSet rs = st.getResultSet();
            rs.next();
            // atribui a mensagem à variável declarada
            msg = rs.getString(2);
            st.close();
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return msg;
    }

    public ArrayList<FormaPagamento> pesquise(String termo) {
        // cria uma coleção de objetos vazia
        ArrayList<FormaPagamento> formasPagamento = new ArrayList<>();

        try {
            // cria uma conexão com o banco de dados
            Conexao c = new Conexao();
            c.conecte();

            // query que será executada
            String sql = "select * from forma_pagamento where descricao like ? order by descricao";

            // prepara a query para receber o termo de busca na ?
            PreparedStatement st = c.conexao.prepareStatement(sql);
            st.setString(1, "%" + termo + "%");

            st.executeQuery();

            ResultSet rs = st.getResultSet();

            // percorre todos os resultados
            while (rs.next()) {
                // cria objeto da classe FormaPagamento
                FormaPagamento f = new FormaPagamento(
                    rs.getInt(1),
                    rs.getString(2)
                );
                // adiciona o objeto na coleção
                formasPagamento.add(f);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        // retorna a coleção de objetos criada
        return formasPagamento;
    }
}
