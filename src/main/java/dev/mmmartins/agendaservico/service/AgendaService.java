package dev.mmmartins.agendaservico.service;

import dev.mmmartins.agendaservico.exception.RegistroNaoEncontradoException;
import dev.mmmartins.agendaservico.model.Agenda;
import dev.mmmartins.agendaservico.repository.AgendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaService {
    private final AgendaRepository agendaRepository;

    public AgendaService(final AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    public List<Agenda> findAll() {
        return agendaRepository.findAll();
    }

    public Agenda findById(final Long id) {
        return agendaRepository.findById(id).orElseThrow(RegistroNaoEncontradoException::new);
    }

    public Agenda save(final Agenda agenda) {
        return agendaRepository.save(agenda);
    }

    public boolean delete(final Long id) {
        return agendaRepository.findById(id)
                .map(agenda -> {
                    agendaRepository.delete(agenda);
                    return true;
                })
                .orElse(false);
    }
}
