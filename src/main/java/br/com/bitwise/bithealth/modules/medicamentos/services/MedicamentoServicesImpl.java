package br.com.bitwise.bithealth.modules.medicamentos.services;

import br.com.bitwise.bithealth.modules.medicamentos.dto.MedicamentoRequest;
import br.com.bitwise.bithealth.modules.medicamentos.dto.MedicamentoResponse;
import br.com.bitwise.bithealth.modules.medicamentos.exceptions.MedicamentoNotFoundException;
import br.com.bitwise.bithealth.modules.medicamentos.model.ENUMS.TipoMedicamentoEnum;
import br.com.bitwise.bithealth.modules.medicamentos.model.Medicamento;
import br.com.bitwise.bithealth.modules.medicamentos.repository.MedicamentosRepository;
import br.com.bitwise.bithealth.modules.medicamentos.services.mapper.MapperMedicamento;
import br.com.bitwise.bithealth.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MedicamentoServicesImpl implements MedicamentoServices {

    private final MedicamentosRepository medicamentosRepository;
    private final MapperMedicamento mapperMedicamento;
    private final TokenService tokenService;

    public MedicamentoResponse createMedicamento(MedicamentoRequest medicamentoResponse) {
        Medicamento medicamento = mapperMedicamento.requestToModel(medicamentoResponse);
        Medicamento medicamentoSalvo = medicamentosRepository.save(medicamento);
        String tokenId = tokenService.generateTokenId(String.valueOf(medicamentoSalvo.getId()));
        return mapperMedicamento.modelToResponse(medicamentoSalvo, tokenId);
    }

    public List<MedicamentoResponse> getAllMedicamentos() {
        List<Medicamento> medicamentoList = medicamentosRepository.findAll();
        return medicamentoList.stream()
                .map(medicamento -> mapperMedicamento.modelToResponse(
                        medicamento,
                        tokenService.generateTokenId(String.valueOf(medicamento.getId())
                        )))
                .toList();
    }

    public void deleteMedicamento(String tokenId) {
        String id = tokenService.decodeToken(tokenId);
        medicamentosRepository.deleteById(UUID.fromString(id));
    }

    public MedicamentoResponse updateMedicamento(MedicamentoResponse medicamentoResponse, String tokenId) {
        String id = tokenService.decodeToken(tokenId);
        Medicamento medicamento = medicamentosRepository.findById(UUID.fromString(id)).orElseThrow(() -> new MedicamentoNotFoundException("Medicamento não encontrado"));
        medicamento.setNome(medicamentoResponse.nome());
        medicamento.setDescricao(medicamentoResponse.descricao());
        medicamento.setQuantidade(medicamentoResponse.quantidade());
        medicamento.setTipoMedicamento(TipoMedicamentoEnum.fromString(medicamentoResponse.tipoMedicamento()));
        Medicamento medicamentoSalvo = medicamentosRepository.save(medicamento);
        return mapperMedicamento.modelToResponse(medicamentoSalvo, tokenId);
    }
}
