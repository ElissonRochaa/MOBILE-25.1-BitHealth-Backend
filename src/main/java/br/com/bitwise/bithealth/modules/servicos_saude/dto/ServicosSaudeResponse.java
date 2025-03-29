package br.com.bitwise.bithealth.modules.servicos_saude.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ServicosSaudeResponse(
        String id,
        String nome,
        String descricao,
        @JsonProperty(namespace = "horario_inicio")
        String horarioInicio,
        @JsonProperty(namespace = "horario_fim")
        String horarioFim
) {
}
