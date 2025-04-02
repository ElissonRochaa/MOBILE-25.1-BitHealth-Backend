package br.com.bitwise.bithealth.modules.news.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewsRequest(

        @NotBlank(message = "O título é obrigatório.")
        @Size(max = 255, message = "O título não pode ter mais de 255 caracteres.")
        String titulo,

        @NotBlank(message = "O conteúdo da notícia é obrigatório.")
        String conteudo,

        @NotNull(message = "O ID do administrador é obrigatório.")
        String administradorId


) {
}
