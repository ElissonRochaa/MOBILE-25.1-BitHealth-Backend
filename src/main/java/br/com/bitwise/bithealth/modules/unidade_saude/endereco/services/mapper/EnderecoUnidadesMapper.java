package br.com.bitwise.bithealth.modules.unidade_saude.endereco.services.mapper;

import br.com.bitwise.bithealth.modules.unidade_saude.endereco.dto.EnderecoUnidadesRequestDTO;
import br.com.bitwise.bithealth.modules.unidade_saude.endereco.dto.EnderecoUnidadesResponseDTO;
import br.com.bitwise.bithealth.modules.unidade_saude.endereco.model.EnderecoUnidades;
import br.com.bitwise.bithealth.modules.unidade_saude.model.UnidadeSaude;

public interface EnderecoUnidadesMapper {
    EnderecoUnidades requestToModel(EnderecoUnidadesRequestDTO enderecoUnidadeRequest, UnidadeSaude unidadeSaude);
    EnderecoUnidadesResponseDTO modelToResponse(EnderecoUnidades enderecoUnidades, String tokenIdEndereco);
}
