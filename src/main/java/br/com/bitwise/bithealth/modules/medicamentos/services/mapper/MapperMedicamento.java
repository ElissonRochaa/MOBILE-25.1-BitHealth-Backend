package br.com.bitwise.bithealth.modules.medicamentos.services.mapper;

import br.com.bitwise.bithealth.modules.medicamentos.dto.MedicamentoRequest;
import br.com.bitwise.bithealth.modules.medicamentos.dto.MedicamentoResponse;
import br.com.bitwise.bithealth.modules.medicamentos.model.Medicamento;

public interface MapperMedicamento {

    Medicamento requestToModel(MedicamentoRequest medicamentoRequest);
    MedicamentoResponse modelToResponse(Medicamento medicamentoSalvo, String tokenId);
}
