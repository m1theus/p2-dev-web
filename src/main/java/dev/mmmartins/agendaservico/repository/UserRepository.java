package dev.mmmartins.agendaservico.repository;

import dev.mmmartins.agendaservico.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsuarioIgnoreCase(String username);
}
