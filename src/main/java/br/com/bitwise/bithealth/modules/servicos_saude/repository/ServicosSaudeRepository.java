package br.com.bitwise.bithealth.modules.servicos_saude.repository;

import br.com.bitwise.bithealth.modules.servicos_saude.model.ServicosSaude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ServicosSaudeRepository extends JpaRepository<ServicosSaude, UUID> {
    void deleteByUnidadeSaudeId(UUID id);

    List<ServicosSaude> findByUnidadeSaudeId(UUID id);
}
