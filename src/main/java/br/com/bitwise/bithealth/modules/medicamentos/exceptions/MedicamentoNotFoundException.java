package br.com.bitwise.bithealth.modules.medicamentos.exceptions;

public class MedicamentoNotFoundException extends RuntimeException {
    public MedicamentoNotFoundException(String message) {
        super(message);
    }
}
