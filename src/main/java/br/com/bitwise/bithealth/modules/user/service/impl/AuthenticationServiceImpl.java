package br.com.bitwise.bithealth.modules.user.service.impl;

import br.com.bitwise.bithealth.modules.user.dto.LoginRequestDTO;
import br.com.bitwise.bithealth.modules.user.dto.LoginResponseDTO;
import br.com.bitwise.bithealth.modules.user.exceptions.MismatchPasswordOrEmail;
import br.com.bitwise.bithealth.modules.user.model.Usuario;
import br.com.bitwise.bithealth.modules.user.repository.UsuarioRepository;
import br.com.bitwise.bithealth.modules.user.service.AuthenticationService;
import br.com.bitwise.bithealth.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @Override
    public LoginResponseDTO authenticate(LoginRequestDTO loginRequest) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(loginRequest.email());

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            if (passwordEncoder.matches(loginRequest.senha(), usuario.getSenha())) {
                String token = tokenService.generateToken(usuario);

                return new LoginResponseDTO(
                        usuario.getNome(),
                        usuario.getEmail(),
                        token
                );
            }
        }
        throw new MismatchPasswordOrEmail("Credenciais inválidas");
    }
}
