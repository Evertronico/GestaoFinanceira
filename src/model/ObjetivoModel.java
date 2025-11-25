package model;

import controller.Objetivo;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ObjetivoModel {

    public String grave(Objetivo o) {
        String msg = "";
        try {
            Conexao c = new Conexao();
            c.conecte();

            String sql = "call p_salve_objetivo(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = c.conexao.prepareStatement(sql);

            st.setInt(1, o.getObjetivoId());
            st.setString(2, o.getNome());
            st.setBigDecimal(3, o.getValorIntegralizacao());
            st.setDate(4, o.getDataRealizacao());
            st.setBigDecimal(5, o.getJurosAtrasoDiario());
            st.setBigDecimal(6, o.getMultaAtraso());
            st.setInt(7, o.getNumeroParcelas());
            st.setInt(8, o.getCotas());

            st.executeUpdate();
            ResultSet rs = st.getResultSet();
            rs.next();
            msg = rs.getString(2);

            st.close();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return msg;
    }

    public ArrayList<Objetivo> liste() {
        ArrayList<Objetivo> lista = new ArrayList<>();
        try {
            Conexao c = new Conexao();
            c.conecte();
            Statement st = c.conexao.createStatement();
            String sql = "select * from objetivo order by nome";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
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

    public String remova(int objetivoId) {
        String msg = "";
        try {
            Conexao c = new Conexao();
            c.conecte();
            String sql = "call p_delete_objetivo(?)";
            PreparedStatement st = c.conexao.prepareStatement(sql);
            st.setInt(1, objetivoId);
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

    public ArrayList<Objetivo> pesquise(String termo) {
        ArrayList<Objetivo> lista = new ArrayList<>();
        try {
            Conexao c = new Conexao();
            c.conecte();
            String sql = "select * from objetivo where nome like ? order by nome";
            PreparedStatement st = c.conexao.prepareStatement(sql);
            st.setString(1, "%" + termo + "%");
            st.executeQuery();
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
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
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return lista;
    }
}
