package br.com.bitwise.bithealth.modules.vacinas.dto;

import br.com.bitwise.bithealth.modules.vacinas.model.Enums.Doses;
import br.com.bitwise.bithealth.modules.vacinas.model.Enums.FaixaEtaria;

public record VacinasResponse(

        String tokenId,
        String vacina,
        Doses doses,
        String idade,
        String doencasEvitadas,
        FaixaEtaria faixaEtaria

) {
}
