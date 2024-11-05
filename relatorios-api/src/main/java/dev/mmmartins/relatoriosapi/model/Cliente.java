package dev.mmmartins.relatoriosapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;

@Entity
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Cliente implements Serializable, BaseEntity {
    @Id
    @Column(unique = true, nullable = false)
    @CPF(message = "O campo CPF não é válido!")
    @NotBlank(message = "O campo CPF não pode ser vazio!")
    private String cpf;

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

    @JsonIgnore
    @Override
    public String[] getRecord() {
        return new String[]{
                cpf,
                nome,
                telefone,
                email,
                String.format("#%s - %s - %s", endereco.getId(), endereco.getLogradouro(), endereco.getNumero()),
        };
    }

    @Override
    public String toString() {
        return String.format("%s - %s", cpf, nome);
    }
}
