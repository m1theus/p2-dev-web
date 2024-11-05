package dev.mmmartins.relatoriosapi.service;

import dev.mmmartins.relatoriosapi.model.Funcionario;
import dev.mmmartins.relatoriosapi.repository.FuncionarioRepository;
import dev.mmmartins.relatoriosapi.service.generator.GeneratorFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {
    private static final List<String> HEADERS = List.of("ID", "NOME", "TELEFONE", "EMAIL", "ENDERECO", "SALARIO");
    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(final FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<Funcionario> findFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public byte[] generate(final String type) {
        return GeneratorFactory
                .getGenerator(type, Funcionario.class)
                .generate(HEADERS, findFuncionarios());
    }
}
