package br.com.bitwise.bithealth.modules.user.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class LoginResponseDTO {
    private UUID id;
    private String nome;
    private String email;
    private String token;
}
