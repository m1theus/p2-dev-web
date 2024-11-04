package dev.mmmartins.relatoriosapi.service;

import dev.mmmartins.relatoriosapi.model.Cliente;
import dev.mmmartins.relatoriosapi.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(final ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> findClientes() {
        return clienteRepository.findAll();
    }
}
