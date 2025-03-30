package br.com.bitwise.bithealth.modules.unidade_saude.endereco.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record EnderecoUnidadesRequestDTO(
        @NotBlank(message = "O campo logradouro é obrigatório")
        @Length(min = 3, max = 255, message = "O campo logradouro deve ter entre 3 e 255 caracteres")
        String logradouro,

        @NotBlank(message = "O campo número é obrigatório")
        @Length(min = 1, max = 10, message = "O campo número deve ter entre 1 e 10 caracteres")
        String numero,

        @Length(min = 1, max = 100, message = "O campo complemento deve ter entre 1 e 100 caracteres")
        String complemento,

        @NotBlank(message = "O campo bairro é obrigatório")
        @Length(min = 3, max = 100, message = "O campo bairro deve ter entre 3 e 100 caracteres")
        String bairro,

        @NotBlank(message = "O campo cidade é obrigatório")
        @Length(min = 3, max = 100, message = "O campo cidade deve ter entre 3 e 100 caracteres")
        String cidade,

        @NotBlank(message = "O campo estado é obrigatório")
        @Length(min = 2, max = 2, message = "O campo estado deve ter 2 caracteres")
        String estado,

        @NotBlank(message = "O campo CEP é obrigatório")
        @Length(min = 8, max = 10, message = "O campo CEP deve ter entre 8 e 10 caracteres")
        String cep,

        @NotBlank(message = "O campo token de unidade de saúde é obrigatório")
        @JsonProperty(namespace = "unidade_saude_id")
        String unidadeTokenId
) {
}
