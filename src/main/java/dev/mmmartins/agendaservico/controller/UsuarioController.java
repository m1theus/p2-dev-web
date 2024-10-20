package dev.mmmartins.agendaservico.controller;

import dev.mmmartins.agendaservico.exception.RegistroNaoEncontradoException;
import dev.mmmartins.agendaservico.model.Usuario;
import dev.mmmartins.agendaservico.repository.TipoAcessoRepository;
import dev.mmmartins.agendaservico.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final TipoAcessoRepository tipoAcessoRepository;

    public UsuarioController(final UsuarioService usuarioService, final TipoAcessoRepository tipoAcessoRepository) {
        this.usuarioService = usuarioService;
        this.tipoAcessoRepository = tipoAcessoRepository;
    }

    @GetMapping("/usuarios")
    public String index(final Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());
        model.addAttribute("editUsuario", new Usuario());
        model.addAttribute("rolesAcesso", tipoAcessoRepository.findAll());
        return "usuarios/list";
    }

    @PostMapping("/usuarios")
    public String update(@ModelAttribute("usuario") final Usuario usuario,
                       final RedirectAttributes redirectAttributes) {
        try {
            usuarioService.update(usuario);
            redirectAttributes.addFlashAttribute("success", "Role atualizada com sucesso!");
        } catch (final RegistroNaoEncontradoException e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao atualizar role: " + e.getMessage());
        }
        return "redirect:/usuarios";
    }

}
