package br.com.bitwise.bithealth.modules.doctors.services.mapper;

import br.com.bitwise.bithealth.modules.doctors.dto.DoctorRequest;
import br.com.bitwise.bithealth.modules.doctors.dto.DoctorResponse;
import br.com.bitwise.bithealth.modules.doctors.model.Doctor;
import br.com.bitwise.bithealth.modules.unidade_saude.model.UnidadeSaude;
import br.com.bitwise.bithealth.modules.unidade_saude.services.UnidadeSaudeService;
import br.com.bitwise.bithealth.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DoctorMapperImpl implements DoctorMapper {

    private final TokenService tokenService;
    private final UnidadeSaudeService unidadeSaudeService;

    @Override
    public Doctor requestToModel(DoctorRequest doctorRequest) {

        UnidadeSaude unidadeSaude = unidadeSaudeService.getUnidadeSaudeById(doctorRequest.unidadeSaudeTokenId());

        return new Doctor(
                doctorRequest.nome(),
                doctorRequest.crm(),
                doctorRequest.especialidade(),
                unidadeSaude,
                doctorRequest.dataPlantao(),
                doctorRequest.horarioInicio(),
                doctorRequest.horarioFim(),
                doctorRequest.tipo()
        );
    }

    @Override
    public DoctorResponse modelToResponse(Doctor doctor, String tokenId) {
        return new DoctorResponse(
                tokenId,
                doctor.getNome(),
                doctor.getCrm(),
                doctor.getEspecialidade(),
                doctor.getUnidadeSaude().getId(),
                doctor.getDataPlantao(),
                doctor.getHorarioInicio(),
                doctor.getHorarioFim(),
                doctor.getTipo(),
                doctor.getCriadoEm()
        );
    }

    @Override
    public List<DoctorResponse> modelToResponseList(List<Doctor> doctors) {
        return doctors.stream()
                .map(doctor -> modelToResponse(doctor, tokenService.generateTokenId(doctor.getId().toString())))
                .collect(Collectors.toList());
    }
}
