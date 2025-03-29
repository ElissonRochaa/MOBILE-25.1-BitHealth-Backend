package br.com.bitwise.bithealth.modules.calendario_vacinacao.controllers;

import br.com.bitwise.bithealth.modules.calendario_vacinacao.dto.CalendarioRequest;
import br.com.bitwise.bithealth.modules.calendario_vacinacao.dto.CalendarioResponse;
import br.com.bitwise.bithealth.modules.calendario_vacinacao.services.CalendarioVacinacaoServices;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/calendario-vacinacao")
@RequiredArgsConstructor
@Tag(name = "Calendário de Vacinação")
public class CalendarioVacinacaoController {

    private final CalendarioVacinacaoServices calendarioVacinacaoServices;

    @GetMapping("/")
    @SecurityRequirement(name = "JWTAuth")
    @PreAuthorize("hasRole('ADMINISTRADOR') or hasRole('CIDADAO')")
    public ResponseEntity<List<CalendarioResponse>> getAllCalendarioVacinacao() {
        List<CalendarioResponse> calendario = calendarioVacinacaoServices.getAllCalendarioVacinacao();
        return ResponseEntity.ok().body(calendario);
    }

    @PostMapping("/")
    @SecurityRequirement(name = "JWTAuth")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<CalendarioResponse> createCalendarioVacinacao(@RequestBody @Valid CalendarioRequest calendarioRequest) {
        CalendarioResponse calendario = calendarioVacinacaoServices.createCalendarioVacinacao(calendarioRequest);
        URI uri = URI.create("/calendario/" + calendario.tokenId());
        return ResponseEntity.created(uri).body(calendario);
    }

    @DeleteMapping("/{tokenId}")
    @SecurityRequirement(name = "JWTAuth")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Void> deleteCalendarioVacinacao(@PathVariable String tokenId) {
        calendarioVacinacaoServices.deleteCalendarioVacinacao(tokenId);
        return ResponseEntity.ok().build();
    }
}
