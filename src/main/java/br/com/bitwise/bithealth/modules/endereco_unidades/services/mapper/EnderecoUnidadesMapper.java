package br.com.bitwise.bithealth.modules.endereco_unidades.services.mapper;

import br.com.bitwise.bithealth.modules.endereco_unidades.dto.EnderecoUnidadesRequestDTO;
import br.com.bitwise.bithealth.modules.endereco_unidades.dto.EnderecoUnidadesResponseDTO;
import br.com.bitwise.bithealth.modules.endereco_unidades.model.EnderecoUnidades;
import br.com.bitwise.bithealth.modules.unidade_saude.model.UnidadeSaude;
import br.com.bitwise.bithealth.modules.unidade_saude.services.UnidadeSaudeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnderecoUnidadesMapper {

    private final UnidadeSaudeService unidadeSaudeService;

    // Mapeia o DTO de Request para o modelo de entidade EnderecoUnidades
    public EnderecoUnidades requestToModel(EnderecoUnidadesRequestDTO enderecoUnidadeRequest) {
        UnidadeSaude unidadeSaude = unidadeSaudeService.getUnidadeSaudeById(enderecoUnidadeRequest.unidadeTokenId());

        return new EnderecoUnidades(
                enderecoUnidadeRequest.logradouro(),
                enderecoUnidadeRequest.numero(),
                enderecoUnidadeRequest.complemento(),
                enderecoUnidadeRequest.bairro(),
                enderecoUnidadeRequest.cidade(),
                enderecoUnidadeRequest.estado(),
                enderecoUnidadeRequest.latitude(),
                enderecoUnidadeRequest.longitude(),
                enderecoUnidadeRequest.cep(),
                unidadeSaude
        );
    }

    // Mapeia o modelo EnderecoUnidades para o DTO de Response
    public EnderecoUnidadesResponseDTO modelToResponse(EnderecoUnidades enderecoUnidade, String tokenId) {
        return new EnderecoUnidadesResponseDTO(
                tokenId,
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
