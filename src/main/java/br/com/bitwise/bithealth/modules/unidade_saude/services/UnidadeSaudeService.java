package br.com.bitwise.bithealth.modules.unidade_saude.services;


import br.com.bitwise.bithealth.modules.servicos_saude.repository.ServicosSaudeRepository;
import br.com.bitwise.bithealth.modules.unidade_saude.dto.UnidadeSaudeRequest;
import br.com.bitwise.bithealth.modules.unidade_saude.dto.UnidadeSaudeResponse;
import br.com.bitwise.bithealth.modules.unidade_saude.exceptions.UnidadeSaudeAlreadyExistsException;
import br.com.bitwise.bithealth.modules.unidade_saude.exceptions.UnidadeSaudeNotFoundException;
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
    private final ServicosSaudeRepository servicosSaudeRepository;
    private final UnidadeSaudeMapper mapperUnidadeSaude;
    private final TokenService tokenService;

    public UnidadeSaudeResponse createUnidadeSaude(UnidadeSaudeRequest unidadeSaudeRequest) {
        if (unidadeSaudeRepository.getByNome(unidadeSaudeRequest.nome()) != null) {
            throw new UnidadeSaudeAlreadyExistsException("Já existe uma unidade de saúde com o nome informado");
        }

        UnidadeSaude UnidadeSaude = mapperUnidadeSaude.requestToModel(unidadeSaudeRequest);
        UnidadeSaude UnidadeSaudeSaved = unidadeSaudeRepository.save(UnidadeSaude);
        String tokenId = tokenService.generateTokenId(String.valueOf(UnidadeSaudeSaved.getId()));

        return mapperUnidadeSaude.modelToResponse(UnidadeSaudeSaved, tokenId);
    }

    public UnidadeSaude getUnidadeSaudeById(String tokenId) {
        String id = tokenService.decodeToken(tokenId);
        return unidadeSaudeRepository.findById(UUID.fromString(id)).orElseThrow(() -> new UnidadeSaudeNotFoundException("Unidade de saúde não encontrada"));
    }

    public List<UnidadeSaudeResponse> getAllUnidadeSaude() {
        List<UnidadeSaude> unidadeSaude = unidadeSaudeRepository.findAll();
        return unidadeSaude.stream()
                .map(x -> mapperUnidadeSaude.modelToResponse(
                        x,
                        tokenService.generateTokenId(String.valueOf(x.getId())
                        )))
                .collect(Collectors.toList());
    }

    public Boolean existsUnidadeSaude(String tokenId) {
        String id = tokenService.decodeToken(tokenId);
        return unidadeSaudeRepository.existsById(UUID.fromString(id));
    }

    public Void deleteUnidadeSaude(String tokenId) {
        String id = tokenService.decodeToken(tokenId);
        servicosSaudeRepository.deleteByUnidadeSaudeId(UUID.fromString(id));
        unidadeSaudeRepository.deleteById(UUID.fromString(id));
        return null;
    }
}
