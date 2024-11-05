package dev.mmmartins.relatoriosapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class Servico implements Serializable, BaseEntity {
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

    @JsonIgnore
    @Override
    public String[] getRecord() {
        return new String[]{
                id.toString(),
                nome,
                valor.toString()
        };
    }

    @Override
    public String toString() {
        return String.format("#%s - %s - %s", id, nome, valor);
    }
}
