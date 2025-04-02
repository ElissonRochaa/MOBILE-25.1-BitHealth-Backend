package br.com.bitwise.bithealth.modules.doctors.repository;

import br.com.bitwise.bithealth.modules.doctors.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, UUID> {

    Optional<Doctor> findByCrm(String crm);

    boolean existsByCrm(String crm);
}