package dev.mmmartins.agendaservico.exception;

public class UsuarioJaExistenteException extends RuntimeException {
    public UsuarioJaExistenteException() {
        super("Usuário já existente!");
    }

    public UsuarioJaExistenteException(final String message) {
        super(message);
    }
}
