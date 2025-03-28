package br.com.bitwise.bithealth.modules.unidadeSaude.services.mapper;

import br.com.bitwise.bithealth.modules.unidadeSaude.dto.UnidadeSaudeRequest;
import br.com.bitwise.bithealth.modules.unidadeSaude.dto.UnidadeSaudeResponse;
import br.com.bitwise.bithealth.modules.unidadeSaude.model.ENUMS.TipoUnidade;
import br.com.bitwise.bithealth.modules.unidadeSaude.model.UnidadeSaude;
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
