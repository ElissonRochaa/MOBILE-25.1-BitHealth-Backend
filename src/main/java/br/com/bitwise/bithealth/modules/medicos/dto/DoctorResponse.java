package br.com.bitwise.bithealth.modules.medicos.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public record DoctorResponse(

        @JsonProperty("token_id")
        String tokenId,

        String nome,
        String crm,
        String especialidade,

        @JsonProperty("unidade_saude_id")
        UUID unidadeSaudeId,

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