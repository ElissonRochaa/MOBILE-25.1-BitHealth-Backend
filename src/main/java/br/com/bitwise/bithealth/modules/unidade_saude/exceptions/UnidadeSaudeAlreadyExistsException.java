package br.com.bitwise.bithealth.modules.unidade_saude.exceptions;

public class UnidadeSaudeAlreadyExistsException extends RuntimeException {
    public UnidadeSaudeAlreadyExistsException(String message) {
        super(message);
    }
}
