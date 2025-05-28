package br.com.bitwise.bithealth.modules.user.resetPassword.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ResetPasswordRequest(
        @NotBlank
        @Schema(description = "Token recebido por e-mail")
        String token,

        @NotBlank
        @Size(min = 8)
        @Schema(description = "Nova senha para o usuário", example = "NovaSenha@123")
        String newPassword
) {}
