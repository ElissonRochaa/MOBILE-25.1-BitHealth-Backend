package br.com.bitwise.bithealth.modules.unidade_saude.endereco.services.mapper;

import br.com.bitwise.bithealth.modules.unidade_saude.endereco.dto.EnderecoUnidadesRequestDTO;
import br.com.bitwise.bithealth.modules.unidade_saude.endereco.dto.EnderecoUnidadesResponseDTO;
import br.com.bitwise.bithealth.modules.unidade_saude.endereco.model.EnderecoUnidades;
import br.com.bitwise.bithealth.modules.unidade_saude.model.UnidadeSaude;
import br.com.bitwise.bithealth.modules.unidade_saude.endereco.services.AdressApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnderecoUnidadesMapperImpl implements EnderecoUnidadesMapper {

    private final AdressApiService adressApiService;

    @Override
    public EnderecoUnidades requestToModel(EnderecoUnidadesRequestDTO enderecoUnidadeRequest, UnidadeSaude unidadeSaude) {

        String coordinates = adressApiService.getCoordinatesAsString(
                enderecoUnidadeRequest.logradouro() + ", " +
                        enderecoUnidadeRequest.bairro() + ", " +
                        enderecoUnidadeRequest.cidade() + ", " +
                        enderecoUnidadeRequest.estado() + ", " +
                        enderecoUnidadeRequest.cep()
        );

        String latitude = null;
        String longitude = null;

        if (coordinates != null) {
            String[] coords = coordinates.split(",");
            latitude = coords[0];
            longitude = coords[1];
        }

        return new EnderecoUnidades(
                enderecoUnidadeRequest.logradouro(),
                enderecoUnidadeRequest.numero(),
                enderecoUnidadeRequest.complemento(),
                enderecoUnidadeRequest.bairro(),
                enderecoUnidadeRequest.cidade(),
                enderecoUnidadeRequest.estado(),
                latitude,
                longitude,
                enderecoUnidadeRequest.cep(),
                unidadeSaude
        );
    }

    @Override
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
