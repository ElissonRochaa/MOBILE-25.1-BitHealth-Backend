package br.com.bitwise.bithealth.modules.calendario_vacinacao.exceptions;

public class DateInitAfterDateEndException extends RuntimeException {
    public DateInitAfterDateEndException(String message) {
        super(message);
    }
}
