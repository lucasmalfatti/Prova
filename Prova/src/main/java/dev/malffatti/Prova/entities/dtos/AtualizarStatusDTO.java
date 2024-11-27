package dev.malffatti.Prova.entities.dtos;

public class AtualizarStatusDTO {

    private Long codigo;
    private String status;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
