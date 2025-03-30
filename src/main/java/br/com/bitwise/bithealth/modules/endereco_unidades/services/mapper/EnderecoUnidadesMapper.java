package br.com.bitwise.bithealth.modules.endereco_unidades.services.mapper;

import br.com.bitwise.bithealth.modules.endereco_unidades.dto.EnderecoUnidadesRequestDTO;
import br.com.bitwise.bithealth.modules.endereco_unidades.dto.EnderecoUnidadesResponseDTO;
import br.com.bitwise.bithealth.modules.endereco_unidades.model.EnderecoUnidades;
import br.com.bitwise.bithealth.modules.endereco_unidades.services.AdressApiService;
import br.com.bitwise.bithealth.modules.unidade_saude.model.UnidadeSaude;
import br.com.bitwise.bithealth.modules.unidade_saude.services.UnidadeSaudeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnderecoUnidadesMapper {

    private final UnidadeSaudeService unidadeSaudeService;
    private final AdressApiService adressApiService;


    // Mapeia o DTO de Request para o modelo de entidade EnderecoUnidades
    public EnderecoUnidades requestToModel(EnderecoUnidadesRequestDTO enderecoUnidadeRequest) {
        UnidadeSaude unidadeSaude = unidadeSaudeService.getUnidadeSaudeById(enderecoUnidadeRequest.unidadeTokenId());

        // Monta o endereço completo a partir dos dados recebidos
        String endereco = enderecoUnidadeRequest.logradouro() + ", " +
                enderecoUnidadeRequest.numero() + ", " +
                enderecoUnidadeRequest.bairro() + ", " +
                enderecoUnidadeRequest.cidade() + ", " +
                enderecoUnidadeRequest.estado() + ", " +
                enderecoUnidadeRequest.cep();

        // Chama o serviço para obter as coordenadas (latitude e longitude)
        String coordinates = adressApiService.getCoordinatesAsString(endereco);

        // Verifica se as coordenadas foram recebidas com sucesso
        if (coordinates == null) {
            throw new RuntimeException("Não foi possível obter as coordenadas para o endereço fornecido.");
        }

        // Divide as coordenadas em latitude e longitude
        String[] coords = coordinates.split(",");
        String latitude = coords[0];
        String longitude = coords[1];

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

    // Mapeia o modelo EnderecoUnidades para o DTO de Response
    public EnderecoUnidadesResponseDTO modelToResponse(EnderecoUnidades enderecoUnidade) {
        return new EnderecoUnidadesResponseDTO(
                enderecoUnidade.getId().toString(),
                enderecoUnidade.getLogradouro(),
                enderecoUnidade.getNumero(),
                enderecoUnidade.getComplemento(),
                enderecoUnidade.getBairro(),
                enderecoUnidade.getCidade(),
                enderecoUnidade.getEstado(),
                enderecoUnidade.getLatitude(),
                enderecoUnidade.getLongitude(),
                enderecoUnidade.getCep()
        );
    }
}
