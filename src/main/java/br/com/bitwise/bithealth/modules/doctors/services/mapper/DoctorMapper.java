package br.com.bitwise.bithealth.modules.doctors.services.mapper;

import br.com.bitwise.bithealth.modules.doctors.dto.DoctorRequest;
import br.com.bitwise.bithealth.modules.doctors.dto.DoctorResponse;
import br.com.bitwise.bithealth.modules.doctors.model.Doctor;

import java.util.List;

public interface DoctorMapper {

    Doctor requestToModel(DoctorRequest dto);

    DoctorResponse modelToResponse(Doctor doctor, String tokenId);

    List<DoctorResponse> modelToResponseList(List<Doctor> doctors);
}
