package dev.mmmartins.relatoriosapi.repository;

import dev.mmmartins.relatoriosapi.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
