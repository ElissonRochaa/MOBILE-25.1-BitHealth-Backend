package br.com.bitwise.bithealth.modules.medicos.services;

import br.com.bitwise.bithealth.modules.medicos.dto.DoctorRequest;
import br.com.bitwise.bithealth.modules.medicos.dto.DoctorResponse;
import br.com.bitwise.bithealth.modules.medicos.exceptions.DoctorNotFoundException;
import br.com.bitwise.bithealth.modules.medicos.services.mapper.DoctorMapper;
import br.com.bitwise.bithealth.modules.medicos.model.Doctor;
import br.com.bitwise.bithealth.modules.medicos.repository.DoctorRepository;
import br.com.bitwise.bithealth.modules.unidade_saude.repository.UnidadeSaudeRepository;
import br.com.bitwise.bithealth.security.TokenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final UnidadeSaudeRepository unidadeSaudeRepository;
    private final DoctorMapper doctorMapper;
    private final TokenService tokenService;

    @Override
    public DoctorResponse createDoctor(DoctorRequest doctorRequestDTO) {

        Doctor doctor = doctorMapper.requestToModel(doctorRequestDTO);

        doctor = doctorRepository.save(doctor);

        String tokenId = tokenService.generateTokenId(String.valueOf(doctor.getId()));

        return doctorMapper.modelToResponse(doctor, tokenId);
    }

    @Override
    public Doctor getDoctorById(String tokenId) {

        String id = tokenService.decodeToken(tokenId);

        return doctorRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new DoctorNotFoundException("Médico não encontrado"));
    }

    @Override
    public List<DoctorResponse> getAllDoctors() {

        return doctorRepository.findAll().stream()
                .map(doctor -> {
                    String tokenId = tokenService.generateTokenId(String.valueOf(doctor.getId()));
                    return doctorMapper.modelToResponse(doctor,tokenId);
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteDoctor(String tokenId) {

        String id = tokenService.decodeToken(tokenId);

        doctorRepository.deleteById(UUID.fromString(id));
    }
}