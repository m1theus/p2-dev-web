package dev.mmmartins.agendaservico.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
public class Servico {
    @Id
    @Column
    @GeneratedValue
    private Long id;

    @Column
    private String nome;

    @Column
    private Double valor;
}
