package model;

import controller.Objetivo;
import controller.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import controller.MembroObjetivo;

public class MembroObjetivoModel {

    public ArrayList<Objetivo> listeObjetivo() {
        ObjetivoModel objModel = new ObjetivoModel();
        return objModel.liste();
    }

    public ArrayList<Cliente> listeCliente() {
        ClienteModel clienteModel = new ClienteModel();
        return clienteModel.liste();
    }

    public void grave(int clienteId, int objetivoId) {
        try {
            Conexao c = new Conexao();
            c.conecte();
            String sql = "insert into membro_grupo(cliente_id,objetivo_id)values(?,?)";
            PreparedStatement st = c.conexao.prepareStatement(sql);

            st.setInt(1, clienteId);
            st.setInt(2, objetivoId);

            st.execute();
            st.close();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public ArrayList<MembroObjetivo> liste() {
        // cria uma coleção de objetos vazia
        ArrayList<MembroObjetivo> lista = new ArrayList<>();

        try {
            // cria uma conexão com o banco de dados
            Conexao c = new Conexao();
            c.conecte();

            Statement st = c.conexao.createStatement();
            String sql = "select\n"
                    + "	mg.membro_grupo_id,\n"
                    + "    c.nome,\n"
                    + "    o.nome as obj,\n"
                    + "    mg.gestor\n"
                    + "from membro_grupo as mg\n"
                    + "inner join cliente c on c.cliente_id = mg.cliente_id\n"
                    + "inner join objetivo o on o.objetivo_id = mg.objetivo_id";

            ResultSet rs = st.executeQuery(sql);
            // percorre todos os resultados (linhas) retornados pela query (sql)
            while (rs.next()) {
                // cria um objeto da classe objetivo
                MembroObjetivo membro = new MembroObjetivo(
                        rs.getInt("membro_grupo_id"),
                        rs.getString("nome"),
                        rs.getString("obj"),
                        rs.getInt("gestor")
                );
                lista.add(membro);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return lista;
    }

    public void remova(int objetivoId) {
        try {
            // cria uma conexão com o banco de dados
            Conexao c = new Conexao();
            c.conecte();

            // determina a query que será exutada
            String sql = "delete from membro_grupo where membro_grupo_id = ?";

            // prepara a query substituindo a ? pelo valor da chave primária do registro
            PreparedStatement st = c.conexao.prepareStatement(sql);
            st.setInt(1, objetivoId);

            // executa a procedure
            st.executeUpdate();

            // fecha a conexão
            st.close();
        } catch (SQLException | ClassNotFoundException ex) { // captura uma exceção
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
