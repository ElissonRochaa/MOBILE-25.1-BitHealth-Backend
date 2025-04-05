package br.com.bitwise.bithealth.modules.news.media.Services.mapper;

import br.com.bitwise.bithealth.modules.news.media.DTO.MediaRequest;
import br.com.bitwise.bithealth.modules.news.media.DTO.MediaResponse;
import br.com.bitwise.bithealth.modules.news.media.domain.Media;

public interface MediasMapper {
    Media requestToDomain(MediaRequest mediaRequest, String noticiaId);

    MediaResponse domainToResponse(Media media, String tokenId);
}
