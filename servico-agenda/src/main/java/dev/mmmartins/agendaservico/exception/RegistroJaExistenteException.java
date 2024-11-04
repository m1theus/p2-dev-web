package dev.mmmartins.agendaservico.exception;

public class RegistroJaExistenteException extends RuntimeException {
    public RegistroJaExistenteException() {
        super("Registro já existente!");
    }

    public RegistroJaExistenteException(final String message) {
        super(message);
    }
}
