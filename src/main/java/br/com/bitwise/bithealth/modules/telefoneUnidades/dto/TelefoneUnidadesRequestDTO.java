package br.com.bitwise.bithealth.modules.telefoneUnidades.dto;

import br.com.bitwise.bithealth.modules.telefoneUnidades.model.ENUM.TipoTelefone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record TelefoneUnidadesRequestDTO(

        @NotBlank(message = "ID da unidade é obrigatorio")
        String unidadeId,

        @NotBlank(message = "O número do telefone é obrigatório")
        @Size(min = 8, max = 20, message = "O número deve ter entre 8 e 20 caracteres")
        String numero,

        @NotNull(message = "O tipo de telefone é obrigatório")
        TipoTelefone tipo
) {}