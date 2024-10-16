package dev.mmmartins.agendaservico.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
public class Cliente {
    @Id
    @Column(unique = true, nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String telefone;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToOne
    @JoinColumn(nullable = false)
    private Endereco endereco;
}
