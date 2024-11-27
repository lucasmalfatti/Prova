package dev.malffatti.Prova.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Treino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @ManyToMany
    @JsonIgnoreProperties("treino")
    private List<Exercicio> exercicios;

    private String status;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public List<Exercicio> getExercicios() {
        return exercicios;
    }

    public void setExercicios(List<Exercicio> exercicios) {
        this.exercicios = exercicios;
    }

    public String getStatus() {
        if (status != "Ativo" && status != "Vencido" && status != "Completo"){
            Error error = new Error("Essa palavra não é válida para status");
        }
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
