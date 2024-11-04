package dev.mmmartins.relatoriosapi.controller;

import dev.mmmartins.relatoriosapi.model.Funcionario;
import dev.mmmartins.relatoriosapi.service.FuncionarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    public FuncionarioController(final FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> funcionarios() {
        return ResponseEntity.ok(funcionarioService.findFuncionarios());
    }
}
