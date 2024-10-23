package dev.mmmartins.agendaservico;

import dev.mmmartins.agendaservico.model.TipoAcesso;
import dev.mmmartins.agendaservico.model.Usuario;
import dev.mmmartins.agendaservico.repository.TipoAcessoRepository;
import dev.mmmartins.agendaservico.service.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.List;

@EnableCaching
@EnableFeignClients
@SpringBootApplication
public class MainApplication implements CommandLineRunner {
    private final TipoAcessoRepository tipoAcessoRepository;
    private final UsuarioService usuarioService;

    public MainApplication(final TipoAcessoRepository tipoAcessoRepository, final UsuarioService usuarioService) {
        this.tipoAcessoRepository = tipoAcessoRepository;
        this.usuarioService = usuarioService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Override
    public void run(String... args) {
        if (tipoAcessoRepository.count() == 0) {
            var acessos = tipoAcessoRepository.saveAll(List.of(
                    TipoAcesso.builder().nome("ROLE_ADMIN").descricao("USUARIO ADMIN").build(),
                    TipoAcesso.builder().nome("ROLE_USER").descricao("USUARIO NORMAL").build()
            ));

            acessos
                    .stream()
                    .filter(t -> t.getNome().equals("ROLE_ADMIN"))
                    .findFirst()
                    .ifPresent(t -> usuarioService.createUser(new Usuario("1", "1", t)));
        }
    }
}
