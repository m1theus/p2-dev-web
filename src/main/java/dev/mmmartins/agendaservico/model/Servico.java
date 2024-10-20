package dev.mmmartins.agendaservico.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Servico {
    @Id
    @Column
    @GeneratedValue
    private Long id;

    @Column
    @Size(min = 5, max = 100, message = "O nome deve ter entre 5 e 100 caracteres!")
    @NotBlank(message = "O nome não pode ser vazio!")
    private String nome;

    @Column
    @NotNull(message = "O valor não pode ser vazio!")
    @Positive(message = "O valor deve ser um valor positivo!")
    private Double valor;
}
