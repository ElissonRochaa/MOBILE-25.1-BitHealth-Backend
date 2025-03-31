package br.com.bitwise.bithealth.modules.calendario_vacinacao.services.mapper;

import br.com.bitwise.bithealth.modules.calendario_vacinacao.dto.CalendarioRequest;
import br.com.bitwise.bithealth.modules.calendario_vacinacao.dto.CalendarioResponse;
import br.com.bitwise.bithealth.modules.calendario_vacinacao.model.CalendarioVacinacao;

public interface MapperCalendarioVacinacao {

    CalendarioVacinacao requestToModel(CalendarioRequest calendarioRequest);
    CalendarioResponse modelToResponse(CalendarioVacinacao calendario, String tokenId);
}
