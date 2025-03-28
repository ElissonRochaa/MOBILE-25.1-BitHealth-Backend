package br.com.bitwise.bithealth.modules.user.service;

import br.com.bitwise.bithealth.modules.user.dto.LoginRequestDTO;
import br.com.bitwise.bithealth.modules.user.dto.LoginResponseDTO;
import br.com.bitwise.bithealth.modules.user.exceptions.MismatchPasswordOrEmail;
import br.com.bitwise.bithealth.modules.user.model.Usuario;
import br.com.bitwise.bithealth.modules.user.repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthenticationService {

    private Key jwtSecretKey;
    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;

    public AuthenticationService(Key jwtSecretKey, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.jwtSecretKey = jwtSecretKey;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private final long EXPIRATION_TIME = 864_000_000;

    public LoginResponseDTO authenticate(LoginRequestDTO loginRequest) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(loginRequest.email());

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            if (passwordEncoder.matches(loginRequest.senha(), usuario.getSenha())) {
                String token = generateJwtToken(usuario);

                return new LoginResponseDTO(
                        usuario.getNome(),
                        usuario.getEmail(),
                        token
                );
            }
        }

        throw new MismatchPasswordOrEmail("Credenciais inválidas");
    }

    private String generateJwtToken(Usuario usuario) {
        return Jwts.builder()
                .setSubject(usuario.getEmail())
                .claim("userId", usuario.getId())
                .claim("userType", usuario.getTipoUsuario())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(jwtSecretKey)
                .compact();
    }
}
