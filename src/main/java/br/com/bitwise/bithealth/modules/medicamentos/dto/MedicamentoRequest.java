package br.com.bitwise.bithealth.modules.medicamentos.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record MedicamentoRequest(
        @NotBlank(message = "O campo nome é obrigatório")
        @Length(min = 3, max = 100, message = "O campo nome deve ter entre 3 e 100 caracteres")
        String nome,
        @NotBlank(message = "O campo descrição é obrigatório")
        @Length(min = 3, max = 100, message = "O campo descrição deve ter entre 3 e 100 caracteres")
        String descricao,
        @NotNull(message = "O campo quantidade é obrigatório")
        @Min(value = 1, message = "O campo quantidade deve ser maior que 0")
        Integer quantidade,
        @NotBlank(message = "O campo tipo de medicamento é obrigatório")
        @Length(min = 3, max = 100, message = "O campo tipo de medicamento deve ter entre 3 e 100 caracteres")
        @JsonProperty(namespace = "tipo_medicamento")
        String tipoMedicamento,
        @NotBlank(message = "O campo token de unidade de saúde é obrigatório")
        @JsonProperty(namespace = "unidade_saude_id")
        String userTokenId
) {
}
