package model;

import controller.Cliente;
import controller.MembroGrupo;
import controller.Objetivo;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MembroGrupoModel {

    private ArrayList<MembroGrupo> executarBusca(String sql, Integer filtroId) {
        ArrayList<MembroGrupo> lista = new ArrayList<>();
        try {
            Conexao c = new Conexao();
            c.conecte();
            PreparedStatement st = c.conexao.prepareStatement(sql);
            
            if (filtroId != null) {
                st.setInt(1, filtroId);
            }
            
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                // Reconstrói o objeto Cliente
                Cliente cli = new Cliente(
                    rs.getInt("cliente_id"), 
                    rs.getString("nome_cliente"), 
                    rs.getString("cpf"), 
                    rs.getDate("data_nascimento"), 
                    rs.getString("email"), 
                    "", "", "" 
                );
                
                // objeto Objetivo
                Objetivo obj = new Objetivo(
                    rs.getInt("objetivo_id"), 
                    rs.getString("nome_objetivo"), 
                    rs.getBigDecimal("valor_integralizacao"), 
                    null, null, null, 0, 0 
                );
                
                MembroGrupo mg = new MembroGrupo(
                    rs.getInt("membro_grupo_id"),
                    cli,
                    obj,
                    rs.getInt("gestor")
                );
                lista.add(mg);
            }
            st.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar dados: " + ex.getMessage());
        }
        return lista;
    }

    // 1. Listar todos
    public ArrayList<MembroGrupo> liste() {
        String sql = "SELECT m.membro_grupo_id, m.gestor, " +
                     "c.cliente_id, c.nome as nome_cliente, c.cpf, c.data_nascimento, c.email, " +
                     "o.objetivo_id, o.nome as nome_objetivo, o.valor_integralizacao " +
                     "FROM membro_grupo m " +
                     "INNER JOIN cliente c ON m.cliente_id = c.cliente_id " +
                     "INNER JOIN objetivo o ON m.objetivo_id = o.objetivo_id " +
                     "ORDER BY o.nome, c.nome";
        return executarBusca(sql, null);
    }

    // 2. Filtrar por Objetivo (Para o combobox de filtro)
    public ArrayList<MembroGrupo> listePorObjetivo(int objetivoId) {
        String sql = "SELECT m.membro_grupo_id, m.gestor, " +
                     "c.cliente_id, c.nome as nome_cliente, c.cpf, c.data_nascimento, c.email, " +
                     "o.objetivo_id, o.nome as nome_objetivo, o.valor_integralizacao " +
                     "FROM membro_grupo m " +
                     "INNER JOIN cliente c ON m.cliente_id = c.cliente_id " +
                     "INNER JOIN objetivo o ON m.objetivo_id = o.objetivo_id " +
                     "WHERE m.objetivo_id = ? " +
                     "ORDER BY c.nome";
        return executarBusca(sql, objetivoId);
    }

    // 3. Gravar (Procedure)
    public String grave(MembroGrupo m) {
        try {
            Conexao c = new Conexao();
            c.conecte();
            String sql = "call p_salve_membro_grupo(?, ?, ?, ?)";
            PreparedStatement st = c.conexao.prepareStatement(sql);
            
            st.setInt(1, m.getMembroGrupoId());
            st.setInt(2, m.getCliente().getClienteId());
            st.setInt(3, m.getObjetivo().getObjetivoId());
            st.setInt(4, m.getGestor());
            
            st.executeUpdate();
            ResultSet rs = st.getResultSet();
            rs.next();
            String msg = rs.getString(2);
            st.close();
            return msg;
        } catch (Exception ex) {
            return "Erro ao gravar: " + ex.getMessage();
        }
    }

    // 4. Remover (Procedure)
    public String remova(int id) {
        try {
            Conexao c = new Conexao();
            c.conecte();
            String sql = "call p_delete_membro_grupo(?)";
            PreparedStatement st = c.conexao.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            ResultSet rs = st.getResultSet();
            rs.next();
            String msg = rs.getString(2);
            st.close();
            return msg;
        } catch (Exception ex) {
            return "Erro ao remover: " + ex.getMessage();
        }
    }
}