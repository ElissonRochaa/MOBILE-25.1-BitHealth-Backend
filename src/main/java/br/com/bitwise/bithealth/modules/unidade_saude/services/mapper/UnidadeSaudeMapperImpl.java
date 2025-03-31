package br.com.bitwise.bithealth.modules.unidade_saude.services.mapper;

import br.com.bitwise.bithealth.modules.unidade_saude.endereco.dto.EnderecoUnidadesResponseDTO;
import br.com.bitwise.bithealth.modules.unidade_saude.endereco.model.EnderecoUnidades;
import br.com.bitwise.bithealth.modules.unidade_saude.endereco.repository.EnderecoUnidadesRepository;
import br.com.bitwise.bithealth.modules.medicamentos.dto.MedicamentoResponse;
import br.com.bitwise.bithealth.modules.medicamentos.model.Medicamento;
import br.com.bitwise.bithealth.modules.medicamentos.repository.MedicamentosRepository;
import br.com.bitwise.bithealth.modules.servicos_saude.dto.ServicosSaudeResponse;
import br.com.bitwise.bithealth.modules.servicos_saude.model.ServicosSaude;
import br.com.bitwise.bithealth.modules.servicos_saude.repository.ServicosSaudeRepository;
import br.com.bitwise.bithealth.modules.unidade_saude.dto.UnidadeSaudeRequest;
import br.com.bitwise.bithealth.modules.unidade_saude.dto.UnidadeSaudeResponse;
import br.com.bitwise.bithealth.modules.unidade_saude.model.ENUMS.TipoUnidade;
import br.com.bitwise.bithealth.modules.unidade_saude.model.UnidadeSaude;
import br.com.bitwise.bithealth.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UnidadeSaudeMapperImpl implements UnidadeSaudeMapper {

    private final ServicosSaudeRepository servicosSaudeRepository;
    private final MedicamentosRepository medicamentosRepository;
    private final EnderecoUnidadesRepository enderecoUnidadesRepository;
    private final TokenService tokenService;

    @Override
    public UnidadeSaude requestToModel(UnidadeSaudeRequest unidadeSaudeRequest) {

        return new UnidadeSaude(
                unidadeSaudeRequest.nome(),
                TipoUnidade.fromString(unidadeSaudeRequest.tipo()),
                unidadeSaudeRequest.horarioInicioAtendimento(),
                unidadeSaudeRequest.horarioFimAtendimento()
        );
    }

    @Override
    public UnidadeSaudeResponse modelToResponse(UnidadeSaude unidadeSaude, String tokenId) {

        List<ServicosSaudeResponse> servicosSaudeResponseList = mapServicosSaude(unidadeSaude.getId());
        List<MedicamentoResponse> medicamentoResponseList = mapMedicamentos(unidadeSaude.getId());
        EnderecoUnidadesResponseDTO enderecoUnidadesResponseDTO = mapEnderecoUnidades(unidadeSaude.getId());

        return new UnidadeSaudeResponse(
                tokenId,
                unidadeSaude.getNome(),
                unidadeSaude.getTipoUnidade().toString(),
                unidadeSaude.getHorarioInicioAtendimento(),
                unidadeSaude.getHorarioFimAtendimento(),
                servicosSaudeResponseList,
                medicamentoResponseList,
                enderecoUnidadesResponseDTO
        );
    }

    private List<ServicosSaudeResponse> mapServicosSaude(UUID unidadeSaudeId) {
        List<ServicosSaude> servicosSaudeList = servicosSaudeRepository.findByUnidadeSaudeId(unidadeSaudeId);
        return servicosSaudeList.stream()
                .map(servicosSaude -> new ServicosSaudeResponse(
                        tokenService.generateTokenId(servicosSaude.getId().toString()),
                        servicosSaude.getNome(),
                        servicosSaude.getDescricao(),
                        servicosSaude.getHorarioInicio(),
                        servicosSaude.getHorarioFim()
                ))
                .collect(Collectors.toList());
    }

    private List<MedicamentoResponse> mapMedicamentos(UUID unidadeSaudeId) {
        List<Medicamento> medicamentoList = medicamentosRepository.findByUnidadeSaudeId(unidadeSaudeId);
        return medicamentoList.stream()
                .map(medicamento -> new MedicamentoResponse(
                        tokenService.generateTokenId(medicamento.getId().toString()),
                        medicamento.getNome(),
                        medicamento.getDescricao(),
                        medicamento.getQuantidade(),
                        medicamento.getTipoMedicamento().toString()
                ))
                .collect(Collectors.toList());
    }

    private EnderecoUnidadesResponseDTO mapEnderecoUnidades(UUID unidadeSaudeId) {
        EnderecoUnidades enderecoUnidades = enderecoUnidadesRepository.findByUnidadeSaudeId(unidadeSaudeId);
        return new EnderecoUnidadesResponseDTO(
                tokenService.generateTokenId(enderecoUnidades.getId().toString()),
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

    @Override
    public UnidadeSaudeResponse modelToResponse(UnidadeSaude unidadeSaude, String tokenIdUnidade, EnderecoUnidadesResponseDTO enderecoUnidadesResponseDTO) {

        List<ServicosSaudeResponse> servicosSaudeResponseList = mapServicosSaude(unidadeSaude.getId());
        List<MedicamentoResponse> medicamentoResponseList = mapMedicamentos(unidadeSaude.getId());

        return new UnidadeSaudeResponse(
                tokenIdUnidade,
                unidadeSaude.getNome(),
                unidadeSaude.getTipoUnidade().toString(),
                unidadeSaude.getHorarioInicioAtendimento(),
                unidadeSaude.getHorarioFimAtendimento(),
                servicosSaudeResponseList,
                medicamentoResponseList,
                enderecoUnidadesResponseDTO
        );
    }
}
