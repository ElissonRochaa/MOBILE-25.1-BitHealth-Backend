package br.com.bitwise.bithealth.modules.unidadeSaude.controller;

import br.com.bitwise.bithealth.modules.unidadeSaude.dto.UnidadeSaudeRequest;
import br.com.bitwise.bithealth.modules.unidadeSaude.dto.UnidadeSaudeResponse;
import br.com.bitwise.bithealth.modules.unidadeSaude.services.UnidadeSaudeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/unidades-saude")
@Tag(name = "Unidades de Saúde")
public class UnidadeSaudeController {

    private UnidadeSaudeService unidadeSaudeService;

    public UnidadeSaudeController(UnidadeSaudeService unidadeSaudeService) {
        this.unidadeSaudeService = unidadeSaudeService;
    }

    @GetMapping("/")
    public ResponseEntity<List<UnidadeSaudeResponse>> getAllUnidades() {
        List<UnidadeSaudeResponse> unidades = unidadeSaudeService.getAllUnidadeSaude();
        return ResponseEntity.ok().body(unidades);
    }

    @PostMapping("/")
    public ResponseEntity<UnidadeSaudeResponse> createUnidade(@RequestBody @Valid UnidadeSaudeRequest unidadeRequest) {
        UnidadeSaudeResponse unidade = unidadeSaudeService.createUnidadeSaude(unidadeRequest);
        URI uri = URI.create("/unidades/" + unidade.tokenId());
        return ResponseEntity.created(uri).body(unidade);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUnidade(@PathVariable String tokenId) {
        unidadeSaudeService.deleteUnidadeSaude(tokenId);
        return ResponseEntity.ok().build();
    }
}
