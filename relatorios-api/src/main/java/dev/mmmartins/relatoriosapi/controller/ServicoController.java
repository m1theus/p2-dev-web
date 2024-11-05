package dev.mmmartins.relatoriosapi.controller;

import dev.mmmartins.relatoriosapi.model.Servico;
import dev.mmmartins.relatoriosapi.service.ServicoService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/servicos")
public class ServicoController {
    private final ServicoService servicoService;

    public ServicoController(final ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @GetMapping
    public ResponseEntity<List<Servico>> servicos() {
        return ResponseEntity.ok(servicoService.findServicos());
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> download(@RequestParam("type") final String type) {
        byte[] fileContent = servicoService.generate(type);
        final var fileName = String.format("relatorio-servicos.%s", type);
        final var attachmentHeader = String.format("attachment; filename=%s", fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, attachmentHeader)
                .body(fileContent);
    }
}
