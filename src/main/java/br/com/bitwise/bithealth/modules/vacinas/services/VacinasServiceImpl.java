package br.com.bitwise.bithealth.modules.vacinas.services;

import br.com.bitwise.bithealth.modules.calendario_vacinacao.model.CalendarioVacinacao;
import br.com.bitwise.bithealth.modules.calendario_vacinacao.repository.CalendarioVacinacaoRepository;
import br.com.bitwise.bithealth.modules.vacinas.dto.VacinasRequest;
import br.com.bitwise.bithealth.modules.vacinas.dto.VacinasResponse;
import br.com.bitwise.bithealth.modules.vacinas.model.Vacinas;
import br.com.bitwise.bithealth.modules.vacinas.repository.VacinasRepository;
import br.com.bitwise.bithealth.modules.vacinas.services.mapper.MapperVacinas;
import br.com.bitwise.bithealth.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VacinasServiceImpl implements VacinasService {

    private final VacinasRepository vacinasRepository;
    private final MapperVacinas mapperVacinas;
    private final TokenService tokenService;
    private final CalendarioVacinacaoRepository calendarioVacinacaoRepository;

    @Override
    public VacinasResponse createVacina(VacinasRequest vacinasRequest) {

        Vacinas vacinas = mapperVacinas.toModel(vacinasRequest);

        vacinas = vacinasRepository.save(vacinas);

        String tokenId = tokenService.generateTokenId(vacinas.getId().toString());

        return mapperVacinas.toResponse(vacinas, tokenId);
    }

    @Override
    public List<VacinasResponse> getALlVacinas() {

        List<Vacinas> vacinas = vacinasRepository.findAll();

        return vacinas.stream()
                .map(x -> mapperVacinas.toResponse(
                        x,
                        tokenService.generateTokenId(x.getId().toString())
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteVacina(String tokenId) {

        String id = tokenService.decodeToken(tokenId);

        calendarioVacinacaoRepository.deleteById(UUID.fromString(id));

    }
}
