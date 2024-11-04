package dev.mmmartins.agendaservico.service;

import dev.mmmartins.agendaservico.exception.RegistroJaExistenteException;
import dev.mmmartins.agendaservico.exception.RegistroNaoEncontradoException;
import dev.mmmartins.agendaservico.exception.SenhaInvalidaException;
import dev.mmmartins.agendaservico.model.Usuario;
import dev.mmmartins.agendaservico.repository.UsuarioRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(final UsuarioRepository usuarioRepository, final PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAllOrderByTipoAcessoNullFirst();
    }

    public void createUser(final Usuario usuario) throws RegistroJaExistenteException, SenhaInvalidaException {
        Optional<Usuario> user = usuarioRepository.findByUsuarioIgnoreCase(usuario.getUsuario());

        if (user.isPresent()) {
            throw new RegistroJaExistenteException("Usuário já existente!");
        }

        if (!Objects.equals(usuario.getSenha(), usuario.getConfirmaSenha())) {
            throw new SenhaInvalidaException("As senhas devem ser iguais!");
        }

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuarioRepository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByUsuarioIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new User(
                user.getUsuario(),
                user.getSenha(),
                user.getAcessos().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getNome()))
                        .collect(Collectors.toList())
        );
    }

    public Usuario update(final Usuario usuario) throws RegistroNaoEncontradoException {
        final var editUser = usuarioRepository.findById(usuario.getId())
                .orElseThrow(RegistroNaoEncontradoException::new);

        editUser.setAcessos(usuario.getAcessos());

        return usuarioRepository.save(editUser);
    }
}
