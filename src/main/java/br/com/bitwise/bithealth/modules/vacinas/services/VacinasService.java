package br.com.bitwise.bithealth.modules.vacinas.services;

import br.com.bitwise.bithealth.modules.vacinas.dto.VacinasRequest;
import br.com.bitwise.bithealth.modules.vacinas.dto.VacinasResponse;

import java.util.List;

public interface VacinasService {

    VacinasResponse createVacina(VacinasRequest vacinasRequest);
    List<VacinasResponse> getALlVacinas();
    void deleteVacina(String tokenId);

}
