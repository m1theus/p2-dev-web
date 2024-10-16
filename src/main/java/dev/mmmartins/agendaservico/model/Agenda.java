package dev.mmmartins.agendaservico.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Agenda {
    @Id
    @Column
    @GeneratedValue
    private Long id;

    @Column
    private String descricao;

    @Column
    private LocalDate data;

    @OneToMany
    @JoinColumn
    private List<Servico> servico;

    @ManyToOne
    private Cliente cliente;
}
