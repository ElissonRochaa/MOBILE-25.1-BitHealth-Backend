package br.com.bitwise.bithealth.modules.medicamentos.services;

import br.com.bitwise.bithealth.modules.medicamentos.dto.MedicamentoRequest;
import br.com.bitwise.bithealth.modules.medicamentos.dto.MedicamentoResponse;

import java.util.List;

public interface MedicamentoServices {

    MedicamentoResponse createMedicamento(MedicamentoRequest medicamentoResponse);
    List<MedicamentoResponse> getAllMedicamentos();
    void deleteMedicamento(String tokenId);
    MedicamentoResponse updateMedicamento(MedicamentoResponse medicamentoResponse, String tokenId);
}
