package dev.mmmartins.agendaservico.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
public class Endereco {
    @Id
    @Column
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private String UF;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String numero;

    @Column()
    private String complemento;
}
