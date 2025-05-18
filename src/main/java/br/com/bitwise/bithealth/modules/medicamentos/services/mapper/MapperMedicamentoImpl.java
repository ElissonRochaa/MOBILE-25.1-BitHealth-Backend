package br.com.bitwise.bithealth.modules.medicamentos.services.mapper;

import br.com.bitwise.bithealth.modules.medicamentos.dto.MedicamentoRequest;
import br.com.bitwise.bithealth.modules.medicamentos.dto.MedicamentoResponse;
import br.com.bitwise.bithealth.modules.medicamentos.model.ENUMS.TipoMedicamentoEnum;
import br.com.bitwise.bithealth.modules.medicamentos.model.Medicamento;
import br.com.bitwise.bithealth.modules.unidade_saude.model.UnidadeSaude;
import br.com.bitwise.bithealth.modules.unidade_saude.services.UnidadeSaudeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperMedicamentoImpl implements MapperMedicamento {

    private final UnidadeSaudeService unidadeServices;

    public Medicamento requestToModel(MedicamentoRequest medicamentoRequest) {
        UnidadeSaude unidadeSaude = unidadeServices.getUnidadeSaudeById(medicamentoRequest.userTokenId());

        return new Medicamento(
                medicamentoRequest.nome(),
                medicamentoRequest.descricao(),
                medicamentoRequest.quantidade(),
                TipoMedicamentoEnum.fromString(medicamentoRequest.tipoMedicamento()),
                unidadeSaude
        );
    }

    public MedicamentoResponse modelToResponse(Medicamento medicamentoSalvo,String tokenId) {
        return new MedicamentoResponse(
                tokenId,
                medicamentoSalvo.getNome(),
                medicamentoSalvo.getDescricao(),
                medicamentoSalvo.getQuantidade(),
                medicamentoSalvo.getTipoMedicamento().toString(),
                unidadeServices.getUnidadeSaudeNameByIdMedicamento(medicamentoSalvo.getId())
        );
    }
}
