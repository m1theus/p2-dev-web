package dev.mmmartins.relatoriosapi.service;

import dev.mmmartins.relatoriosapi.model.Agenda;
import dev.mmmartins.relatoriosapi.model.Servico;
import dev.mmmartins.relatoriosapi.repository.AgendaRepository;
import dev.mmmartins.relatoriosapi.repository.ServicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {
    private final ServicoRepository servicoRepository;

    public ServicoService(final ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public List<Servico> findServicos() {
        return servicoRepository.findAll();
    }
}
