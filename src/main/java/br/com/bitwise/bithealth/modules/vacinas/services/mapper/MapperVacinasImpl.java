package br.com.bitwise.bithealth.modules.vacinas.services.mapper;


import br.com.bitwise.bithealth.modules.vacinas.dto.VacinasRequest;
import br.com.bitwise.bithealth.modules.vacinas.dto.VacinasResponse;
import br.com.bitwise.bithealth.modules.vacinas.model.Vacinas;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperVacinasImpl implements MapperVacinas {
    @Override
    public Vacinas toModel(VacinasRequest vacinasRequest) {
        return new Vacinas(
                vacinasRequest.vacina(),
                vacinasRequest.idade(),
                vacinasRequest.doses(),
                vacinasRequest.doencasEvitadas(),
                vacinasRequest.faixaEtaria()
        );
    }

    @Override
    public VacinasResponse toResponse(Vacinas vacinas, String tokenId) {
        return new VacinasResponse(
                tokenId,
                vacinas.getVacina(),
                vacinas.getDoses(),
                vacinas.getIdade(),
                vacinas.getDoencasEvitadas(),
                vacinas.getFaixaEtaria()
        );
    }
}
