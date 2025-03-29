package br.com.bitwise.bithealth.modules.medicamentos.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MedicamentoResponse(
        @JsonProperty(namespace = "user_token_id")
        String tokenId,
        String nome,
        String descricao,
        Integer quantidade,
        String tipoMedicamento
) {
}
