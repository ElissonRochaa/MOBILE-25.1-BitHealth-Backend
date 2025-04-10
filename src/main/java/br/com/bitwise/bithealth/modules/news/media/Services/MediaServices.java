package br.com.bitwise.bithealth.modules.news.media.Services;

import br.com.bitwise.bithealth.modules.news.media.DTO.MediaRequest;
import br.com.bitwise.bithealth.modules.news.media.DTO.MediaResponse;

import java.util.UUID;

public interface MediaServices {
    MediaResponse saveMedia(MediaRequest mediaRequest, String noticiaId);
    MediaResponse findAllMediaResponseByNoticiaId(String tokenNoticiaId);
}
