package br.com.bitwise.bithealth.modules.news.media.Services;

import br.com.bitwise.bithealth.modules.news.media.DTO.MediaRequest;
import br.com.bitwise.bithealth.modules.news.media.DTO.MediaResponse;
import br.com.bitwise.bithealth.modules.news.media.Services.mapper.MediasMapper;
import br.com.bitwise.bithealth.modules.news.media.domain.Media;
import br.com.bitwise.bithealth.modules.news.media.repository.MediaRepository;
import br.com.bitwise.bithealth.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MedisServicesImpl implements MediaServices {

    private final MediasMapper mediasMapper;
    private final MediaRepository mediaRepository;
    private final TokenService tokenService;

    @Override
    public MediaResponse saveMedia(MediaRequest mediaRequest) {
        String noticiaId = tokenService.decodeToken(mediaRequest.noticiaId());
        Media media = mediasMapper.requestToDomain(mediaRequest, noticiaId);
        media = mediaRepository.save(media);
        String tokenId = tokenService.generateTokenId(String.valueOf(media.getId()));
        return mediasMapper.domainToResponse(media, tokenId);
    }

    @Override
    public MediaResponse findAllMediaResponseByNoticiaId(String tokenNoticiaId) {
        String noticiaId = tokenService.decodeToken(tokenNoticiaId);
        Media media = mediaRepository.findAllByNoticiaId(UUID.fromString(noticiaId));
        if (media == null) {
            return null;
        }
        String tokenId = tokenService.generateTokenId(String.valueOf(media.getId()));
        return mediasMapper.domainToResponse(media, tokenId);
    }
}
