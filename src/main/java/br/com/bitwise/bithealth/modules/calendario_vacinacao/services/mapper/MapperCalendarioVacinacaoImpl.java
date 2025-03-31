package br.com.bitwise.bithealth.modules.calendario_vacinacao.services.mapper;

import br.com.bitwise.bithealth.modules.calendario_vacinacao.dto.CalendarioRequest;
import br.com.bitwise.bithealth.modules.calendario_vacinacao.dto.CalendarioResponse;
import br.com.bitwise.bithealth.modules.calendario_vacinacao.model.CalendarioVacinacao;
import br.com.bitwise.bithealth.modules.calendario_vacinacao.model.ENUMS.StatusVacinacao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperCalendarioVacinacaoImpl implements MapperCalendarioVacinacao {

    public CalendarioVacinacao requestToModel(CalendarioRequest calendarioRequest) {
        return new CalendarioVacinacao(
            calendarioRequest.vacina(),
            calendarioRequest.idadeMinima(),
            calendarioRequest.idadeMaxima(),
            calendarioRequest.descricao(),
            calendarioRequest.dataInicio(),
            calendarioRequest.dataFim(),
            StatusVacinacao.fromString(calendarioRequest.status())
        );
    }

    public CalendarioResponse modelToResponse(CalendarioVacinacao calendario, String tokenId) {
        return new CalendarioResponse(
            tokenId,
            calendario.getVacina(),
            calendario.getIdadeMinima(),
            calendario.getIdadeMaxima(),
            calendario.getDescricao(),
            calendario.getDataInicio(),
            calendario.getDataFim(),
            calendario.getStatusVacinacao().toString()
        );
    }
}
