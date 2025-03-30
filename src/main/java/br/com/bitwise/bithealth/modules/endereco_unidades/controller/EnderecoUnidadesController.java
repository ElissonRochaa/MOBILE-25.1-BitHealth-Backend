package br.com.bitwise.bithealth.modules.endereco_unidades.controller;

import br.com.bitwise.bithealth.modules.endereco_unidades.dto.EnderecoUnidadesRequestDTO;
import br.com.bitwise.bithealth.modules.endereco_unidades.dto.EnderecoUnidadesResponseDTO;
import br.com.bitwise.bithealth.modules.endereco_unidades.dto.MensagemResponse;
import br.com.bitwise.bithealth.modules.endereco_unidades.services.EnderecoUnidadesService;
import br.com.bitwise.bithealth.modules.endereco_unidades.services.EnderecoUnidadesServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/endereco-unidades")
@RequiredArgsConstructor
@Tag(name = "Endereços de Unidades de Saúde")
public class EnderecoUnidadesController {

    private final EnderecoUnidadesService enderecoUnidadesService;

    @PostMapping("/")
    @SecurityRequirement(name = "JWTAuth")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<EnderecoUnidadesResponseDTO> createEnderecoUnidade(@RequestBody @Valid EnderecoUnidadesRequestDTO enderecoUnidadeRequest) {
        EnderecoUnidadesResponseDTO response = enderecoUnidadesService.createEnderecoUnidade(enderecoUnidadeRequest);
        URI uri = URI.create("/endereco-unidades/" + response.enderecoUnidadeId());
        return ResponseEntity.created(uri).body(response);
    }


    @DeleteMapping("/{tokenId}")
    @SecurityRequirement(name = "JWTAuth")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<MensagemResponse> deleteEnderecoUnidade(@PathVariable String tokenId) {
        enderecoUnidadesService.deleteEnderecoUnidade(tokenId);
        return ResponseEntity.ok().body(new MensagemResponse("Endereço de unidade deletado com sucesso"));
    }

//    @PutMapping("/{tokenId}")
//    @SecurityRequirement(name = "JWTAuth")
//    @PreAuthorize("hasRole('ADMINISTRADOR')")
//    public ResponseEntity<EnderecoUnidadesResponseDTO> updateEnderecoUnidade(@RequestBody @Valid EnderecoUnidadesRequestDTO enderecoUnidadeRequest, @PathVariable String tokenId) {
//        EnderecoUnidadesResponseDTO response = enderecoUnidadesService.updateEnderecoUnidade(enderecoUnidadeRequest, tokenId);
//        return ResponseEntity.ok().body(response);
//    }
}
