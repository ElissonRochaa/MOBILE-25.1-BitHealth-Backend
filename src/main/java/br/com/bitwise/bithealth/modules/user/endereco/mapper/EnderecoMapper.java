package br.com.bitwise.bithealth.modules.user.endereco.mapper;

import br.com.bitwise.bithealth.modules.user.endereco.dto.EnderecoDTO;
import br.com.bitwise.bithealth.modules.user.endereco.model.Endereco;

public interface EnderecoMapper {
    Endereco toEntity(EnderecoDTO enderecoDTO);
    EnderecoDTO toDto(Endereco endereco);
}
