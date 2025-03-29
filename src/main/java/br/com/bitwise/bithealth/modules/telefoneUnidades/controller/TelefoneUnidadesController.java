package br.com.bitwise.bithealth.modules.telefoneUnidades.controller;

import br.com.bitwise.bithealth.modules.telefoneUnidades.dto.TelefoneUnidadesRequestDTO;
import br.com.bitwise.bithealth.modules.telefoneUnidades.dto.TelefoneUnidadesResponseDTO;
import br.com.bitwise.bithealth.modules.telefoneUnidades.services.TelefoneUnidadesService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/telefones-unidades")
@RequiredArgsConstructor
@Tag(name = "Telefones das Unidades de Saúde")
public class TelefoneUnidadesController {

    private final TelefoneUnidadesService telefoneUnidadesService;

    @PostMapping
    @SecurityRequirement(name = "JWTAuth")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<TelefoneUnidadesResponseDTO> cadastrarTelefone(
            @RequestBody @Valid TelefoneUnidadesRequestDTO request) {

        TelefoneUnidadesResponseDTO telefone = telefoneUnidadesService.cadastrarTelefone(request);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(telefone.telefoneTokenId())
                .toUri();

        return ResponseEntity.created(uri).body(telefone);
    }

}