package br.com.bitwise.bithealth.modules.user.exceptions;

public class NumeroTelefoneAlreadyExistsException extends RuntimeException {
  public NumeroTelefoneAlreadyExistsException(String message) {
    super(message);
  }
}
