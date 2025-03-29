package br.com.bitwise.bithealth.modules.user;

import br.com.bitwise.bithealth.modules.user.controller.RegistroController;
import br.com.bitwise.bithealth.modules.user.dto.RegistroUsuarioDTO;
import br.com.bitwise.bithealth.modules.user.dto.UsuarioDTO;
import br.com.bitwise.bithealth.modules.user.endereco.dto.EnderecoDTO;
import br.com.bitwise.bithealth.modules.user.exceptions.CPFAlreadyExistsException;
import br.com.bitwise.bithealth.modules.user.exceptions.EmailAlreadyExistsException;
import br.com.bitwise.bithealth.modules.user.model.ENUM.TipoUsuario;
import br.com.bitwise.bithealth.modules.user.service.RegistroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RegistroControllerTest {
    @Mock
    private RegistroService registroService;

    @InjectMocks
    private RegistroController registroController;

    private RegistroUsuarioDTO validRegistroDTO;
    private UsuarioDTO usuarioDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        EnderecoDTO endereco1 = new EnderecoDTO(
                "Rua das Flores",
                "123",
                "Apto 101",
                "Jardim Primavera",
                "São Paulo",
                "SP",
                "01234-567"
        );

        // Criando um DTO válido para testes
        validRegistroDTO = new RegistroUsuarioDTO(
                "Teste Usuario",
                "teste@email.com",
                "123.456.789-00",
                "senha123",
                "11999999999",
                TipoUsuario.CIDADAO,
                "99999999",
                endereco1
        );

        usuarioDTO = new UsuarioDTO(
                UUID.randomUUID(),
                "Teste Usuario",
                "teste@email.com",
                "123.456.789-00",
                "string@email.com",
                "teste",
                TipoUsuario.CIDADAO,
                "99999999",
                true,
                LocalDateTime.now(),
                endereco1
        );
    }

    @Test
    @DisplayName("Deve retornar status 201 quando o registro for bem-sucedido")
    void registrarUsuarioSucesso() {
        // Arrange
        when(registroService.registrarNovoUsuario(any(RegistroUsuarioDTO.class))).thenReturn(usuarioDTO);

        // Act
        ResponseEntity<?> response = registroController.registrarUsuario(validRegistroDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Usuário registrado", response.getBody());
        verify(registroService, times(1)).registrarNovoUsuario(validRegistroDTO);
    }

    @Test
    @DisplayName("Deve retornar status 409 quando o email já existir")
    void registrarUsuarioEmailExistente() {
        // Arrange
        when(registroService.registrarNovoUsuario(any(RegistroUsuarioDTO.class)))
                .thenThrow(new EmailAlreadyExistsException("Email já cadastrado"));

        // Act
        ResponseEntity<?> response = registroController.registrarUsuario(validRegistroDTO);

        // Assert
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Email já cadastrado", response.getBody());
        verify(registroService, times(1)).registrarNovoUsuario(validRegistroDTO);
    }

    @Test
    @DisplayName("Deve retornar status 409 quando o CPF já existir")
    void registrarUsuarioCpfExistente() {
        // Arrange
        when(registroService.registrarNovoUsuario(any(RegistroUsuarioDTO.class)))
                .thenThrow(new CPFAlreadyExistsException("CPF já cadastrado"));

        // Act
        ResponseEntity<?> response = registroController.registrarUsuario(validRegistroDTO);

        // Assert
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("CPF já cadastrado", response.getBody());
        verify(registroService, times(1)).registrarNovoUsuario(validRegistroDTO);
    }

    @Test
    @DisplayName("Deve retornar status 409 para qualquer outra exceção de runtime")
    void registrarUsuarioOutraExcecao() {
        // Arrange
        when(registroService.registrarNovoUsuario(any(RegistroUsuarioDTO.class)))
                .thenThrow(new RuntimeException("Erro inesperado"));

        // Act
        ResponseEntity<?> response = registroController.registrarUsuario(validRegistroDTO);

        // Assert
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Erro inesperado", response.getBody());
        verify(registroService, times(1)).registrarNovoUsuario(validRegistroDTO);
    }
}
