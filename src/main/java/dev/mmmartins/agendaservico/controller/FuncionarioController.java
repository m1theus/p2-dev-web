package dev.mmmartins.agendaservico.controller;

import dev.mmmartins.agendaservico.TipoAcao;
import dev.mmmartins.agendaservico.model.Funcionario;
import dev.mmmartins.agendaservico.repository.FuncionarioRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static dev.mmmartins.agendaservico.TipoAcao.INSERT;
import static dev.mmmartins.agendaservico.TipoAcao.UPDATE;

@Controller
public class FuncionarioController {
    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioController(final FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @GetMapping("/funcionarios")
    public String index(Model model) {
        model.addAttribute("funcionarios", funcionarioRepository.findAll());
        return "/funcionarios/list";
    }

    @GetMapping("/funcionarios/cadastro")
    public String cadastro(Model model) {
        model.addAttribute("funcionario", new Funcionario());
        model.addAttribute("acao", INSERT);
        return "/funcionarios/create";
    }

    @GetMapping("/funcionarios/edit/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        model.addAttribute("funcionario", funcionarioRepository.findById(id));
        model.addAttribute("acao", UPDATE);
        return "/funcionarios/create";
    }

    @PostMapping("/funcionarios/cadastro")
    public String salvar(@Valid @ModelAttribute("funcionario") Funcionario funcionario,
                         @ModelAttribute("acao") TipoAcao acao,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Erro ao cadastrar funcionário: " + bindingResult);
            return "funcionarios/create";
        }

        try {
            funcionarioRepository.save(funcionario);
            redirectAttributes.addFlashAttribute("success", "Funcionário cadastrado com sucesso!");
        } catch (final Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao cadastrar funcionário: " + e.getMessage());
            return "redirect:/funcionarios/cadastro";
        }
        return "redirect:/funcionarios";
    }

    @GetMapping("/funcionarios/delete/{id}")
    public String deletarFuncionario(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            funcionarioRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Funcionário deletado com sucesso!");
        } catch (final Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao deletar funcionário: " + id);
        }

        return "redirect:/funcionarios";
    }
}
