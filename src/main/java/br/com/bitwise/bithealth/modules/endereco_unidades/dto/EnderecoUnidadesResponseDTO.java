package br.com.bitwise.bithealth.modules.endereco_unidades.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record EnderecoUnidadesResponseDTO(
        @JsonProperty(namespace = "unidade_saude_id")
        String unidadeSaudeId,

        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        BigDecimal latitude,
        BigDecimal longitude,
        String cep
) {
}
