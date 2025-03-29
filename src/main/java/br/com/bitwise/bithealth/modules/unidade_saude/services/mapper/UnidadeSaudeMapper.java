package br.com.bitwise.bithealth.modules.unidade_saude.services.mapper;

import br.com.bitwise.bithealth.modules.unidade_saude.dto.UnidadeSaudeRequest;
import br.com.bitwise.bithealth.modules.unidade_saude.dto.UnidadeSaudeResponse;
import br.com.bitwise.bithealth.modules.unidade_saude.model.ENUMS.TipoUnidade;
import br.com.bitwise.bithealth.modules.unidade_saude.model.UnidadeSaude;
import org.springframework.stereotype.Component;

@Component
public class UnidadeSaudeMapper {

    public UnidadeSaude requestToModel(UnidadeSaudeRequest unidadeSaudeRequest) {
        return new UnidadeSaude(
            unidadeSaudeRequest.nome(),
            TipoUnidade.fromString(unidadeSaudeRequest.tipo()),
            unidadeSaudeRequest.horarioInicioAtendimento(),
            unidadeSaudeRequest.horarioFimAtendimento()
        );
    }

    public UnidadeSaudeResponse modelToResponse(UnidadeSaude unidadeSaude, String tokenId) {
        return new UnidadeSaudeResponse(
            tokenId,
            unidadeSaude.getNome(),
            unidadeSaude.getTipoUnidade().toString(),
            unidadeSaude.getHorarioInicioAtendimento(),
            unidadeSaude.getHorarioFimAtendimento()
        );
    }
}
