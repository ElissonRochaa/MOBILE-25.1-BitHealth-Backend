package br.com.bitwise.bithealth.modules.unidadeSaude.services;


import br.com.bitwise.bithealth.modules.unidadeSaude.dto.UnidadeSaudeRequest;
import br.com.bitwise.bithealth.modules.unidadeSaude.dto.UnidadeSaudeResponse;
import br.com.bitwise.bithealth.modules.unidadeSaude.model.UnidadeSaude;
import br.com.bitwise.bithealth.modules.unidadeSaude.repository.UnidadeSaudeRepository;
import br.com.bitwise.bithealth.modules.unidadeSaude.services.mapper.UnidadeSaudeMapper;
import br.com.bitwise.bithealth.utils.encryptId.CriptIdUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnidadeSaudeService {

    private UnidadeSaudeRepository unidadeSaudeRepository;
    private UnidadeSaudeMapper mapperUnidadeSaude;
    private CriptIdUtils criptIdUtils;

    public UnidadeSaudeService(UnidadeSaudeRepository unidadeSaudeRepository, UnidadeSaudeMapper mapperUnidadeSaude, CriptIdUtils criptIdUtils) {
        this.unidadeSaudeRepository = unidadeSaudeRepository;
        this.mapperUnidadeSaude = mapperUnidadeSaude;
        this.criptIdUtils = criptIdUtils;
    }

    public UnidadeSaudeResponse createUnidadeSaude(UnidadeSaudeRequest unidadeSaudeRequest) {
        UnidadeSaude UnidadeSaude = mapperUnidadeSaude.requestToModel(unidadeSaudeRequest);
        UnidadeSaude UnidadeSaudeSaved = unidadeSaudeRepository.save(UnidadeSaude);

        String tokenId = criptIdUtils.generateTokenId(String.valueOf(UnidadeSaudeSaved.getId()));

        return mapperUnidadeSaude.modelToResponse(UnidadeSaudeSaved, tokenId);
    }

    public UnidadeSaudeResponse getUnidadeSaudeById(String tokenId) {
        String id = criptIdUtils.decodeToken(tokenId);
        UnidadeSaude UnidadeSaude = unidadeSaudeRepository.findById(Long.parseLong(id)).orElseThrow();

        return mapperUnidadeSaude.modelToResponse(UnidadeSaude, tokenId);
    }

    public List<UnidadeSaudeResponse> getAllUnidadeSaude() {
        List<UnidadeSaude> UnidadeSaudes = unidadeSaudeRepository.findAll();
        return UnidadeSaudes.stream()
                .map(UnidadeSaude -> mapperUnidadeSaude.modelToResponse(UnidadeSaude, criptIdUtils.generateTokenId(String.valueOf(UnidadeSaude.getId()))))
                .collect(Collectors.toList());
    }

    public Boolean existsUnidadeSaude(String tokenId) {
        String id = criptIdUtils.decodeToken(tokenId);
        return unidadeSaudeRepository.existsById(Long.parseLong(id));
    }

    public Void deleteUnidadeSaude(String tokenId) {
        String id = criptIdUtils.decodeToken(tokenId);
        unidadeSaudeRepository.deleteById(Long.parseLong(id));
        return null;
    }
}
