package dev.mmmartins.agendaservico.repository;

import dev.mmmartins.agendaservico.model.TipoAcesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoAcessoRepository extends JpaRepository<TipoAcesso, Long> {
}
