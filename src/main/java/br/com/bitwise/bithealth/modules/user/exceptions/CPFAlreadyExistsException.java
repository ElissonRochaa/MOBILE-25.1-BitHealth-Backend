package br.com.bitwise.bithealth.modules.user.exceptions;

public class CPFAlreadyExistsException extends RuntimeException {
    public CPFAlreadyExistsException(String message) {
        super(message);
    }
}
