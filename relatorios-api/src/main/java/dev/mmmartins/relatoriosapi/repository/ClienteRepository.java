package dev.mmmartins.relatoriosapi.repository;

import dev.mmmartins.relatoriosapi.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
    boolean existsByCpfOrEmail(final String cpf, final String email);
}
