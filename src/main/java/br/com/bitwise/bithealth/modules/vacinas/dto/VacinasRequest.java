package br.com.bitwise.bithealth.modules.vacinas.dto;

import br.com.bitwise.bithealth.modules.vacinas.model.Enums.Doses;
import br.com.bitwise.bithealth.modules.vacinas.model.Enums.FaixaEtaria;
import jakarta.validation.constraints.NotNull;

public record VacinasRequest(

        @NotNull(message = "O campo vacina é obrigatorio")
        String vacina,

        @NotNull(message = "O campo doses é obrigatorio")
        Doses doses,

        @NotNull(message = "O campo idade é obrigatorio")
        String idade,

        @NotNull(message = "O campo doenças é obrigatorio")
        String doencasEvitadas,

        @NotNull(message = "O campo faixa etaria é obrigatorio")
        FaixaEtaria faixaEtaria

) {
}
