package dev.mmmartins.relatoriosapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
public class Agenda implements Serializable, BaseEntity {
    @Id
    @Column
    @GeneratedValue
    private Long id;

    @NotBlank(message = "A descrição não pode ser vazia!")
    @Size(min = 5, max = 200, message = "A descrição deve ter entre 5 e 200 caracteres!")
    @Column
    private String descricao;

    @NotNull(message = "A data não pode ser vazia!")
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "agendas_servicos",
            joinColumns = @JoinColumn(name = "agenda_id"),
            inverseJoinColumns = @JoinColumn(name = "servicos_id")
    )
    private List<Servico> servicos = new ArrayList<>();

    @NotNull(message = "O cliente não pode ser vazio!")
    @ManyToOne
    @JoinColumn
    private Cliente cliente;

    @NotNull(message = "O funcionário não pode ser vazio!")
    @ManyToOne
    @JoinColumn
    private Funcionario funcionario;

    @Override
    public String toString() {
        return "Agenda{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", data=" + data +
                ", servicos=" + servicos +
                ", cliente=" + cliente +
                ", funcionario=" + funcionario +
                '}';
    }

    @Override
    @JsonIgnore
    public String[] getRecord() {
        return new String[]{
                getId().toString(),
                getDescricao(),
                getData().toString(),
                getServicosRecord(),
                getCliente().getCpf(),
                String.format("%s - %s", getFuncionario().getId(), getFuncionario().getNome())};
    }

    @JsonIgnore
    public String getServicosRecord() {
        return this.servicos
                .stream()
                .map(item -> String.format("%s - %s - %s", item.getId(), item.getNome(), item.getValor()))
                .collect(Collectors.joining());
    }
}
