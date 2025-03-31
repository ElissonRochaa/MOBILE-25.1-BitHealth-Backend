package br.com.bitwise.bithealth.modules.user.mapper.impl;

import br.com.bitwise.bithealth.modules.user.dto.RegistroUsuarioDTO;
import br.com.bitwise.bithealth.modules.user.dto.UsuarioDTO;
import br.com.bitwise.bithealth.modules.user.endereco.mapper.EnderecoMapper;
import br.com.bitwise.bithealth.modules.user.endereco.dto.EnderecoDTO;
import br.com.bitwise.bithealth.modules.user.mapper.UsuarioMapper;
import br.com.bitwise.bithealth.modules.user.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioMapperImp implements UsuarioMapper {

    private final EnderecoMapper enderecoMapper;

    @Override
    public Usuario toEntity(RegistroUsuarioDTO registroDTO) {
        return new Usuario(
                registroDTO.nome(),
                registroDTO.sobrenome(),
                registroDTO.cpf(),
                registroDTO.email(),
                registroDTO.senha(),
                registroDTO.tipoUsuario(),
                registroDTO.numeroTelefone()
        );
    }

    @Override
    public UsuarioDTO toDto(Usuario usuario) {

        EnderecoDTO enderecoDTO = null;
        if (usuario.getEndereco() != null) {
            enderecoDTO = enderecoMapper.toDto(usuario.getEndereco());
        }

        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getSobrenome(),
                usuario.getCpf(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getTipoUsuario(),
                usuario.getNumeroTelefone(),
                usuario.getAtivo(),
                usuario.getCriadoEm(),
                enderecoDTO
        );
    }

}
