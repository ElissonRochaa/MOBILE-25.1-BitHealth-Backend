package br.com.bitwise.bithealth.modules.endereco_unidades.dto;

public record EnderecoUnidadesResponseDTO(

        String enderecoUnidadeId,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String latitude,
        String longitude,
        String cep
) {
}
