package br.com.bitwise.bithealth.modules.user.controller;

import br.com.bitwise.bithealth.modules.user.dto.LoginRequestDTO;
import br.com.bitwise.bithealth.modules.user.dto.LoginResponseDTO;
import br.com.bitwise.bithealth.modules.user.exceptions.MismatchPasswordOrEmail;
import br.com.bitwise.bithealth.modules.user.service.AuthenticationService;
import br.com.bitwise.bithealth.modules.user.service.impl.AuthenticationServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        try {
            LoginResponseDTO response = authenticationService.authenticate(loginRequest);
            return ResponseEntity.ok(response);
        } catch (MismatchPasswordOrEmail e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
