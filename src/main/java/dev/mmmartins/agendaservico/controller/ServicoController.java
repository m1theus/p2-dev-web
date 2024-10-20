package dev.mmmartins.agendaservico.controller;

import dev.mmmartins.agendaservico.exception.RegistroNaoEncontradoException;
import dev.mmmartins.agendaservico.model.Servico;
import dev.mmmartins.agendaservico.repository.ServicoRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ServicoController {
    final ServicoRepository servicoRepository;

    public ServicoController(final ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    @GetMapping("/servicos")
    public String index(Model model) {
        model.addAttribute("servicos", servicoRepository.findAll());
        return "servicos/list";
    }

    @GetMapping("/servicos/cadastro")
    public String cadastro(Model model) {
        model.addAttribute("servico", new Servico());
        model.addAttribute("action", "create");
        return "servicos/create";
    }

    @GetMapping("/servicos/edit/{id}")
    public String cadastro(Model model, @PathVariable Long id) {
        model.addAttribute("servico", servicoRepository.findById(id)
                .orElseThrow(RegistroNaoEncontradoException::new));
        model.addAttribute("action", "edit");
        return "servicos/create";
    }

    @GetMapping("/servicos/delete/{id}")
    public String delete(RedirectAttributes redirectAttributes, @PathVariable Long id) {
        try {
            final var servico = servicoRepository.findById(id)
                    .orElseThrow(RegistroNaoEncontradoException::new);
            servicoRepository.delete(servico);
            redirectAttributes.addFlashAttribute("success", "Serviço excluido com sucesso!");
        } catch (final Exception e) {
            redirectAttributes.addFlashAttribute("error", "Falha ao excluir serviço!");
        }
        return "redirect:/servicos";
    }

    @PostMapping("/servicos/cadastro")
    public String salvar(@Valid @ModelAttribute("servico") Servico servico,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Erro ao cadastrar serviço: " + bindingResult);
            return "servicos/create";
        }

        try {
            servicoRepository.save(servico);
            redirectAttributes.addFlashAttribute("success", "Serviço cadastrado com sucesso!");
        } catch (final Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao cadastrar serviço: " + e.getMessage());
            return "servicos/create";
        }
        return "redirect:/servicos";
    }
}
