package dev.mmmartins.relatoriosapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Endereco {
    @Id
    @Column
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "O logradouro não pode ser vazio!")
    @Size(min = 5, max = 200, message = "O logradouro deve ser entre 5 e 200 caracteres!")
    private String logradouro;

    @Column(nullable = false)
    @NotBlank(message = "O estado não pode ser vazio!")
    private String estado;

    @Column(nullable = false)
    @NotBlank(message = "O CEP não pode ser vazio!")
    @Size(min = 8, max = 8, message = "O cep deve ter 8 caracteres!")
    private String cep;

    @Column(nullable = false)
    @NotBlank(message = "A cidade não pode ser vazio!")
    private String cidade;

    @Column(nullable = false)
    @NotBlank(message = "O número não pode ser vazio!")
    @Positive(message = "O número deve ser positivo!")
    private String numero;

    @Column
    private String complemento;

    @Override
    public String toString() {
        return String.format("#%s - %s, %s - %s %s, %s - %s", id, logradouro, numero, complemento, cidade, estado, cep);
    }
}
