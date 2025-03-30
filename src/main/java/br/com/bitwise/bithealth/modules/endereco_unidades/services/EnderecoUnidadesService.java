package br.com.bitwise.bithealth.modules.endereco_unidades.services;

import br.com.bitwise.bithealth.modules.endereco_unidades.dto.EnderecoUnidadesResponseDTO;
import br.com.bitwise.bithealth.modules.endereco_unidades.dto.EnderecoUnidadesRequestDTO;
import br.com.bitwise.bithealth.modules.endereco_unidades.exceptions.EnderecoUnidadesNotFoundException;
import br.com.bitwise.bithealth.modules.endereco_unidades.model.EnderecoUnidades;
import br.com.bitwise.bithealth.modules.endereco_unidades.repository.EnderecoUnidadesRepository;
import br.com.bitwise.bithealth.modules.endereco_unidades.services.mapper.EnderecoUnidadesMapper;
import br.com.bitwise.bithealth.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnderecoUnidadesService {

    private final EnderecoUnidadesRepository enderecoUnidadesRepository;
    private final EnderecoUnidadesMapper mapperEnderecoUnidades;
    private final TokenService tokenService;

    public EnderecoUnidadesResponseDTO createEnderecoUnidade(EnderecoUnidadesRequestDTO enderecoUnidadeRequest) {
        EnderecoUnidades enderecoUnidade = mapperEnderecoUnidades.requestToModel(enderecoUnidadeRequest);
        EnderecoUnidades enderecoUnidadeSalvo = enderecoUnidadesRepository.save(enderecoUnidade);
        String tokenId = tokenService.generateTokenId(String.valueOf(enderecoUnidadeSalvo.getId()));
        return mapperEnderecoUnidades.modelToResponse(enderecoUnidadeSalvo, tokenId);
    }

    public List<EnderecoUnidadesResponseDTO> getAllEnderecosUnidade() {
        List<EnderecoUnidades> enderecoUnidadeList = enderecoUnidadesRepository.findAll();
        return enderecoUnidadeList.stream()
                .map(enderecoUnidade -> mapperEnderecoUnidades.modelToResponse(
                        enderecoUnidade,
                        tokenService.generateTokenId(String.valueOf(enderecoUnidade.getId()))
                ))
                .toList();
    }

    public void deleteEnderecoUnidade(String tokenId) {
        String id = tokenService.decodeToken(tokenId);
        enderecoUnidadesRepository.deleteById(UUID.fromString(id));
    }

    public EnderecoUnidadesResponseDTO updateEnderecoUnidade(EnderecoUnidadesRequestDTO enderecoUnidadeRequest, String tokenId) {
        String id = tokenService.decodeToken(tokenId);
        EnderecoUnidades enderecoUnidade = enderecoUnidadesRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EnderecoUnidadesNotFoundException("Endereço de unidade não encontrado"));
        enderecoUnidade.setLogradouro(enderecoUnidadeRequest.logradouro());
        enderecoUnidade.setNumero(enderecoUnidadeRequest.numero());
        enderecoUnidade.setComplemento(enderecoUnidadeRequest.complemento());
        enderecoUnidade.setBairro(enderecoUnidadeRequest.bairro());
        enderecoUnidade.setCidade(enderecoUnidadeRequest.cidade());
        enderecoUnidade.setEstado(enderecoUnidadeRequest.estado());
        enderecoUnidade.setLatitude(enderecoUnidadeRequest.latitude());
        enderecoUnidade.setLongitude(enderecoUnidadeRequest.longitude());
        enderecoUnidade.setCep(enderecoUnidadeRequest.cep());
        EnderecoUnidades enderecoUnidadeSalvo = enderecoUnidadesRepository.save(enderecoUnidade);
        return mapperEnderecoUnidades.modelToResponse(enderecoUnidadeSalvo, tokenId);
    }
}
