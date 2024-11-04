package dev.mmmartins.relatoriosapi.service;

import dev.mmmartins.relatoriosapi.model.Agenda;
import dev.mmmartins.relatoriosapi.repository.AgendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaService {
    private final AgendaRepository agendaRepository;

    public AgendaService(final AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    public List<Agenda> findAgendas() {
        return agendaRepository.findAll();
    }
}
