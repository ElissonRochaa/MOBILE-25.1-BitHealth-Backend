package br.com.bitwise.bithealth.modules.medicos.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

public record DoctorRequest(

        @NotBlank(message = "O nome do médico é obrigatório")
        @Length(min = 3, max = 150, message = "O nome deve ter entre 3 e 150 caracteres")
        String nome,

        @NotBlank(message = "O CRM é obrigatório")
        @Length(min = 4, max = 20, message = "O CRM deve ter entre 4 e 20 caracteres")
        String crm,

        @NotBlank(message = "A especialidade é obrigatória")
        @Length(min = 3, max = 100, message = "A especialidade deve ter entre 3 e 100 caracteres")
        String especialidade,

        @NotNull(message = "O ID da unidade de saúde é obrigatório")
        @JsonProperty("unidade_saude_id")
        String unidadeSaudeTokenId,

        @NotNull(message = "A data de plantão é obrigatória")
        @JsonProperty("data_plantao")
        String dataPlantao,

        @NotNull(message = "O horário de início é obrigatório")
        @JsonProperty("horario_inicio")
        String horarioInicio,

        @NotNull(message = "O horário de fim é obrigatório")
        @JsonProperty("horario_fim")
        String horarioFim,

        @NotBlank(message = "O tipo de atendimento é obrigatório")
        @Length(min = 3, message = "O tipo de atendimento deve ter no mínimo 3 caracteres")
        String tipo

) {}