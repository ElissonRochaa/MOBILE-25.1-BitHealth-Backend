package br.com.bitwise.bithealth.modules.unidade_saude.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UnidadeSaudeResponse(
        @JsonProperty(namespace = "token_Id")
        String tokenId,
        String nome,
        String tipo,
        @JsonProperty(namespace = "horario_inicio_atendimento")
        String horarioInicioAtendimento,
        @JsonProperty(namespace = "horario_fim_atendimento")
        String horarioFimAtendimento
) {
}
