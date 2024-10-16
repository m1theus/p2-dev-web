package dev.mmmartins.agendaservico.exception;

public class SenhaInvalidaException extends RuntimeException {
    public SenhaInvalidaException(final String message) {
        super(message);
    }

    public SenhaInvalidaException() {
        super("Senha Inválida!");
    }
}
