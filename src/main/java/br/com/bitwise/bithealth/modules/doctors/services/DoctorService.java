package br.com.bitwise.bithealth.modules.doctors.services;

import br.com.bitwise.bithealth.modules.doctors.dto.DoctorRequest;
import br.com.bitwise.bithealth.modules.doctors.dto.DoctorResponse;
import br.com.bitwise.bithealth.modules.doctors.model.Doctor;

import java.util.List;

public interface DoctorService {
    DoctorResponse createDoctor(DoctorRequest doctorRequestDTO);
    Doctor getDoctorById(String tokenId);
    List<DoctorResponse> getAllDoctors();
    void deleteDoctor(String tokenId);
}