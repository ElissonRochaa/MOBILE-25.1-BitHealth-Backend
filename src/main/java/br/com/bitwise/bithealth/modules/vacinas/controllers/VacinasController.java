package br.com.bitwise.bithealth.modules.vacinas.controllers;

import br.com.bitwise.bithealth.modules.vacinas.dto.VacinasRequest;
import br.com.bitwise.bithealth.modules.vacinas.dto.VacinasResponse;
import br.com.bitwise.bithealth.modules.vacinas.services.VacinasService;
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
@RequestMapping("api/vacinas")
@RequiredArgsConstructor
@Tag(name = "Vacinas")
public class VacinasController {

    private final VacinasService vacinasService;

    @GetMapping("/")
    @SecurityRequirement(name = "JWTAuth")
    public ResponseEntity<List<VacinasResponse>> getAllVacinas() {
        List<VacinasResponse> vacinas = vacinasService.getALlVacinas();
        return ResponseEntity.ok().body(vacinas);
    }

    @PostMapping
    @SecurityRequirement(name = "JWTAuth")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<VacinasResponse> createVacinas(@RequestBody @Valid VacinasRequest vacinasRequest) {

        VacinasResponse vacinas = vacinasService.createVacina(vacinasRequest);
        URI uri = URI.create("/vacinas/" + vacinas.tokenId());
        return ResponseEntity.created(uri).body(vacinas);

    }

    @DeleteMapping
    @SecurityRequirement(name = "JWTAuth")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Void> deleteVacinas(@PathVariable String tokenId){
        vacinasService.deleteVacina(tokenId);
        return ResponseEntity.noContent().build();
    }



}
