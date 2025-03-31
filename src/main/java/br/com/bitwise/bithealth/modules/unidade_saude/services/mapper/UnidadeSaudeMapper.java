package br.com.bitwise.bithealth.modules.unidade_saude.services.mapper;

import br.com.bitwise.bithealth.modules.unidade_saude.dto.UnidadeSaudeRequest;
import br.com.bitwise.bithealth.modules.unidade_saude.dto.UnidadeSaudeResponse;
import br.com.bitwise.bithealth.modules.unidade_saude.endereco.dto.EnderecoUnidadesResponseDTO;
import br.com.bitwise.bithealth.modules.unidade_saude.model.UnidadeSaude;

public interface UnidadeSaudeMapper {
    UnidadeSaude requestToModel(UnidadeSaudeRequest unidadeSaudeRequest);
    UnidadeSaudeResponse modelToResponse(UnidadeSaude unidadeSaude, String tokenId);
    UnidadeSaudeResponse modelToResponse(UnidadeSaude unidadeSaude, String tokenId, EnderecoUnidadesResponseDTO enderecoUnidadesResponseDTO);

}
