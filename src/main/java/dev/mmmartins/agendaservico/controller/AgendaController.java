package dev.mmmartins.agendaservico.controller;

import dev.mmmartins.agendaservico.service.AgendaService;
import org.springframework.stereotype.Controller;

@Controller
public class AgendaController {
    private final AgendaService agendaService;

    public AgendaController(final AgendaService agendaService) {
        this.agendaService = agendaService;
    }
}
