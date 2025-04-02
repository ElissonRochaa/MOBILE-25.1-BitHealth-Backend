package br.com.bitwise.bithealth.modules.news.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record NewsResponse(

    @JsonProperty(namespace = "tokenId")
    String tokenId,
    String titulo,
    String conteudo,
    LocalDateTime dataPublicacao,
    @JsonProperty(namespace = "administradorTokenId")
    String administradorTokenId

    ) {

}
