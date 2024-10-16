package dev.mmmartins.agendaservico.controller;

import dev.mmmartins.agendaservico.exception.SenhaInvalidaException;
import dev.mmmartins.agendaservico.exception.UsuarioJaExistenteException;
import dev.mmmartins.agendaservico.model.Usuario;
import dev.mmmartins.agendaservico.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    private final UsuarioService usuarioService;

    public LoginController(final UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String loginIndex() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(final Model model) {
        model.addAttribute("user", new Usuario());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") Usuario usuario,
                               final RedirectAttributes redirectAttributes) {
        try {
            usuarioService.createUser(usuario);
        } catch (UsuarioJaExistenteException | SenhaInvalidaException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/register";
        }

        return "redirect:/login";
    }
}
