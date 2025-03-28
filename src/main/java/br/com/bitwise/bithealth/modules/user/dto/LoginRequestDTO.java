package br.com.bitwise.bithealth.modules.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public record LoginRequestDTO(

        @NotBlank(message = "Email é obrigatório")
        String email,

        @NotBlank(message = "Senha é obrigatória")
        String senha
) {
}
