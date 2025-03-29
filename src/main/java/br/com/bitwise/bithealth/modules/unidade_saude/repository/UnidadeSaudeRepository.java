package br.com.bitwise.bithealth.modules.unidade_saude.repository;

import br.com.bitwise.bithealth.modules.unidade_saude.model.UnidadeSaude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeSaudeRepository extends JpaRepository<UnidadeSaude, Long> {
}
