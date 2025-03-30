package br.com.bitwise.bithealth.modules.unidade_saude.endereco.repository;

import br.com.bitwise.bithealth.modules.unidade_saude.endereco.model.EnderecoUnidades;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnderecoUnidadesRepository extends JpaRepository<EnderecoUnidades, UUID> {
    EnderecoUnidades findByUnidadeSaudeId(UUID unidadeSaudeId);
    void deleteByUnidadeSaudeId(UUID unidadeSaudeId);
}
