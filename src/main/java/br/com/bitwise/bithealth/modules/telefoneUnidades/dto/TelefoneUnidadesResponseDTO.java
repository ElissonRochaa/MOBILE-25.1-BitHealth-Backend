package br.com.bitwise.bithealth.modules.telefoneUnidades.dto;

import br.com.bitwise.bithealth.modules.telefoneUnidades.model.ENUM.TipoTelefone;
import com.fasterxml.jackson.annotation.JsonProperty;

public record TelefoneUnidadesResponseDTO(

        @JsonProperty(namespace = "token_Id")
        String telefoneTokenId,
        String numero,
        TipoTelefone tipo,
        boolean ativo
) {}