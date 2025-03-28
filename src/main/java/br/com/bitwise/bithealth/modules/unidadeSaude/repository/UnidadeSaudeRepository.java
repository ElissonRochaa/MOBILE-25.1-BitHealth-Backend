package br.com.bitwise.bithealth.modules.unidadeSaude.repository;

import br.com.bitwise.bithealth.modules.unidadeSaude.model.UnidadeSaude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeSaudeRepository extends JpaRepository<UnidadeSaude, Long> {
}
