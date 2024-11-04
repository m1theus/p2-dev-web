package dev.mmmartins.relatoriosapi.repository;

import dev.mmmartins.relatoriosapi.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
}
