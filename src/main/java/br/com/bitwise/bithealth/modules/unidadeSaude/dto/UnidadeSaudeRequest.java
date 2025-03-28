package br.com.bitwise.bithealth.modules.unidadeSaude.dto;

import br.com.bitwise.bithealth.utils.annotations.ValidHorarioAtendimento;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UnidadeSaudeRequest(
        @NotBlank(message = "O campo nome é obrigatório")
        @Length(min = 3, max = 100, message = "O campo nome deve ter entre 3 e 100 caracteres")
        String nome,
        @NotBlank(message = "O campo tipo é obrigatório")
        @Length(min = 3, max = 100, message = "O campo tipo deve ter entre 3 e 100 caracteres")
        String tipo,
        @JsonProperty(namespace = "horario_inicio_atendimento")
        @NotBlank(message = "O campo horário de início de atendimento é obrigatório")
        @Length(min = 5, max = 5, message = "O campo horário de início de atendimento deve ter 5 caracteres")
        @ValidHorarioAtendimento
        String horarioInicioAtendimento,
        @JsonProperty(namespace = "horario_fim_atendimento")
        @NotBlank(message = "O campo horário de fim de atendimento é obrigatório")
        @Length(min = 5, max = 5, message = "O campo horário de fim de atendimento deve ter 5 caracteres")
        @ValidHorarioAtendimento
        String horarioFimAtendimento
) {
}
