package br.com.bitwise.bithealth.modules.user.service;

import br.com.bitwise.bithealth.modules.user.dto.RegistroUsuarioDTO;
import br.com.bitwise.bithealth.modules.user.dto.UsuarioDTO;
import br.com.bitwise.bithealth.modules.user.exceptions.CPFAlreadyExistsException;
import br.com.bitwise.bithealth.modules.user.exceptions.EmailAlreadyExistsException;
import br.com.bitwise.bithealth.modules.user.mapper.UsuarioMapper;
import br.com.bitwise.bithealth.modules.user.model.Usuario;
import br.com.bitwise.bithealth.modules.user.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistroService {

    private final UsuarioMapper usuarioMapper;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistroService(UsuarioRepository usuarioRepository,
                           PasswordEncoder passwordEncoder,
                           UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.usuarioMapper = usuarioMapper;
    }


    @Transactional
    public UsuarioDTO registrarNovoUsuario(RegistroUsuarioDTO registroDTO) {
        if (usuarioRepository.existsByEmail(registroDTO.email())) {
            throw new EmailAlreadyExistsException("Email já cadastrado");
        }

        if (usuarioRepository.existsByCpf(registroDTO.cpf())) {
            throw new CPFAlreadyExistsException("CPF já cadastrado");
        }

        Usuario usuario = usuarioMapper.toEntity(registroDTO);
        usuario.setSenha(passwordEncoder.encode(registroDTO.senha()));
        usuario.setAtivo(true);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return usuarioMapper.toDto(usuarioSalvo);

    }
}