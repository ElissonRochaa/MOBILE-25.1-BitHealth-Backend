package br.com.bitwise.bithealth.modules.unidade_saude.dto;

import br.com.bitwise.bithealth.modules.medicamentos.dto.MedicamentoResponse;
import br.com.bitwise.bithealth.modules.servicos_saude.dto.ServicosSaudeResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record UnidadeSaudeResponse(
        @JsonProperty(namespace = "token_Id")
        String tokenId,
        String nome,
        String tipo,
        @JsonProperty(namespace = "horario_inicio_atendimento")
        String horarioInicioAtendimento,
        @JsonProperty(namespace = "horario_fim_atendimento")
        String horarioFimAtendimento,
        List<ServicosSaudeResponse> listServicosSaude,
        List<MedicamentoResponse> listMedicamentos
) {
}
