package dev.mmmartins.relatoriosapi.service;

import dev.mmmartins.relatoriosapi.model.Cliente;
import dev.mmmartins.relatoriosapi.repository.ClienteRepository;
import dev.mmmartins.relatoriosapi.service.generator.GeneratorFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private static final List<String> HEADERS = List.of("CPF", "NOME", "TELEFONE", "EMAIL", "ENDERECO");

    private final ClienteRepository clienteRepository;

    public ClienteService(final ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> findClientes() {
        return clienteRepository.findAll();
    }

    public byte[] generate(final String type) {
        return GeneratorFactory
                .getGenerator(type, Cliente.class)
                .generate(HEADERS, findClientes());
    }
}
