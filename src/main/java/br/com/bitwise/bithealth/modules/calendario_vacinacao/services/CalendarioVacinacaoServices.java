package br.com.bitwise.bithealth.modules.calendario_vacinacao.services;

import br.com.bitwise.bithealth.modules.calendario_vacinacao.dto.CalendarioRequest;
import br.com.bitwise.bithealth.modules.calendario_vacinacao.dto.CalendarioResponse;
import br.com.bitwise.bithealth.modules.calendario_vacinacao.model.CalendarioVacinacao;
import br.com.bitwise.bithealth.modules.calendario_vacinacao.repository.CalendarioVacinacaoRepository;
import br.com.bitwise.bithealth.modules.calendario_vacinacao.services.mapper.MapperCalendarioVacinacao;
import br.com.bitwise.bithealth.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CalendarioVacinacaoServices {

    private final CalendarioVacinacaoRepository calendarioVacinacaoRepository;
    private final MapperCalendarioVacinacao mapperCalendarioVacinacao;
    private final TokenService tokenService;

    public CalendarioResponse createCalendarioVacinacao(CalendarioRequest calendarioRequest) {
        CalendarioVacinacao calendario = mapperCalendarioVacinacao.requestToModel(calendarioRequest);
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
                .collect(java.util.stream.Collectors.toList());
    }

    public Void deleteCalendarioVacinacao(String tokenId) {
        String id = tokenService.decodeToken(tokenId);
        calendarioVacinacaoRepository.deleteById(UUID.fromString(id));
        return null;
    }
}
