package br.com.bitwise.bithealth.modules.doctors.controller;

import br.com.bitwise.bithealth.modules.doctors.dto.DoctorRequest;
import br.com.bitwise.bithealth.modules.doctors.dto.DoctorResponse;
import br.com.bitwise.bithealth.modules.doctors.services.DoctorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/doctors")
@RequiredArgsConstructor
@Tag(name = "Médicos")
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("/")
    @SecurityRequirement(name = "JWTAuth")
    public ResponseEntity<List<DoctorResponse>> getAllDoctors() {
        List<DoctorResponse> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok().body(doctors);
    }

    @PostMapping("/")
    @SecurityRequirement(name = "JWTAuth")
    public ResponseEntity<DoctorResponse> createDoctor(@RequestBody @Valid DoctorRequest doctorRequest) {

        DoctorResponse doctor = doctorService.createDoctor(doctorRequest);
        URI uri = URI.create("/doctors/" + doctor.tokenId());
        return ResponseEntity.created(uri).body(doctor);
    }

    @DeleteMapping("/{tokenId}")
    @SecurityRequirement(name = "JWTAuth")
    public ResponseEntity<String> deleteDoctor(@PathVariable String tokenId) {
        doctorService.deleteDoctor(tokenId);
        return ResponseEntity.ok().body("Médico deletado com sucesso");
    }
}
