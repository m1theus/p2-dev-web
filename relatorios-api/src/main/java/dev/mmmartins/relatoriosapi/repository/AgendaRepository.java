package dev.mmmartins.relatoriosapi.repository;

import dev.mmmartins.relatoriosapi.model.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {
}
