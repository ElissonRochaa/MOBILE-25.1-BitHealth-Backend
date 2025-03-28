package br.com.bitwise.bithealth.modules.user.service;

import br.com.bitwise.bithealth.modules.user.dto.RegistroUsuarioDTO;
import br.com.bitwise.bithealth.modules.user.model.Usuario;
import br.com.bitwise.bithealth.modules.user.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistroService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario registrarNovoUsuario(RegistroUsuarioDTO registroDTO) {
        if (usuarioRepository.existsByEmail(registroDTO.email())) {
            throw new RuntimeException("Email já cadastrado");
        }

        if (usuarioRepository.existsByCpf(registroDTO.cpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(registroDTO.nome());
        novoUsuario.setSobrenome(registroDTO.sobrenome());
        novoUsuario.setCpf(registroDTO.cpf());
        novoUsuario.setEmail(registroDTO.email());

        novoUsuario.setSenha(passwordEncoder.encode(registroDTO.senha()));

        novoUsuario.setTipoUsuario(registroDTO.tipoUsuario());

        novoUsuario.setAtivo(true);

        return usuarioRepository.save(novoUsuario);
    }
}