package br.com.bitwise.bithealth.modules.unidade_saude.repository;

import br.com.bitwise.bithealth.modules.unidade_saude.model.UnidadeSaude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UnidadeSaudeRepository extends JpaRepository<UnidadeSaude, UUID> {
    UnidadeSaude getByNome(String nome);

    @Query("SELECT u.nome FROM Medicamento m JOIN m.unidadeSaude u WHERE m.id = :id")
    String findNomeUnidadeByMedicamentoId(@Param("id") UUID medicamentoId);
}
