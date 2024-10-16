package dev.mmmartins.agendaservico.service;

import dev.mmmartins.agendaservico.exception.SenhaInvalidaException;
import dev.mmmartins.agendaservico.exception.UsuarioJaExistenteException;
import dev.mmmartins.agendaservico.model.Usuario;
import dev.mmmartins.agendaservico.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(final UserRepository userRepository, final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(final Usuario usuario) throws UsuarioJaExistenteException, SenhaInvalidaException {
        Optional<Usuario> user = userRepository.findByUsuarioIgnoreCase(usuario.getUsuario());

        if (user.isPresent()) {
            throw new UsuarioJaExistenteException();
        }

        if (!Objects.equals(usuario.getSenha(), usuario.getConfirmaSenha())) {
            throw new SenhaInvalidaException("As senhas devem ser iguais!");
        }

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        userRepository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        Usuario user = userRepository.findByUsuarioIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new User(
                user.getUsuario(),
                user.getSenha(),
                user.getAcessos().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getNome()))
                        .collect(Collectors.toList())
        );
    }
}
