package br.com.bitwise.bithealth.modules.calendario_vacinacao.dto;

public record CalendarioResponse(
        String tokenId,
        String vacina,
        Integer idadeMinima,
        Integer idadeMaxima,
        String descricao,
        String dataInicio,
        String dataFim,
        String status
) {
}
