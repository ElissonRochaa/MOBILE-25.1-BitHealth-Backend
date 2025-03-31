package br.com.bitwise.bithealth.modules.user.mapper;

import br.com.bitwise.bithealth.modules.user.dto.RegistroUsuarioDTO;
import br.com.bitwise.bithealth.modules.user.dto.UsuarioDTO;
import br.com.bitwise.bithealth.modules.user.model.Usuario;

public interface UsuarioMapper {
    Usuario toEntity(RegistroUsuarioDTO registroDTO);
    UsuarioDTO toDto(Usuario usuario);
}
