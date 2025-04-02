package br.com.bitwise.bithealth.modules.user.service;

import br.com.bitwise.bithealth.modules.user.dto.LoginRequestDTO;
import br.com.bitwise.bithealth.modules.user.dto.LoginResponseDTO;
import org.springframework.stereotype.Component;

@Component
public interface AuthenticationService {
    LoginResponseDTO authenticate(LoginRequestDTO loginRequest);
}
