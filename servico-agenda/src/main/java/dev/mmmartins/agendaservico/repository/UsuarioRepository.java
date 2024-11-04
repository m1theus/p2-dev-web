package dev.mmmartins.agendaservico.repository;

import dev.mmmartins.agendaservico.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("SELECT u FROM Usuario u LEFT JOIN u.acessos a ORDER BY CASE WHEN a IS NULL THEN 0 ELSE 1 END, a.nome")
    List<Usuario> findAllOrderByTipoAcessoNullFirst();

    Optional<Usuario> findByUsuarioIgnoreCase(String username);
}
