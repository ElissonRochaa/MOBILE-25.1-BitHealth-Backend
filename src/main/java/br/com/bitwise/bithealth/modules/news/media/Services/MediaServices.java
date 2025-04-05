package br.com.bitwise.bithealth.modules.news.media.Services;

import br.com.bitwise.bithealth.modules.news.media.DTO.MediaRequest;
import br.com.bitwise.bithealth.modules.news.media.DTO.MediaResponse;

public interface MediaServices {
    MediaResponse saveMedia(MediaRequest mediaRequest);
    MediaResponse findAllMediaResponseByNoticiaId(String tokenNoticiaId);
}
