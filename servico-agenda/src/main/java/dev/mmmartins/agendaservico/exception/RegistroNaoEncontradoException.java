package dev.mmmartins.agendaservico.exception;

public class RegistroNaoEncontradoException extends RuntimeException {
    public RegistroNaoEncontradoException() {
        super("Registro não encontrado!");
    }

    public RegistroNaoEncontradoException(String message) {
        super(message);
    }
}
