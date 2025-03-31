package br.com.bitwise.bithealth.modules.user.endereco.mapper.impl;

import br.com.bitwise.bithealth.modules.user.endereco.dto.EnderecoDTO;
import br.com.bitwise.bithealth.modules.user.endereco.mapper.EnderecoMapper;
import br.com.bitwise.bithealth.modules.user.endereco.model.Endereco;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapperImpl implements EnderecoMapper {

    @Override
    public Endereco toEntity(EnderecoDTO enderecoDTO) {
        return new Endereco(
                enderecoDTO.logradouro(),
                enderecoDTO.numero(),
                enderecoDTO.complemento(),
                enderecoDTO.bairro(),
                enderecoDTO.cidade(),
                enderecoDTO.estado(),
                enderecoDTO.cep()
        );
    }

    @Override
    public EnderecoDTO toDto(Endereco endereco) {
        return new EnderecoDTO(
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep()
        );
    }

}
