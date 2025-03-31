package br.com.bitwise.bithealth.modules.unidade_saude.services;


import br.com.bitwise.bithealth.modules.unidade_saude.endereco.dto.EnderecoUnidadesRequestDTO;
import br.com.bitwise.bithealth.modules.unidade_saude.endereco.dto.EnderecoUnidadesResponseDTO;
import br.com.bitwise.bithealth.modules.unidade_saude.endereco.model.EnderecoUnidades;
import br.com.bitwise.bithealth.modules.unidade_saude.endereco.repository.EnderecoUnidadesRepository;
import br.com.bitwise.bithealth.modules.unidade_saude.endereco.services.mapper.EnderecoUnidadesMapper;
import br.com.bitwise.bithealth.modules.medicamentos.repository.MedicamentosRepository;
import br.com.bitwise.bithealth.modules.servicos_saude.repository.ServicosSaudeRepository;
import br.com.bitwise.bithealth.modules.unidade_saude.dto.UnidadeSaudeRequest;
import br.com.bitwise.bithealth.modules.unidade_saude.dto.UnidadeSaudeResponse;
import br.com.bitwise.bithealth.modules.unidade_saude.exceptions.UnidadeSaudeAlreadyExistsException;
import br.com.bitwise.bithealth.modules.unidade_saude.exceptions.UnidadeSaudeNotFoundException;
import br.com.bitwise.bithealth.modules.unidade_saude.model.UnidadeSaude;
import br.com.bitwise.bithealth.modules.unidade_saude.repository.UnidadeSaudeRepository;
import br.com.bitwise.bithealth.modules.unidade_saude.services.mapper.UnidadeSaudeMapper;
import br.com.bitwise.bithealth.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UnidadeSaudeServiceImpl implements UnidadeSaudeService {

    private final UnidadeSaudeRepository unidadeSaudeRepository;
    private final ServicosSaudeRepository servicosSaudeRepository;
    private final MedicamentosRepository medicamentosRepository;
    private final UnidadeSaudeMapper mapperUnidadeSaude;
    private final EnderecoUnidadesRepository enderecoUnidadesRepository;
    private final EnderecoUnidadesMapper enderecoUnidadesMapper;
    private final TokenService tokenService;

    @Override
    public UnidadeSaudeResponse createUnidadeSaude(UnidadeSaudeRequest unidadeSaudeRequest) {
        if (unidadeSaudeRepository.getByNome(unidadeSaudeRequest.nome()) != null) {
            throw new UnidadeSaudeAlreadyExistsException("Já existe uma unidade de saúde com o nome informado");
        }

        UnidadeSaude unidadeSaude = mapperUnidadeSaude.requestToModel(unidadeSaudeRequest);
        String tokenIdUnidade = tokenService.generateTokenId(String.valueOf(unidadeSaude.getId()));
        unidadeSaude = unidadeSaudeRepository.save(unidadeSaude);

        EnderecoUnidadesRequestDTO enderecoUnidadeRequest = unidadeSaudeRequest.enderecoUnidadesRequestDTO();
        EnderecoUnidades enderecoUnidades = enderecoUnidadesMapper.requestToModel(enderecoUnidadeRequest, unidadeSaude);
        enderecoUnidades = enderecoUnidadesRepository.save(enderecoUnidades);
        String tokenIdEndereco = tokenService.generateTokenId(String.valueOf(enderecoUnidades.getId()));
        EnderecoUnidadesResponseDTO enderecoUnidadesResponseDTO = enderecoUnidadesMapper.modelToResponse(enderecoUnidades, tokenIdEndereco);

        return mapperUnidadeSaude.modelToResponse(unidadeSaude, tokenIdUnidade, enderecoUnidadesResponseDTO);
    }

    @Override
    public UnidadeSaude getUnidadeSaudeById(String tokenId) {
        String id = tokenService.decodeToken(tokenId);
        return unidadeSaudeRepository.findById(UUID.fromString(id)).orElseThrow(() -> new UnidadeSaudeNotFoundException("Unidade de saúde não encontrada"));
    }

    @Override
    public List<UnidadeSaudeResponse> getAllUnidadeSaude() {
        List<UnidadeSaude> unidadeSaude = unidadeSaudeRepository.findAll();
        return unidadeSaude.stream()
                .map(x -> mapperUnidadeSaude.modelToResponse(
                        x,
                        tokenService.generateTokenId(String.valueOf(x.getId())
                        )))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUnidadeSaude(String tokenId) {
        String id = tokenService.decodeToken(tokenId);
        servicosSaudeRepository.deleteByUnidadeSaudeId(UUID.fromString(id));
        medicamentosRepository.deleteByUnidadeSaudeId(UUID.fromString(id));
        enderecoUnidadesRepository.deleteByUnidadeSaudeId(UUID.fromString(id));
        unidadeSaudeRepository.deleteById(UUID.fromString(id));
    }
}
