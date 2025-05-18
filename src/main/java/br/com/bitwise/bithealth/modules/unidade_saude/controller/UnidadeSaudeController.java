package br.com.bitwise.bithealth.modules.unidade_saude.controller;

import br.com.bitwise.bithealth.modules.unidade_saude.dto.MensagemResponse;
import br.com.bitwise.bithealth.modules.unidade_saude.dto.UnidadeSaudeRequest;
import br.com.bitwise.bithealth.modules.unidade_saude.dto.UnidadeSaudeResponse;
import br.com.bitwise.bithealth.modules.unidade_saude.services.UnidadeSaudeService;
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
@RequestMapping("/api/unidades-saude")
@RequiredArgsConstructor
@Tag(name = "Unidades de Saúde")
public class UnidadeSaudeController {

    private final UnidadeSaudeService unidadeSaudeService;

    @GetMapping("/")
    @SecurityRequirement(name = "JWTAuth")
    public ResponseEntity<List<UnidadeSaudeResponse>> getAllUnidades() {
        List<UnidadeSaudeResponse> unidades = unidadeSaudeService.getAllUnidadeSaude();
        return ResponseEntity.ok().body(unidades);
    }

    @PostMapping("/")
    @SecurityRequirement(name = "JWTAuth")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<UnidadeSaudeResponse> createUnidade(@RequestBody @Valid UnidadeSaudeRequest unidadeRequest) {
        UnidadeSaudeResponse unidade = unidadeSaudeService.createUnidadeSaude(unidadeRequest);
        URI uri = URI.create("/unidades/" + unidade.tokenId());
        return ResponseEntity.created(uri).body(unidade);
    }

    @DeleteMapping("/{tokenId}")
    @SecurityRequirement(name = "JWTAuth")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<MensagemResponse> deleteUnidade(@PathVariable String tokenId) {
        unidadeSaudeService.deleteUnidadeSaude(tokenId);
        return ResponseEntity.ok().body(new MensagemResponse("Unidade de Saúde deletada com sucesso"));
    }
}
