package controller;

import java.math.BigDecimal;
import java.sql.Date;


public class Cliente {
    
    private int clienteId;
    private String nome;
    private String cpf;
    private java.sql.Date dataNascimento;
    private String email;
    private String senha;
    private String celular;
    private String fixo;

    public Cliente(int clienteId, String nome, String cpf, Date dataNascimento, String email, String senha, String celular, String fixo) {
        this.clienteId = clienteId;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.senha = senha;
        this.celular = celular;
        this.fixo = fixo;
    }

    public Cliente(int aInt, String string, BigDecimal bigDecimal, Date date, BigDecimal bigDecimal0, BigDecimal bigDecimal1, int aInt0, int aInt1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clinteId) {
        this.clienteId = clinteId;
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

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getFixo() {
        return fixo;
    }

    public void setFixo(String fixo) {
        this.fixo = fixo;
    }
    
    //sobreescrever o método.
    @Override
    public String toString() {
        return nome;
    }
}