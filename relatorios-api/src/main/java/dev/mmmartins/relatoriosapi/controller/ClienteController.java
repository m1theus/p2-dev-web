package dev.mmmartins.relatoriosapi.controller;

import dev.mmmartins.relatoriosapi.model.Cliente;
import dev.mmmartins.relatoriosapi.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(final ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> clientes() {
        return ResponseEntity.ok(clienteService.findClientes());
    }
}
