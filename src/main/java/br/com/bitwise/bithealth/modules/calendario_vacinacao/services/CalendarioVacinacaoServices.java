package br.com.bitwise.bithealth.modules.calendario_vacinacao.services;

import br.com.bitwise.bithealth.modules.calendario_vacinacao.dto.CalendarioRequest;
import br.com.bitwise.bithealth.modules.calendario_vacinacao.dto.CalendarioResponse;

import java.util.List;

public interface CalendarioVacinacaoServices {

    CalendarioResponse createCalendarioVacinacao(CalendarioRequest calendarioRequest);
    List<CalendarioResponse> getAllCalendarioVacinacao();
    void deleteCalendarioVacinacao(String tokenId);
    void validadeDataInicioDataFim(String dataInicio, String dataFim);
}
