package controller;

import java.math.BigDecimal;
import java.sql.Date;


public class Cliente {
    
    private int clinteId;
    private String nome;
    private String cpf;
    private java.sql.Date dataNascimento;
    private String email;
    private String senha;

    public Cliente(int clinteId, String nome, String cpf, Date dataNascimento, String email, String senha) {
        this.clinteId = clinteId;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.senha = senha;
    }

    public Cliente(int aInt, String string, BigDecimal bigDecimal, Date date, BigDecimal bigDecimal0, BigDecimal bigDecimal1, int aInt0, int aInt1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getClinteId() {
        return clinteId;
    }

    public void setClinteId(int clinteId) {
        this.clinteId = clinteId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
    
}
