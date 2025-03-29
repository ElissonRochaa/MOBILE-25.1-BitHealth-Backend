package br.com.bitwise.bithealth.modules.medicamentos.repository;

import br.com.bitwise.bithealth.modules.medicamentos.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MedicamentosRepository extends JpaRepository<Medicamento, UUID> {
    List<Medicamento> findByUnidadeSaudeId(UUID unidadeSaudeId);
    void deleteByUnidadeSaudeId(UUID id);

}
