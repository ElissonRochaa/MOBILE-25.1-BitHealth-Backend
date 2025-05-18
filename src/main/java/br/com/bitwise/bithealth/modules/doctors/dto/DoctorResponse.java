package br.com.bitwise.bithealth.modules.doctors.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public record DoctorResponse(

        @JsonProperty("token_id")
        String tokenId,

        String nome,
        String crm,
        String especialidade,

        @JsonProperty("unidade_saude_name")
        String unidadeSaudeNome,

        @JsonProperty("data_plantao")
        String dataPlantao,

        @JsonProperty("horario_inicio")
        String horarioInicio,

        @JsonProperty("horario_fim")
        String horarioFim,

        String tipo,

        @JsonProperty("criado_em")
        LocalDateTime criadoEm

) {}