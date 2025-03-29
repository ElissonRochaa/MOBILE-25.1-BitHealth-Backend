package br.com.bitwise.bithealth.modules.user.dto;

public record MensagemRespostaDTO(
        String mensagem,
        boolean sucesso
) {
    public static MensagemRespostaDTO sucesso(String mensagem) {
        return new MensagemRespostaDTO(mensagem, true);
    }

    public static MensagemRespostaDTO erro(String mensagem) {
        return new MensagemRespostaDTO(mensagem, false);
    }
}
