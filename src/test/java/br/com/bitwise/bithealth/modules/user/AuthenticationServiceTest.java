package br.com.bitwise.bithealth.modules.user;

import br.com.bitwise.bithealth.modules.user.dto.LoginRequestDTO;
import br.com.bitwise.bithealth.modules.user.dto.LoginResponseDTO;
import br.com.bitwise.bithealth.modules.user.exceptions.MismatchPasswordOrEmail;
import br.com.bitwise.bithealth.modules.user.model.Usuario;
import br.com.bitwise.bithealth.modules.user.repository.UsuarioRepository;
import br.com.bitwise.bithealth.modules.user.service.AuthenticationService;
import br.com.bitwise.bithealth.security.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class AuthenticationServiceTest {
    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private AuthenticationService authenticationService;

    private Usuario mockUsuario;
    private LoginRequestDTO loginRequestDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Preparando um usuário de mock
        mockUsuario = new Usuario();
        mockUsuario.setNome("Test User");
        mockUsuario.setEmail("test@example.com");
        mockUsuario.setSenha("hashedPassword");

        // DTO de requisição
        loginRequestDTO = new LoginRequestDTO("test@example.com", "password123");
    }

    @Test
    @DisplayName("Deve autenticar com sucesso quando credenciais forem válidas")
    void authenticateSuccessWithValidCredentials() {
        // Arrange
        when(usuarioRepository.findByEmail(loginRequestDTO.email())).thenReturn(Optional.of(mockUsuario));
        when(passwordEncoder.matches(loginRequestDTO.senha(), mockUsuario.getSenha())).thenReturn(true);
        when(tokenService.generateToken(mockUsuario)).thenReturn("mocked-jwt-token");

        // Act
        LoginResponseDTO response = authenticationService.authenticate(loginRequestDTO);

        // Assert
        assertNotNull(response);
        assertEquals(mockUsuario.getNome(), response.nome());
        assertEquals(mockUsuario.getEmail(), response.email());
        assertEquals("mocked-jwt-token", response.token());
    }

    @Test
    @DisplayName("Deve lançar exceção quando usuário não for encontrado")
    void authenticateThrowsExceptionWhenUserNotFound() {
        // Arrange
        when(usuarioRepository.findByEmail(loginRequestDTO.email())).thenReturn(Optional.empty());

        // Act & Assert
        MismatchPasswordOrEmail exception = assertThrows(MismatchPasswordOrEmail.class, () -> {
            authenticationService.authenticate(loginRequestDTO);
        });

        assertEquals("Credenciais inválidas", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando a senha estiver incorreta")
    void authenticateThrowsExceptionWhenPasswordIsIncorrect() {
        // Arrange
        when(usuarioRepository.findByEmail(loginRequestDTO.email())).thenReturn(Optional.of(mockUsuario));
        when(passwordEncoder.matches(loginRequestDTO.senha(), mockUsuario.getSenha())).thenReturn(false);

        // Act & Assert
        MismatchPasswordOrEmail exception = assertThrows(MismatchPasswordOrEmail.class, () -> {
            authenticationService.authenticate(loginRequestDTO);
        });

        assertEquals("Credenciais inválidas", exception.getMessage());
    }

}
