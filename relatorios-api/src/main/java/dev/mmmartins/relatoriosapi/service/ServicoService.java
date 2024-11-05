package dev.mmmartins.relatoriosapi.service;

import dev.mmmartins.relatoriosapi.model.Servico;
import dev.mmmartins.relatoriosapi.repository.ServicoRepository;
import dev.mmmartins.relatoriosapi.service.generator.GeneratorFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {
    private static final List<String> HEADERS = List.of("ID", "NOME", "VALOR");
    private final ServicoRepository servicoRepository;

    public ServicoService(final ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public List<Servico> findServicos() {
        return servicoRepository.findAll();
    }

    public byte[] generate(final String type) {
        return GeneratorFactory
                .getGenerator(type, Servico.class)
                .generate(HEADERS, findServicos());
    }
}
