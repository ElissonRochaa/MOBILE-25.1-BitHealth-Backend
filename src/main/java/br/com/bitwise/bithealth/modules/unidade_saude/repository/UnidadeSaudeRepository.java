package br.com.bitwise.bithealth.modules.unidade_saude.repository;

import br.com.bitwise.bithealth.modules.unidade_saude.model.UnidadeSaude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UnidadeSaudeRepository extends JpaRepository<UnidadeSaude, UUID> {
    UnidadeSaude getByNome(String nome);
}
