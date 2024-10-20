package dev.mmmartins.agendaservico.controller;

import dev.mmmartins.agendaservico.TipoAcao;
import dev.mmmartins.agendaservico.model.Agenda;
import dev.mmmartins.agendaservico.repository.ServicoRepository;
import dev.mmmartins.agendaservico.service.AgendaService;
import dev.mmmartins.agendaservico.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AgendaController {
    private final AgendaService agendaService;
    private final ServicoRepository servicoRepository;
    private final ClienteService clienteService;

    public AgendaController(final AgendaService agendaService,
                            final ServicoRepository servicoRepository,
                            final ClienteService clienteService) {
        this.agendaService = agendaService;
        this.servicoRepository = servicoRepository;
        this.clienteService = clienteService;
    }

    @GetMapping("/agendas")
    public String index(Model model) {
        model.addAttribute("agendas", agendaService.findAll());
        return "agendas/list";
    }

    @GetMapping("/agendas/cadastro")
    public String cadastro(Model model) {
        model.addAttribute("agenda", new Agenda());
        model.addAttribute("action", TipoAcao.INSERT);
        model.addAttribute("todosServicos", servicoRepository.findAll());
        model.addAttribute("todosClientes", clienteService.findAll());
        return "agendas/create";
    }

    @GetMapping("/agendas/edit/{id}")
    public String update(Model model,
                         @PathVariable Long id) {
        model.addAttribute("agenda", agendaService.findById(id));
        model.addAttribute("action", TipoAcao.UPDATE);
        model.addAttribute("todosServicos", servicoRepository.findAll());
        model.addAttribute("todosClientes", clienteService.findAll());
        return "agendas/create";
    }

    @GetMapping("/agendas/delete/{id}")
    public String delete(@PathVariable Long id,
                         RedirectAttributes redirectAttributes) {
        final var isDeleted = agendaService.delete(id);
        if (isDeleted) {
            redirectAttributes.addFlashAttribute("success", "Agenda deletado com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Erro ao deletar agenda: " + id);
        }
        return "redirect:/agendas";
    }

    @PostMapping("/agendas/cadastro")
    public String addServico(@Valid @ModelAttribute Agenda agenda,
                             @RequestParam List<Long> servicosIds,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Erro ao cadastradar agenda: " + bindingResult);
            return "agendas/create";
        }

        try {
            final var servicos = servicoRepository.findAllById(servicosIds);
            agenda.setServicos(servicos);
            agendaService.save(agenda);
            redirectAttributes.addFlashAttribute("success", "Agenda cadastrada com sucesso!");
        } catch (final Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao cadastradar agenda: " + agenda.getId());
            return "agendas/create";
        }

        return "redirect:/agendas";
    }
}
