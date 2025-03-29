package br.com.bitwise.bithealth.modules.user.dto;

import br.com.bitwise.bithealth.modules.user.endereco.dto.EnderecoDTO;
import br.com.bitwise.bithealth.modules.user.model.ENUM.TipoUsuario;

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
        String numeroTelefone,
        Boolean ativo,
        LocalDateTime criadoEm,
        EnderecoDTO endereco
) {
}
