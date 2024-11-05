package dev.mmmartins.relatoriosapi.service;

import dev.mmmartins.relatoriosapi.model.Agenda;
import dev.mmmartins.relatoriosapi.repository.AgendaRepository;
import dev.mmmartins.relatoriosapi.service.generator.GeneratorFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaService {
    private final AgendaRepository agendaRepository;

    private static final List<String> HEADERS = List.of("ID", "DESCRICAO", "DATA", "SERVICOS", "CLIENTE", "FUNCIONARIO");

    public AgendaService(final AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    public List<Agenda> findAgendas() {
        return agendaRepository.findAll();
    }

    public byte[] generate(final String type) {
        return GeneratorFactory
                .getGenerator(type, Agenda.class)
                .generate(HEADERS, findAgendas());
    }
}
