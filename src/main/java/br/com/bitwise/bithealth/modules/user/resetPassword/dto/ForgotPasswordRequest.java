package br.com.bitwise.bithealth.modules.user.resetPassword.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ForgotPasswordRequest(
        @NotBlank
        @Email
        @Schema(description = "E-mail do usuário para redefinição de senha", example = "usuario@example.com")
        String email
) {}
