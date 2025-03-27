package br.com.bitwise.bithealth.modules.user.service;

import br.com.bitwise.bithealth.modules.user.dto.RegistroUsuarioDTO;
import br.com.bitwise.bithealth.modules.user.model.Usuario;
import br.com.bitwise.bithealth.modules.user.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistroService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario registrarNovoUsuario(RegistroUsuarioDTO registroDTO) {
        if (usuarioRepository.existsByEmail(registroDTO.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }

        if (usuarioRepository.existsByCpf(registroDTO.getCpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(registroDTO.getNome());
        novoUsuario.setSobrenome(registroDTO.getSobrenome());
        novoUsuario.setCpf(registroDTO.getCpf());
        novoUsuario.setEmail(registroDTO.getEmail());

        novoUsuario.setSenha(passwordEncoder.encode(registroDTO.getSenha()));

        novoUsuario.setTipoUsuario(registroDTO.getTipoUsuario());

        novoUsuario.setAtivo(true);

        return usuarioRepository.save(novoUsuario);
    }
}