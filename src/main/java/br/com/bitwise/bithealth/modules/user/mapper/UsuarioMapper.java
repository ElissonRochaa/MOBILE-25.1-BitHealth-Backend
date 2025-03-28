package br.com.bitwise.bithealth.modules.user.mapper;

import br.com.bitwise.bithealth.modules.user.dto.RegistroUsuarioDTO;
import br.com.bitwise.bithealth.modules.user.dto.UsuarioDTO;
import br.com.bitwise.bithealth.modules.user.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public Usuario toEntity(RegistroUsuarioDTO registroDTO) {
        return new Usuario(
                null,
                registroDTO.nome(),
                registroDTO.sobrenome(),
                registroDTO.cpf(),
                registroDTO.email(),
                registroDTO.senha(),
                registroDTO.tipoUsuario()
        );
    }

    public Usuario toEntity(UsuarioDTO usuarioDTO) {
        return new Usuario(
                usuarioDTO.getId(),
                usuarioDTO.getNome(),
                usuarioDTO.getSobrenome(),
                usuarioDTO.getCpf(),
                usuarioDTO.getEmail(),
                usuarioDTO.getSenha(),
                usuarioDTO.getTipoUsuario(),
                usuarioDTO.getAtivo(),
                usuarioDTO.getCriadoEm()
        );
    }

    public UsuarioDTO toDto(Usuario usuario) {
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .sobrenome(usuario.getSobrenome())
                .cpf(usuario.getCpf())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .tipoUsuario(usuario.getTipoUsuario())
                .ativo(usuario.getAtivo())
                .criadoEm(usuario.getCriadoEm())
                .build();
    }

}
