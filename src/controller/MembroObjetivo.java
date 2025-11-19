package controller;

public class MembroObjetivo {
    private Integer membroGrupoId;
    private String clienteId;
    private String objetivoId;
    private Integer gestor;

    public MembroObjetivo(Integer membroGrupoId, String clienteId, String objetivoId, Integer gestor) {
        this.membroGrupoId = membroGrupoId;
        this.clienteId = clienteId;
        this.objetivoId = objetivoId;
        this.gestor = gestor;
    }

    public Integer getMembroGrupoId() {
        return membroGrupoId;
    }

    public void setMembroGrupoId(Integer membroGrupoId) {
        this.membroGrupoId = membroGrupoId;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getObjetivoId() {
        return objetivoId;
    }

    public void setObjetivoId(String objetivoId) {
        this.objetivoId = objetivoId;
    }

    public Integer getGestor() {
        return gestor;
    }

    public void setGestor(Integer gestor) {
        this.gestor = gestor;
    }

    
    
}
