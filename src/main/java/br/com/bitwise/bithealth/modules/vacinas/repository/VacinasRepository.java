package br.com.bitwise.bithealth.modules.vacinas.repository;

import br.com.bitwise.bithealth.modules.vacinas.model.Vacinas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VacinasRepository extends JpaRepository<Vacinas, UUID> {
}
