package br.com.bitwise.bithealth.modules.medicos.services.mapper;

import br.com.bitwise.bithealth.modules.medicos.dto.DoctorRequest;
import br.com.bitwise.bithealth.modules.medicos.dto.DoctorResponse;
import br.com.bitwise.bithealth.modules.medicos.model.Doctor;
import br.com.bitwise.bithealth.modules.unidade_saude.model.UnidadeSaude;

import java.util.List;

public interface DoctorMapper {

    Doctor requestToModel(DoctorRequest dto);

    DoctorResponse modelToResponse(Doctor doctor, String tokenId);

    List<DoctorResponse> modelToResponseList(List<Doctor> doctors);
}
