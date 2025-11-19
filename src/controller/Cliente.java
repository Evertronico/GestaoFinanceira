package controller;

import java.sql.Date;

public class Cliente {

    private int clienteId;
    private String nome;
    private String cpf;
    private Date dataNascimento;
    private String email;
    private String senha;
    private String numeroTelefoneMovel;
    private String numeroTelefoneFixo;

    // Construtor
    public Cliente(int clienteId, String nome, String cpf, Date dataNascimento, String email, String senha, String numeroTelefoneMovel, String numeroTelefoneFixo) {
        this.clienteId = clienteId;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.senha = senha;
        this.numeroTelefoneMovel = numeroTelefoneMovel;
        this.numeroTelefoneFixo = numeroTelefoneFixo;
    }

    // Getters e Setters

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getNome() {
        return nome;
    }
    
    @Override
    public String toString() {
        return this.nome; // Isso diz ao ComboBox para mostrar o NOME do cliente
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

    public String getNumeroTelefoneMovel() {
        return numeroTelefoneMovel;
    }

    public void setNumeroTelefoneMovel(String numeroTelefoneMovel) {
        this.numeroTelefoneMovel = numeroTelefoneMovel;
    }

    public String getNumeroTelefoneFixo() {
        return numeroTelefoneFixo;
    }

    public void setNumeroTelefoneFixo(String numeroTelefoneFixo) {
        this.numeroTelefoneFixo = numeroTelefoneFixo;
    }
}