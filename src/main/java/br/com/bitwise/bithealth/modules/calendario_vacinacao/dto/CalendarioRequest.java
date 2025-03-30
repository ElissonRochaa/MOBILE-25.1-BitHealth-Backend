package br.com.bitwise.bithealth.modules.calendario_vacinacao.dto;

import br.com.bitwise.bithealth.utils.annotations.ValidData;
import br.com.bitwise.bithealth.utils.annotations.ValidHorarioAtendimento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CalendarioRequest(
        @NotBlank(message = "O campo vacina é obrigatório")
        @Length(min = 3, max = 100, message = "O campo vacina deve ter entre 3 e 100 caracteres")
        String vacina,
        @NotNull(message = "O campo idade mínima é obrigatório")
        Integer idadeMinima,
        @NotNull(message = "O campo idade máxima é obrigatório")
        Integer idadeMaxima,
        @NotBlank(message = "O campo descrição é obrigatório")
        @Length(min = 3, max = 100, message = "O campo descrição deve ter entre 3 e 100 caracteres")
        String descricao,
        @NotBlank(message = "O campo data de início é obrigatório")
        @Length(min = 10, max = 10, message = "O campo data de inicio não é válido")
        @ValidData
        String dataInicio,
        @NotBlank(message = "O campo data de final é obrigatório")
        @Length(min = 10, max = 10, message = "O campo data de final não é válido")
        @ValidData
        String dataFim,
        String status
) {
}
