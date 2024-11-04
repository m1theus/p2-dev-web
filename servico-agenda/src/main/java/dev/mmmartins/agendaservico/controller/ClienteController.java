package dev.mmmartins.agendaservico.controller;

import dev.mmmartins.agendaservico.TipoAcao;
import dev.mmmartins.agendaservico.exception.RegistroJaExistenteException;
import dev.mmmartins.agendaservico.model.Cliente;
import dev.mmmartins.agendaservico.service.ClienteService;
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
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(final ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/clientes")
    public String index(Model model) {
        model.addAttribute("clientes", clienteService.findAll());
        return "/clientes/list";
    }

    @GetMapping("/clientes/cadastro")
    public String cadastro(Model model) {
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("acao", INSERT);
        return "/clientes/create";
    }

    @GetMapping("/clientes/edit/{cpf}")
    public String update(@PathVariable("cpf") String cpf, Model model) {
        model.addAttribute("cliente", clienteService.findByCpf(cpf));
        model.addAttribute("acao", UPDATE);
        return "/clientes/create";
    }

    @PostMapping("/clientes/cadastro")
    public String salvar(@Valid @ModelAttribute("cliente") Cliente cliente,
                         @ModelAttribute("acao") TipoAcao acao,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Erro ao cadastrar cliente: " + bindingResult);
            return "clientes/create";
        }

        try {
            if (UPDATE.equals(acao)) {
                clienteService.update(cliente);
                redirectAttributes.addFlashAttribute("success", "Cliente editado com sucesso!");
            } else {
                clienteService.save(cliente);
                redirectAttributes.addFlashAttribute("success", "Cliente cadastrado com sucesso!");
            }
        } catch (final RegistroJaExistenteException e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao cadastrar cliente: " + e.getMessage());
            return "redirect:/clientes/cadastro";
        }
        return "redirect:/clientes";
    }

    @GetMapping("/clientes/delete/{cpf}")
    public String deleteCliente(@PathVariable("cpf") String cpf, RedirectAttributes redirectAttributes) {
        final var isDeleted = clienteService.deleteByCpf(cpf);

        if (isDeleted) {
            redirectAttributes.addFlashAttribute("success", "Cliente deletado com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Erro ao deletar cliente: " + cpf);
        }

        return "redirect:/clientes";
    }
}
