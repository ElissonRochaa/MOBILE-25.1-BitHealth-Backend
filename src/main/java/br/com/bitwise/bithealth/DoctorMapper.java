package br.com.bitwise.bithealth;

import br.com.bitwise.bithealth.modules.medicos.dto.DoctorRequest;
import br.com.bitwise.bithealth.modules.medicos.dto.DoctorResponse;
import br.com.bitwise.bithealth.modules.medicos.model.Doctor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DoctorMapper {

    Doctor requestToModel(DoctorRequest doctorRequest);

    DoctorResponse modelToResponse(Doctor doctor, String tokenId);

    List<DoctorResponse> modelToResponseList(List<Doctor> doctors);
}
