package dev.mmmartins.agendaservico.service;

import dev.mmmartins.agendaservico.exception.RegistroJaExistenteException;
import dev.mmmartins.agendaservico.exception.RegistroNaoEncontradoException;
import dev.mmmartins.agendaservico.model.Cliente;
import dev.mmmartins.agendaservico.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(final ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente findByCpf(final String cpf) {
        return clienteRepository.findById(cpf)
                .orElseThrow(() -> new RegistroNaoEncontradoException(String.format("Registro %s não encontrado.", cpf)));
    }

    public void save(final Cliente cliente) throws RegistroJaExistenteException {
        if (clienteRepository.existsByCpfOrEmail(cliente.getCpf(), cliente.getEmail())) {
            throw new RegistroJaExistenteException("Cliente já existente!");
        }
        this.clienteRepository.save(cliente);
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public boolean deleteByCpf(final String cpf) {
        return clienteRepository.findById(cpf)
                .map(t -> {
                    clienteRepository.deleteById(cpf);
                    return true;
                })
                .orElse(false);
    }


    public void update(final Cliente cliente) {
        this.clienteRepository.save(cliente);
    }
}
