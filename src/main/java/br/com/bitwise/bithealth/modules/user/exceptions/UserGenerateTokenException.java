package br.com.bitwise.bithealth.modules.user.exceptions;

public class UserGenerateTokenException extends RuntimeException {

    public UserGenerateTokenException(String id) {
        super("Erro ao gerar token para o usuário com id: " + id);
    }
}
