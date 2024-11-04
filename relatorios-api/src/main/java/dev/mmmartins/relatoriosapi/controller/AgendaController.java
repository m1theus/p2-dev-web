package dev.mmmartins.relatoriosapi.controller;

import dev.mmmartins.relatoriosapi.model.Agenda;
import dev.mmmartins.relatoriosapi.service.AgendaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/agendas")
public class AgendaController {
    private final AgendaService agendaService;

    public AgendaController(final AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @GetMapping
    public ResponseEntity<List<Agenda>> agendas() {
        return ResponseEntity.ok(agendaService.findAgendas());
    }
}
