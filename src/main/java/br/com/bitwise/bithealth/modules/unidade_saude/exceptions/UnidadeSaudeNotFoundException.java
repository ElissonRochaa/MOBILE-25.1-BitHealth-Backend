package br.com.bitwise.bithealth.modules.unidade_saude.exceptions;

public class UnidadeSaudeNotFoundException extends RuntimeException {
    public UnidadeSaudeNotFoundException(String message) {
        super(message);
    }
}
