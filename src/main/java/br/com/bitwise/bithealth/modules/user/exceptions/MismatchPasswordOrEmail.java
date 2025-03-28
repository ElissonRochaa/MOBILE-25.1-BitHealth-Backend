package br.com.bitwise.bithealth.modules.user.exceptions;

public class MismatchPasswordOrEmail extends RuntimeException {
  public MismatchPasswordOrEmail(String message) {
    super(message);
  }
}
