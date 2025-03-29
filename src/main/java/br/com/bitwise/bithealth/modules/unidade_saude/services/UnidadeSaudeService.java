package br.com.bitwise.bithealth.modules.unidade_saude.services;


import br.com.bitwise.bithealth.modules.unidade_saude.dto.UnidadeSaudeRequest;
import br.com.bitwise.bithealth.modules.unidade_saude.dto.UnidadeSaudeResponse;
import br.com.bitwise.bithealth.modules.unidade_saude.model.UnidadeSaude;
import br.com.bitwise.bithealth.modules.unidade_saude.repository.UnidadeSaudeRepository;
import br.com.bitwise.bithealth.modules.unidade_saude.services.mapper.UnidadeSaudeMapper;
import br.com.bitwise.bithealth.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UnidadeSaudeService {

    private final UnidadeSaudeRepository unidadeSaudeRepository;
    private final UnidadeSaudeMapper mapperUnidadeSaude;
    private final TokenService tokenService;

    public UnidadeSaudeResponse createUnidadeSaude(UnidadeSaudeRequest unidadeSaudeRequest) {
        UnidadeSaude UnidadeSaude = mapperUnidadeSaude.requestToModel(unidadeSaudeRequest);
        UnidadeSaude UnidadeSaudeSaved = unidadeSaudeRepository.save(UnidadeSaude);

        String tokenId = tokenService.generateTokenId(String.valueOf(UnidadeSaudeSaved.getId()));

        return mapperUnidadeSaude.modelToResponse(UnidadeSaudeSaved, tokenId);
    }

    public UnidadeSaude getUnidadeSaudeById(String tokenId) {
        String id = tokenService.decodeToken(tokenId);
        return unidadeSaudeRepository.findById(UUID.fromString(id)).orElse(null);
    }

    public List<UnidadeSaudeResponse> getAllUnidadeSaude() {
        List<UnidadeSaude> UnidadeSaudes = unidadeSaudeRepository.findAll();
        return UnidadeSaudes.stream()
                .map(UnidadeSaude -> mapperUnidadeSaude.modelToResponse(UnidadeSaude, tokenService.generateTokenId(String.valueOf(UnidadeSaude.getId()))))
                .collect(Collectors.toList());
    }

    public Boolean existsUnidadeSaude(String tokenId) {
        String id = tokenService.decodeToken(tokenId);
        return unidadeSaudeRepository.existsById(UUID.fromString(id));
    }

    public Void deleteUnidadeSaude(String tokenId) {
        String id = tokenService.decodeToken(tokenId);
        unidadeSaudeRepository.deleteById(UUID.fromString(id));
        return null;
    }
}
