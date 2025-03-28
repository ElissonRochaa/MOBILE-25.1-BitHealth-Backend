package br.com.bitwise.bithealth.modules.user.controller;

import br.com.bitwise.bithealth.modules.user.dto.RegistroUsuarioDTO;
import br.com.bitwise.bithealth.modules.user.dto.UsuarioDTO;
import br.com.bitwise.bithealth.modules.user.model.Usuario;
import br.com.bitwise.bithealth.modules.user.service.RegistroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RegistroController {
    private  final RegistroService registroService;

    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@Valid @RequestBody RegistroUsuarioDTO registroDTO) {
        try {
            registroService.registrarNovoUsuario(registroDTO);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Usuário registrado");
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }
}
