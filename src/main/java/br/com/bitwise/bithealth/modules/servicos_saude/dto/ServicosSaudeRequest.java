package br.com.bitwise.bithealth.modules.servicos_saude.dto;

import br.com.bitwise.bithealth.utils.annotations.ValidHorarioAtendimento;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ServicosSaudeRequest(
        @NotBlank(message = "O campo nome é obrigatório")
        @Length(min = 3, max = 100, message = "O campo nome deve ter entre 3 e 100 caracteres")
        String nome,
        @NotBlank(message = "O campo descrição é obrigatório")
        @Length(min = 3, max = 100, message = "O campo descrição deve ter entre 3 e 100 caracteres")
        String descricao,
        @JsonProperty("horario_inicio")
        @NotBlank(message = "O campo horário de início é obrigatório")
        @Length(min = 5, max = 5, message = "O campo horário de início deve ter 5 caracteres")
        @ValidHorarioAtendimento
        String horarioInicio,
        @JsonProperty("horario_fim")
        @NotBlank(message = "O campo horário de fim é obrigatório")
        @Length(min = 5, max = 5, message = "O campo horário de fim deve ter 5 caracteres")
        @ValidHorarioAtendimento
        String horarioFim,
        @JsonProperty("unidade_saude_id")
        @NotBlank(message = "O campo unidade de saúde é obrigatório")
        String unidadeSaudeId
) {
}
