package br.com.bitwise.bithealth.modules.user.dto;

public record LoginResponseDTO(
        String nome,
        String email,
        String token
) {
}
