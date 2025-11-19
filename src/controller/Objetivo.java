package controller;

import java.math.BigDecimal;
import java.sql.Date;

public class Objetivo {

    private int objetivoId;
    private String nome;
    private BigDecimal valorIntegralizacao;
    private java.sql.Date dataRealizacao;
    private BigDecimal jurosAtrasoDiario;
    private BigDecimal multaAtraso;
    private int numeroParcelas;
    private int cotas;

    public Objetivo(int objetivoId, String nome, BigDecimal valorIntegralizacao, Date dataRealizacao, BigDecimal jurosAtrasoDiario, BigDecimal multaAtraso, int numeroParcelas, int cotas) {
        this.objetivoId = objetivoId;
        this.nome = nome;
        this.valorIntegralizacao = valorIntegralizacao;
        this.dataRealizacao = dataRealizacao;
        this.jurosAtrasoDiario = jurosAtrasoDiario;
        this.multaAtraso = multaAtraso;
        this.numeroParcelas = numeroParcelas;
        this.cotas = cotas;
    }

    public int getObjetivoId() {
        return objetivoId;
    }

    public void setObjetivoId(int objetivoId) {
        this.objetivoId = objetivoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValorIntegralizacao() {
        return valorIntegralizacao;
    }

    public void setValorIntegralizacao(BigDecimal valorIntegralizacao) {
        this.valorIntegralizacao = valorIntegralizacao;
    }

    public Date getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(Date dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public BigDecimal getJurosAtrasoDiario() {
        return jurosAtrasoDiario;
    }

    public void setJurosAtrasoDiario(BigDecimal jurosAtrasoDiario) {
        this.jurosAtrasoDiario = jurosAtrasoDiario;
    }

    public BigDecimal getMultaAtraso() {
        return multaAtraso;
    }

    public void setMultaAtraso(BigDecimal multaAtraso) {
        this.multaAtraso = multaAtraso;
    }

    public int getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(int numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

    public int getCotas() {
        return cotas;
    }

    public void setCotas(int cotas) {
        this.cotas = cotas;
    }
    
    
    //sobreescrever o método.
    @Override
    public String toString() {
        return nome; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
}