package br.com.bitwise.bithealth.modules.user.dto;

import br.com.bitwise.bithealth.modules.user.model.ENUM.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

public record UsuarioDTO(
        UUID id,
        String nome,
        String sobrenome,
        String cpf,
        String email,
        String senha,
        TipoUsuario tipoUsuario,
        Boolean ativo,
        LocalDateTime criadoEm
) {
}
