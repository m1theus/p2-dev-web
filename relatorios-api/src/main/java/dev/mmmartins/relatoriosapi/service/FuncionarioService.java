package dev.mmmartins.relatoriosapi.service;

import dev.mmmartins.relatoriosapi.model.Funcionario;
import dev.mmmartins.relatoriosapi.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(final FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<Funcionario> findFuncionarios() {
        return funcionarioRepository.findAll();
    }
}
