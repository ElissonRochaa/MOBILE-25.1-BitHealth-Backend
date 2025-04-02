package br.com.bitwise.bithealth.modules.medicos.services;

import br.com.bitwise.bithealth.modules.medicos.dto.DoctorRequest;
import br.com.bitwise.bithealth.modules.medicos.dto.DoctorResponse;
import br.com.bitwise.bithealth.modules.medicos.model.Doctor;

import java.util.List;

public interface DoctorService {
    DoctorResponse createDoctor(DoctorRequest doctorRequestDTO);
    Doctor getDoctorById(String tokenId);
    List<DoctorResponse> getAllDoctors();
    void deleteDoctor(String tokenId);
}