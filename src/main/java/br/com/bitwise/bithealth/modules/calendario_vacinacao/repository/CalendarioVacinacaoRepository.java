package br.com.bitwise.bithealth.modules.calendario_vacinacao.repository;

import br.com.bitwise.bithealth.modules.calendario_vacinacao.model.CalendarioVacinacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CalendarioVacinacaoRepository extends JpaRepository<CalendarioVacinacao, UUID> {
}
