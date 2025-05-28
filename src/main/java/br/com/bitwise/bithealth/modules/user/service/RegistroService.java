package br.com.bitwise.bithealth.modules.user.service;

import br.com.bitwise.bithealth.modules.user.dto.RegistroUsuarioDTO;
import br.com.bitwise.bithealth.modules.user.dto.UsuarioDTO;

public interface RegistroService {
    UsuarioDTO registrarNovoUsuario(RegistroUsuarioDTO registroDTO);
}
