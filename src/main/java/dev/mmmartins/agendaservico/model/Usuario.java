package dev.mmmartins.agendaservico.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
public class Usuario {

    @Id
    @Column
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String usuario;

    @Column(nullable = false)
    private String senha;

    private transient String confirmaSenha;

    @Column(nullable = false)
    private LocalDate dataCadastro = LocalDate.now();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "acessos_usuarios",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "acesso_id"))
    private Set<TipoAcesso> acessos;
}
