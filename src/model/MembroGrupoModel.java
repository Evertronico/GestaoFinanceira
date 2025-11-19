package model;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Model responsável por gerenciar os vínculos entre Objetivo e Cliente (Membro do Grupo)
 */
public class MembroGrupoModel {

    // === Campos do objeto (representam uma linha da consulta) ===
    private int objetivoId;
    private String nomeObjetivo;
    private int clienteId;
    private String nomeCliente;

    // === Construtor vazio (necessário para criar instâncias) ===
    public MembroGrupoModel() {
    }

   public ArrayList<MembroGrupoModel> liste(int filtroObjetivoId, int filtroClienteId) {
    ArrayList<MembroGrupoModel> lista = new ArrayList<>();

    try {
        Conexao c = new Conexao();
        c.conecte();

        String sql = """
            SELECT 
                o.objetivo_id,
                o.nome AS nome_objetivo,
                c.cliente_id,
                c.nome AS nome_cliente
            FROM membro_grupo mg
            INNER JOIN objetivo o ON mg.objetivo_id = o.objetivo_id
            INNER JOIN cliente c ON mg.cliente_id = c.cliente_id
            WHERE (o.objetivo_id = ? OR ? = -1)
              AND (c.cliente_id = ? OR ? = -1)
            ORDER BY o.nome, c.nome
            """;

        PreparedStatement ps = c.conexao.prepareStatement(sql);
        ps.setInt(1, filtroObjetivoId);
        ps.setInt(2, filtroObjetivoId);
        ps.setInt(3, filtroClienteId);
        ps.setInt(4, filtroClienteId);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            MembroGrupoModel mg = new MembroGrupoModel();
            mg.setObjetivoId(rs.getInt("objetivo_id"));
            mg.setNomeObjetivo(rs.getString("nome_objetivo"));
            mg.setClienteId(rs.getInt("cliente_id"));
            mg.setNomeCliente(rs.getString("nome_cliente"));
            lista.add(mg);
        }

        rs.close();
        ps.close();

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, 
            "Erro ao carregar vínculos:\n" + ex.getMessage(), 
            "Erro", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    }

    return lista;
}

    // === Método para VINCULAR (inserir) ===
    public String vincule(int objetivoId, int clienteId) {
        String msg = "Erro desconhecido.";
        try {
            Conexao c = new Conexao();
            c.conecte();
            CallableStatement cs = c.conexao.prepareCall("{call p_salve_membro_grupo(?, ?)}");
            cs.setInt(1, objetivoId);
            cs.setInt(2, clienteId);
            cs.execute();

            ResultSet rs = cs.getResultSet();
            if (rs != null && rs.next()) {
                msg = rs.getString("msg");
            } else {
                msg = "Vínculo realizado com sucesso!";
            }
            cs.close();
        } catch (Exception ex) {
            msg = "Erro ao vincular: " + ex.getMessage();
        }
        return msg;
    }

    // === Método para DESVINCULAR (remover) ===
    public String desvincule(int objetivoId, int clienteId) {
        String msg = "Erro desconhecido.";
        try {
            Conexao c = new Conexao();
            c.conecte();
            CallableStatement cs = c.conexao.prepareCall("{call p_delete_membro_grupo(?, ?)}");
            cs.setInt(1, objetivoId);
            cs.setInt(2, clienteId);
            cs.execute();

            ResultSet rs = cs.getResultSet();
            if (rs != null && rs.next()) {
                msg = rs.getString("msg");
            } else {
                msg = "Vínculo removido com sucesso!";
            }
            cs.close();
        } catch (Exception ex) {
            msg = "Erro ao remover vínculo: " + ex.getMessage();
        }
        return msg;
    }

    // === Getters e Setters (OBRIGATÓRIOS!) ===
    public int getObjetivoId() {
        return objetivoId;
    }

    public void setObjetivoId(int objetivoId) {
        this.objetivoId = objetivoId;
    }

    public String getNomeObjetivo() {
        return nomeObjetivo;
    }

    public void setNomeObjetivo(String nomeObjetivo) {
        this.nomeObjetivo = nomeObjetivo;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
}