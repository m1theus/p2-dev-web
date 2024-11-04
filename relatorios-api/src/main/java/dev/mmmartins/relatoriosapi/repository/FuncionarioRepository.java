package dev.mmmartins.relatoriosapi.repository;

import dev.mmmartins.relatoriosapi.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
