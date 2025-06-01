package br.com.bitwise.bithealth.modules.vacinas.dto;

import br.com.bitwise.bithealth.modules.vacinas.model.Enums.Doses;

public record VacinasResponse(

        String tokenId,
        String vacina,
        Doses doses,
        String idade,
        String doencasEvitadas

) {
}
