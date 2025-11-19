package controller;

public class MembroGrupo {
    
    private int membroGrupoId;
    private Cliente cliente;
    private Objetivo objetivo;
    private int gestor; // 0 ou 1

    public MembroGrupo(int membroGrupoId, Cliente cliente, Objetivo objetivo, int gestor) {
        this.membroGrupoId = membroGrupoId;
        this.cliente = cliente;
        this.objetivo = objetivo;
        this.gestor = gestor;
    }

    public int getMembroGrupoId() { return membroGrupoId; }
    public void setMembroGrupoId(int membroGrupoId) { this.membroGrupoId = membroGrupoId; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Objetivo getObjetivo() { return objetivo; }
    public void setObjetivo(Objetivo objetivo) { this.objetivo = objetivo; }

    public int getGestor() { return gestor; }
    public void setGestor(int gestor) { this.gestor = gestor; }
}