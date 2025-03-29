package br.com.bitwise.bithealth.modules.medicamentos.dto;

public record MedicamentoResponse(
        String tokenId,
        String nome,
        String descricao,
        Integer quantidade,
        String tipoMedicamento
) {
}
