package br.com.bitwise.bithealth.modules.servicos_saude.services.mapper;

import br.com.bitwise.bithealth.modules.servicos_saude.dto.ServicosSaudeRequest;
import br.com.bitwise.bithealth.modules.servicos_saude.dto.ServicosSaudeResponse;
import br.com.bitwise.bithealth.modules.servicos_saude.model.ServicosSaude;
import br.com.bitwise.bithealth.modules.unidade_saude.model.UnidadeSaude;
import br.com.bitwise.bithealth.modules.unidade_saude.services.UnidadeSaudeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperServicosSaude {

    private final UnidadeSaudeService unidadeServices;

    public ServicosSaude requestToModel(ServicosSaudeRequest servicosSaudeRequest) {
        UnidadeSaude unidadeSaude  = unidadeServices.getUnidadeSaudeById(servicosSaudeRequest.unidadeSaudeTokenId());

        return new ServicosSaude(
                servicosSaudeRequest.nome(),
                servicosSaudeRequest.descricao(),
                servicosSaudeRequest.horarioInicio(),
                servicosSaudeRequest.horarioFim(),
                unidadeSaude
        );
    }

    public ServicosSaudeResponse modelToResponse(ServicosSaude servicosSaudeSalvo, String tokenId) {
        return new ServicosSaudeResponse(
                tokenId,
                servicosSaudeSalvo.getNome(),
                servicosSaudeSalvo.getDescricao(),
                servicosSaudeSalvo.getHorarioInicio(),
                servicosSaudeSalvo.getHorarioFim()
        );
    }
}
