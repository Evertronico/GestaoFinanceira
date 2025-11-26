package model;

import controller.Cliente;
import controller.MembroGrupo;
import controller.Objetivo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.Statement;

public class MembroGrupoModel {

    // método de listagem de todos os membros (cliente) vinculados a grupos (objetivo)
    public ArrayList<MembroGrupo> liste() {
        ArrayList<MembroGrupo> lista = new ArrayList<>();

        try {
            Conexao c = new Conexao();
            c.conecte();
            Statement st = c.conexao.createStatement();

            String sql = "select * from v_membro_grupo";

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setClienteId(rs.getInt("cliente_id"));
                cli.setNome(rs.getString("nome_cliente"));

                Objetivo obj = new Objetivo();
                obj.setObjetivoId(rs.getInt("objetivo_id"));
                obj.setNome(rs.getString("nome_objetivo"));

                MembroGrupo mg = new MembroGrupo(
                    rs.getInt("membro_grupo_id"),
                    cli,
                    obj,
                    rs.getInt("gestor")
                );
                lista.add(mg);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return lista;
    }

    // método responsável por registrar membros (cliente) a um grupo (objetivo)
    public String Grave(MembroGrupo membroGrupo) {
        String msg = "";

        try {
            Conexao c = new Conexao();
            c.conecte();

            String sql = "call p_salve_membro_grupo(?, ?, ?, ?)";

            PreparedStatement st = c.conexao.prepareStatement(sql);

            st.setInt(1, membroGrupo.getMembroGrupoId());
            st.setInt(2, membroGrupo.getCliente().getClienteId());
            st.setInt(3, membroGrupo.getObjetivo().getObjetivoId());
            st.setInt(4, membroGrupo.getGestor());

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
}
