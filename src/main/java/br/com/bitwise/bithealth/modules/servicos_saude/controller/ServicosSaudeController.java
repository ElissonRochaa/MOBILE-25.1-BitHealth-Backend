package br.com.bitwise.bithealth.modules.servicos_saude.controller;

import br.com.bitwise.bithealth.modules.servicos_saude.dto.MensagemResponse;
import br.com.bitwise.bithealth.modules.servicos_saude.dto.ServicosSaudeRequest;
import br.com.bitwise.bithealth.modules.servicos_saude.dto.ServicosSaudeResponse;
import br.com.bitwise.bithealth.modules.servicos_saude.services.ServicosSaudeServices;
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
@RequestMapping("/api/servicos-saude")
@RequiredArgsConstructor
@Tag(name = "Serviços de Saúde")
public class ServicosSaudeController {

    private final ServicosSaudeServices servicosSaudeServices;

    @PostMapping("/")
    @SecurityRequirement(name = "JWTAuth")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<ServicosSaudeResponse> createServicosSaude(@RequestBody @Valid ServicosSaudeRequest servicosSaudeRequest) {
        ServicosSaudeResponse response = servicosSaudeServices.createServicosSaude(servicosSaudeRequest);
        URI uri = URI.create("/servicos-saude/" + response.id());
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/")
    @SecurityRequirement(name = "JWTAuth")
    public ResponseEntity<List<ServicosSaudeResponse>> getAllServicosSaude() {
        List<ServicosSaudeResponse> response = servicosSaudeServices.getAllServicosSaude();
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{tokenId}")
    @SecurityRequirement(name = "JWTAuth")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<MensagemResponse> deleteServicosSaude(@PathVariable String tokenId) {
        servicosSaudeServices.deleteServicosSaude(tokenId);
        return ResponseEntity.ok().body(new MensagemResponse("Serviço de saúde deletado com sucesso"));
    }
}
