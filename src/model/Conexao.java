package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    // armazena uma conexão ativa com o banco de dados
    public Connection conexao = null;

    // método explícito para efetuar uma conexão com o banco de dados
    public void conecte() throws ClassNotFoundException, SQLException {
        // string de conexão com o banco de dados
        String url = "jdbc:mysql://localhost:3306/gestao_financeira?autoReconnect=true&useSSL=false";

        // define usuário e senha de comunicação com o banco de dados
        String usuario = "root";
        String senha = "";

        // registra o driver de conexão na aplicação
//        Class.forName("com.mysql.jdbc.Driver");;;

        // efetua uma conexão com o banco de dados e a atribui à variável global conexao
        this.conexao = DriverManager.getConnection(url, usuario, senha);
    }
}
