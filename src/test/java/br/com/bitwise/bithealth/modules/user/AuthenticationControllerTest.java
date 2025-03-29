package br.com.bitwise.bithealth.modules.user;

import br.com.bitwise.bithealth.modules.user.controller.AuthenticationController;
import br.com.bitwise.bithealth.modules.user.dto.LoginRequestDTO;
import br.com.bitwise.bithealth.modules.user.dto.LoginResponseDTO;
import br.com.bitwise.bithealth.modules.user.exceptions.MismatchPasswordOrEmail;
import br.com.bitwise.bithealth.modules.user.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class AuthenticationControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve retornar status OK quando login for bem-sucedido")
    void loginSuccess() {
        // Arrange
        LoginRequestDTO loginRequest = new LoginRequestDTO("user@example.com", "password123");
        LoginResponseDTO expectedResponse = new LoginResponseDTO("User", "user@example.com", "jwt-token");

        when(authenticationService.authenticate(loginRequest)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<LoginResponseDTO> responseEntity = authenticationController.login(loginRequest);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
        verify(authenticationService, times(1)).authenticate(loginRequest);
    }

    @Test
    @DisplayName("Deve passar o DTO corretamente para o service")
    void loginPassesCorrectDTOToService() {
        // Arrange
        LoginRequestDTO loginRequest = new LoginRequestDTO("admin@example.com", "admin123");
        LoginResponseDTO mockResponse = new LoginResponseDTO("Admin", "admin@example.com", "admin-token");

        when(authenticationService.authenticate(loginRequest)).thenReturn(mockResponse);

        // Act
        authenticationController.login(loginRequest);

        // Assert
        verify(authenticationService).authenticate(loginRequest);
    }

    @Test
    @DisplayName("Deve retornar UNAUTHORIZED quando as credenciais forem inválidas")
    void loginUnauthorizedOnInvalidCredentials() {
        // Arrange
        LoginRequestDTO loginRequest = new LoginRequestDTO("wrong@example.com", "wrongpass");

        when(authenticationService.authenticate(loginRequest))
                .thenThrow(new MismatchPasswordOrEmail("Credenciais inválidas"));

        // Act
        ResponseEntity<LoginResponseDTO> responseEntity = authenticationController.login(loginRequest);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }
}
