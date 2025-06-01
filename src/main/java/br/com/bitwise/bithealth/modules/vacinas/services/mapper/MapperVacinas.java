package br.com.bitwise.bithealth.modules.vacinas.services.mapper;

import br.com.bitwise.bithealth.modules.vacinas.dto.VacinasRequest;
import br.com.bitwise.bithealth.modules.vacinas.dto.VacinasResponse;
import br.com.bitwise.bithealth.modules.vacinas.model.Vacinas;

public interface MapperVacinas {

    Vacinas toModel(VacinasRequest vacinasRequest);
    VacinasResponse toResponse(Vacinas vacinas, String tokenId);

}
