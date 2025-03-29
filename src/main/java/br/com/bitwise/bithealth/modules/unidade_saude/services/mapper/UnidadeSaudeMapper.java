package br.com.bitwise.bithealth.modules.unidade_saude.services.mapper;

import br.com.bitwise.bithealth.modules.medicamentos.dto.MedicamentoResponse;
import br.com.bitwise.bithealth.modules.medicamentos.model.Medicamento;
import br.com.bitwise.bithealth.modules.medicamentos.repository.MedicamentosRepository;
import br.com.bitwise.bithealth.modules.servicos_saude.dto.ServicosSaudeResponse;
import br.com.bitwise.bithealth.modules.servicos_saude.model.ServicosSaude;
import br.com.bitwise.bithealth.modules.servicos_saude.repository.ServicosSaudeRepository;
import br.com.bitwise.bithealth.modules.unidade_saude.dto.UnidadeSaudeRequest;
import br.com.bitwise.bithealth.modules.unidade_saude.dto.UnidadeSaudeResponse;
import br.com.bitwise.bithealth.modules.unidade_saude.model.ENUMS.TipoUnidade;
import br.com.bitwise.bithealth.modules.unidade_saude.model.UnidadeSaude;
import br.com.bitwise.bithealth.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UnidadeSaudeMapper {

    private final ServicosSaudeRepository servicosSaudeRepository;
    private final MedicamentosRepository medicamentosRepository;
    private final TokenService tokenService;

    public UnidadeSaude requestToModel(UnidadeSaudeRequest unidadeSaudeRequest) {
        return new UnidadeSaude(
            unidadeSaudeRequest.nome(),
            TipoUnidade.fromString(unidadeSaudeRequest.tipo()),
            unidadeSaudeRequest.horarioInicioAtendimento(),
            unidadeSaudeRequest.horarioFimAtendimento()
        );
    }

    public UnidadeSaudeResponse modelToResponse(UnidadeSaude unidadeSaude, String tokenId) {
        List<ServicosSaude> servicosSaudeList = servicosSaudeRepository.findByUnidadeSaudeId(unidadeSaude.getId());
        List<Medicamento> medicamentoList = medicamentosRepository.findByUnidadeSaudeId(unidadeSaude.getId());

        List<ServicosSaudeResponse> servicosSaudeResponseList = servicosSaudeList.stream()
            .map(servicosSaude -> new ServicosSaudeResponse(
                tokenService.generateTokenId(servicosSaude.getId().toString()),
                servicosSaude.getNome(),
                servicosSaude.getDescricao(),
                servicosSaude.getHorarioInicio(),
                servicosSaude.getHorarioFim()
            ))
            .collect(Collectors.toList());

        List<MedicamentoResponse> medicamentoResponseList = medicamentoList.stream()
            .map(medicamento -> new MedicamentoResponse(
                tokenService.generateTokenId(medicamento.getId().toString()),
                medicamento.getNome(),
                medicamento.getDescricao(),
                medicamento.getQuantidade(),
                medicamento.getTipoMedicamento().toString()
            ))
            .collect(Collectors.toList());

        return new UnidadeSaudeResponse(
            tokenId,
            unidadeSaude.getNome(),
            unidadeSaude.getTipoUnidade().toString(),
            unidadeSaude.getHorarioInicioAtendimento(),
            unidadeSaude.getHorarioFimAtendimento(),
            servicosSaudeResponseList,
            medicamentoResponseList
        );
    }
}
