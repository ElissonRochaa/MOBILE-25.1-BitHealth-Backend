package br.com.bitwise.bithealth.modules.calendario_vacinacao.services;

import br.com.bitwise.bithealth.modules.calendario_vacinacao.dto.CalendarioRequest;
import br.com.bitwise.bithealth.modules.calendario_vacinacao.dto.CalendarioResponse;
import br.com.bitwise.bithealth.modules.calendario_vacinacao.exceptions.DateInitAfterDateEndException;
import br.com.bitwise.bithealth.modules.calendario_vacinacao.model.CalendarioVacinacao;
import br.com.bitwise.bithealth.modules.calendario_vacinacao.repository.CalendarioVacinacaoRepository;
import br.com.bitwise.bithealth.modules.calendario_vacinacao.services.mapper.MapperCalendarioVacinacao;
import br.com.bitwise.bithealth.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalendarioVacinacaoServicesImpl implements CalendarioVacinacaoServices {

    private final CalendarioVacinacaoRepository calendarioVacinacaoRepository;
    private final MapperCalendarioVacinacao mapperCalendarioVacinacao;
    private final TokenService tokenService;

    public CalendarioResponse createCalendarioVacinacao(CalendarioRequest calendarioRequest) {
        CalendarioVacinacao calendario = mapperCalendarioVacinacao.requestToModel(calendarioRequest);
        validadeDataInicioDataFim(calendario.getDataInicio(), calendario.getDataFim());
        calendario = calendarioVacinacaoRepository.save(calendario);
        String tokenId = tokenService.generateTokenId(calendario.getId().toString());
        return mapperCalendarioVacinacao.modelToResponse(calendario, tokenId);
    }

    public List<CalendarioResponse> getAllCalendarioVacinacao() {
        List<CalendarioVacinacao> calendarioVacinacao = calendarioVacinacaoRepository.findAll();
        return calendarioVacinacao.stream()
                .map(x -> mapperCalendarioVacinacao.modelToResponse(
                        x,
                        tokenService.generateTokenId(x.getId().toString())
                ))
                .collect(Collectors.toList());
    }

    public void deleteCalendarioVacinacao(String tokenId) {
        String id = tokenService.decodeToken(tokenId);
        calendarioVacinacaoRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public void validadeDataInicioDataFim(String dataInicio, String dataFim) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date inicio = dateFormat.parse(dataInicio);
            Date fim = dateFormat.parse(dataFim);
            boolean validadeData = inicio.before(fim);
            if (!validadeData) {
                throw new DateInitAfterDateEndException("Data de início não pode ser maior que a data de fim");
            }

        } catch (Exception e) {
            throw new RuntimeException("Data de início não pode ser maior que a data de fim");
        }

    }
}
