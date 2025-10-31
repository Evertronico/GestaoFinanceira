package controller;

public class FormaPagamento {
    
    private int formaPagamentoId;
    private String descricao;

    public FormaPagamento(int formaPagamentoId, String descricao) {
        this.formaPagamentoId = formaPagamentoId;
        this.descricao = descricao;
    }

    public int getFormaPagamentoId() {
        return formaPagamentoId;
    }

    public void setFormaPagamentoId(int formaPagamentoId) {
        this.formaPagamentoId = formaPagamentoId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    
}
