package br.com.bitwise.bithealth.modules.telefoneUnidades.repository;

import br.com.bitwise.bithealth.modules.telefoneUnidades.model.TelefoneUnidades;
import br.com.bitwise.bithealth.modules.telefoneUnidades.model.ENUM.TipoTelefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TelefoneUnidadesRepository extends JpaRepository<TelefoneUnidades, UUID> {


    List<TelefoneUnidades> findByUnidadeSaudeId(UUID unidadeId);

    List<TelefoneUnidades> findByUnidadeSaudeIdAndAtivoTrue(UUID unidadeId);

    List<TelefoneUnidades> findByUnidadeSaudeIdAndTipo(UUID unidadeId, TipoTelefone tipo);

    List<TelefoneUnidades> findByUnidadeSaudeIdAndTipoAndAtivoTrue(UUID unidadeId, TipoTelefone tipo);

    boolean existsByNumero(String numero);

    boolean existsByNumeroAndAtivoTrue(String numero);

    boolean existsByUnidadeSaudeIdAndNumero(UUID unidadeId, String numero);

    boolean existsByUnidadeSaudeIdAndNumeroAndAtivoTrue(UUID unidadeId, String numero);
}