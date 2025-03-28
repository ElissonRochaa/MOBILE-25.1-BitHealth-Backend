package br.com.bitwise.bithealth.modules.user.dto;

import lombok.Data;

import java.util.UUID;

public record LoginResponseDTO(
        String nome,
        String email,
        String token
) {
}
