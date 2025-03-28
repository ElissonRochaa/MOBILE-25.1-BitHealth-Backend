package br.com.bitwise.bithealth.modules.user.mapper;

import br.com.bitwise.bithealth.modules.user.dto.RegistroUsuarioDTO;
import br.com.bitwise.bithealth.modules.user.dto.UsuarioDTO;
import br.com.bitwise.bithealth.modules.user.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public Usuario toEntity(RegistroUsuarioDTO registroDTO) {
        return new Usuario(
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
                usuarioDTO.id(),
                usuarioDTO.nome(),
                usuarioDTO.sobrenome(),
                usuarioDTO.cpf(),
                usuarioDTO.email(),
                usuarioDTO.senha(),
                usuarioDTO.tipoUsuario(),
                usuarioDTO.ativo(),
                usuarioDTO.criadoEm()
        );
    }

    public UsuarioDTO toDto(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getSobrenome(),
                usuario.getCpf(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getTipoUsuario(),
                usuario.getAtivo(),
                usuario.getCriadoEm()
        );
    }

}
