package dev.mmmartins.agendaservico.model;

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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
public class Agenda {
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

    public String getServicosNomes() {
        return servicos.stream()
                .map(Servico::getNome)
                .collect(Collectors.joining(", "));
    }

    public String getServicosIds() {
        return servicos.stream()
                .map(s -> String.format("%s-%s", s.getId(), s.getNome()))
                .collect(Collectors.joining(", "));
    }
}
