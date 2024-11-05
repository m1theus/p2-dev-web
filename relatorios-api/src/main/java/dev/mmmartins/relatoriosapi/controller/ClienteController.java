package dev.mmmartins.relatoriosapi.controller;

import dev.mmmartins.relatoriosapi.model.Cliente;
import dev.mmmartins.relatoriosapi.service.ClienteService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/download")
    public ResponseEntity<byte[]> download(@RequestParam("type") final String type) {
        byte[] fileContent = clienteService.generate(type);
        final var fileName = String.format("relatorio-clientes.%s", type);
        final var attachmentHeader = String.format("attachment; filename=%s", fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, attachmentHeader)
                .body(fileContent);
    }
}
