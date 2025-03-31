package br.com.bitwise.bithealth.modules.unidade_saude.services;

import br.com.bitwise.bithealth.modules.unidade_saude.dto.UnidadeSaudeRequest;
import br.com.bitwise.bithealth.modules.unidade_saude.dto.UnidadeSaudeResponse;
import br.com.bitwise.bithealth.modules.unidade_saude.model.UnidadeSaude;

import java.util.List;

public interface UnidadeSaudeService {

    UnidadeSaudeResponse createUnidadeSaude(UnidadeSaudeRequest unidadeSaudeRequest);
    UnidadeSaude getUnidadeSaudeById(String tokenId);
    List<UnidadeSaudeResponse> getAllUnidadeSaude();
    void deleteUnidadeSaude(String tokenId);
}
