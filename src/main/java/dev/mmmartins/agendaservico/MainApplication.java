package dev.mmmartins.agendaservico;

import dev.mmmartins.agendaservico.model.TipoAcesso;
import dev.mmmartins.agendaservico.repository.TipoAcessoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class MainApplication implements CommandLineRunner {
    private final TipoAcessoRepository tipoAcessoRepository;

    public MainApplication(TipoAcessoRepository tipoAcessoRepository) {
        this.tipoAcessoRepository = tipoAcessoRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        tipoAcessoRepository.saveAll(List.of(
           TipoAcesso.builder().nome("ADMIN").descricao("ADMIN").build(),
           TipoAcesso.builder().nome("USER").descricao("USUARIO NORMAL").build()
        ));
    }
}
