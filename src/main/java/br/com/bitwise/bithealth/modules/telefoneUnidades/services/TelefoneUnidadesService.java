package br.com.bitwise.bithealth.modules.telefoneUnidades.services;

import br.com.bitwise.bithealth.modules.telefoneUnidades.dto.TelefoneUnidadesRequestDTO;
import br.com.bitwise.bithealth.modules.telefoneUnidades.dto.TelefoneUnidadesResponseDTO;
import br.com.bitwise.bithealth.modules.telefoneUnidades.mapper.TelefoneUnidadesMapper;
import br.com.bitwise.bithealth.modules.telefoneUnidades.model.TelefoneUnidades;
import br.com.bitwise.bithealth.modules.telefoneUnidades.repository.TelefoneUnidadesRepository;
import br.com.bitwise.bithealth.modules.unidade_saude.model.UnidadeSaude;
import br.com.bitwise.bithealth.modules.unidade_saude.services.UnidadeSaudeService;
import br.com.bitwise.bithealth.security.TokenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelefoneUnidadesService {

    private final TelefoneUnidadesRepository telefoneUnidadesRepository;
    private final UnidadeSaudeService unidadeSaudeService;
    private final TokenService tokenService;
    private final TelefoneUnidadesMapper telefoneUnidadesMapper;

    @Transactional
    public TelefoneUnidadesResponseDTO cadastrarTelefone(TelefoneUnidadesRequestDTO request) {

        UnidadeSaude unidadeSaude = unidadeSaudeService.getUnidadeSaudeById(request.unidadeId());
        TelefoneUnidades telefoneUnidades = telefoneUnidadesMapper.toEntity(request, unidadeSaude);

        TelefoneUnidades telefoneUnidadesSaved = telefoneUnidadesRepository.save(telefoneUnidades);

        String tokenId = tokenService.generateTokenId(String.valueOf(telefoneUnidadesSaved.getId()));

        return telefoneUnidadesMapper.toDto(telefoneUnidadesSaved,tokenId);
    }


}
