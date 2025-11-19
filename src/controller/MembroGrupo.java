package controller;



public class MembroGrupo{

    private int objetivoId;
    private String nomeObjetivo;
    private int clienteId;
    private String nomeCliente;

    public MembroGrupo(int objetivoId, String nomeObjetivo, int clienteId, String nomeCliente) {
        this.objetivoId = objetivoId;
        this.nomeObjetivo = nomeObjetivo;
        this.clienteId = clienteId;
        this.nomeCliente = nomeCliente;
    }

    public int getObjetivoId() { return objetivoId; }
    public String getNomeObjetivo() { return nomeObjetivo; }
    public int getClienteId() { return clienteId; }
    public String getNomeCliente() { return nomeCliente; }
}