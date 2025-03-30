package br.com.bitwise.bithealth.modules.unidade_saude.endereco.services.mapper;

import br.com.bitwise.bithealth.modules.unidade_saude.endereco.dto.EnderecoUnidadesRequestDTO;
import br.com.bitwise.bithealth.modules.unidade_saude.endereco.dto.EnderecoUnidadesResponseDTO;
import br.com.bitwise.bithealth.modules.unidade_saude.endereco.model.EnderecoUnidades;
import br.com.bitwise.bithealth.modules.unidade_saude.model.UnidadeSaude;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnderecoUnidadesMapper {
    public EnderecoUnidades requestToModel(EnderecoUnidadesRequestDTO enderecoUnidadeRequest, UnidadeSaude unidadeSaude) {
        return new EnderecoUnidades(
                enderecoUnidadeRequest.logradouro(),
                enderecoUnidadeRequest.numero(),
                enderecoUnidadeRequest.complemento(),
                enderecoUnidadeRequest.bairro(),
                enderecoUnidadeRequest.cidade(),
                enderecoUnidadeRequest.estado(),
                "latitude",
                "longitude",
                enderecoUnidadeRequest.cep(),
                unidadeSaude
        );
    }

    public EnderecoUnidadesResponseDTO modelToResponse(EnderecoUnidades enderecoUnidades, String tokenIdEndereco) {
        return new EnderecoUnidadesResponseDTO(
                tokenIdEndereco,
                enderecoUnidades.getLogradouro(),
                enderecoUnidades.getNumero(),
                enderecoUnidades.getComplemento(),
                enderecoUnidades.getBairro(),
                enderecoUnidades.getCidade(),
                enderecoUnidades.getEstado(),
                enderecoUnidades.getLatitude(),
                enderecoUnidades.getLongitude(),
                enderecoUnidades.getCep()
        );
    }
}
