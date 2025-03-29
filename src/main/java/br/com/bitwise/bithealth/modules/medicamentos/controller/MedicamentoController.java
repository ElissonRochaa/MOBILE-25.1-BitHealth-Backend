package br.com.bitwise.bithealth.modules.medicamentos.controller;

import br.com.bitwise.bithealth.modules.medicamentos.dto.MedicamentoRequest;
import br.com.bitwise.bithealth.modules.medicamentos.dto.MedicamentoResponse;
import br.com.bitwise.bithealth.modules.medicamentos.dto.MensagemResponse;
import br.com.bitwise.bithealth.modules.medicamentos.services.MedicamentoServices;
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
@RequestMapping("/api/medicamentos")
@RequiredArgsConstructor
@Tag(name = "Medicamentos")
public class MedicamentoController {

    private final MedicamentoServices medicamentoServices;

    @PostMapping("/")
    @SecurityRequirement(name = "JWTAuth")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<MedicamentoResponse> createMedicamento(@RequestBody @Valid MedicamentoRequest medicamentoRequest) {
        MedicamentoResponse response = medicamentoServices.createMedicamento(medicamentoRequest);
        URI uri = URI.create("/medicamentos/" + response.tokenId());
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/")
    @SecurityRequirement(name = "JWTAuth")
    @PreAuthorize("hasRole('ADMINISTRADOR') or hasRole('CIDADAO')")
    public ResponseEntity<List<MedicamentoResponse>> getAllMedicamentos() {
        List<MedicamentoResponse> response = medicamentoServices.getAllMedicamentos();
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{tokenId}")
    @SecurityRequirement(name = "JWTAuth")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<MensagemResponse> deleteMedicamento(@PathVariable String tokenId) {
        medicamentoServices.deleteMedicamento(tokenId);
        return ResponseEntity.ok().body(new MensagemResponse("Medicamento deletado com sucesso"));
    }

    @PutMapping("/{tokenId}")
    @SecurityRequirement(name = "JWTAuth")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<MedicamentoResponse> updateMedicamento(@RequestBody @Valid MedicamentoResponse medicamentoResponse, @PathVariable String tokenId) {
        MedicamentoResponse response = medicamentoServices.updateMedicamento(medicamentoResponse, tokenId);
        return ResponseEntity.ok().body(response);
    }
}
