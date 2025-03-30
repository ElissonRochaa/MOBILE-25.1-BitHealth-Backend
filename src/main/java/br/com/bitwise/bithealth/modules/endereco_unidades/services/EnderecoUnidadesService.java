package br.com.bitwise.bithealth.modules.endereco_unidades.services;

import br.com.bitwise.bithealth.modules.endereco_unidades.dto.EnderecoUnidadesRequestDTO;
import br.com.bitwise.bithealth.modules.endereco_unidades.dto.EnderecoUnidadesResponseDTO;

public interface EnderecoUnidadesService {

    EnderecoUnidadesResponseDTO createEnderecoUnidade(EnderecoUnidadesRequestDTO enderecoUnidadeRequest);
    EnderecoUnidadesResponseDTO getEnderecoByUnidadeId(String unidadeSaudeId);
    void deleteEnderecoUnidade(String tokenId);
}
