package dev.mmmartins.agendaservico.controller;

import dev.mmmartins.agendaservico.exception.RegistroNaoEncontradoException;
import dev.mmmartins.agendaservico.model.Endereco;
import dev.mmmartins.agendaservico.repository.EnderecoRepository;
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
public class EnderecoController {
    private final EnderecoRepository enderecoRepository;


    public EnderecoController(final EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    @GetMapping("/enderecos")
    public String index(final Model model) {
        model.addAttribute("enderecos", enderecoRepository.findAll());
        return "enderecos/list";
    }

    @GetMapping("/enderecos/edit/{id}")
    public String index(final Model model,
                        @PathVariable Long id) {
        model.addAttribute("endereco", enderecoRepository.findById(id)
                .orElseThrow(RegistroNaoEncontradoException::new));
        return "enderecos/edit";
    }

    @PostMapping("/enderecos/cadastro")
    public String edit(@Valid @ModelAttribute("endereco") Endereco endereco,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Erro ao editar endereço: " + bindingResult);
            return "enderecos/edit";
        }
        try {
            enderecoRepository.save(endereco);
            redirectAttributes.addFlashAttribute("success", "Endereço editado com sucesso!");
        } catch (final Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao editar endereço: " + endereco.getId());
            return "enderecos/edit";
        }
        return "redirect:/enderecos";
    }
}
