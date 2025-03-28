package br.com.bitwise.bithealth.modules.user.dto;

import br.com.bitwise.bithealth.modules.user.model.ENUM.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private UUID id;

    private String nome;

    private String sobrenome;

    private String cpf;

    private String email;

    private String senha;

    private TipoUsuario tipoUsuario;

    private Boolean ativo = true;

    private LocalDateTime criadoEm = LocalDateTime.now();
}
