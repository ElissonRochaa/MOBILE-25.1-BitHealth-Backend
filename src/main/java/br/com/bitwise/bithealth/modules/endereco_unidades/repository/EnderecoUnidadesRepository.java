package br.com.bitwise.bithealth.modules.endereco_unidades.repository;

import br.com.bitwise.bithealth.modules.endereco_unidades.model.EnderecoUnidades;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EnderecoUnidadesRepository extends JpaRepository<EnderecoUnidades, UUID> {
    List<EnderecoUnidades> findByUnidadeSaudeId(UUID unidadeSaudeId);
    void deleteByUnidadeSaudeId(UUID unidadeSaudeId);
}
