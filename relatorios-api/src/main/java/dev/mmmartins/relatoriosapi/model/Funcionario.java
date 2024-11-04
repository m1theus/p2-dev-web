package dev.mmmartins.relatoriosapi.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Funcionario {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "O campo Nome não pode ser vazio!")
    @Column(nullable = false)
    private String nome;
    @NotBlank(message = "O campo Telefone não pode ser vazio!")
    @Column(nullable = false)
    private String telefone;
    @NotBlank(message = "O campo Email não pode ser vazio!")
    @Column(unique = true, nullable = false)
    private String email;
    @Valid
    @NotNull(message = "O Endereço não pode ser vazio!")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", nullable = false)
    private Endereco endereco;
    @Positive(message = "O salário deve ser positivo!")
    @Column(nullable = false)
    private Double salario;
}
