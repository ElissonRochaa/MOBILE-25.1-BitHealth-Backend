package br.com.bitwise.bithealth.modules.news.media.Services.mapper;

import br.com.bitwise.bithealth.modules.news.media.DTO.MediaRequest;
import br.com.bitwise.bithealth.modules.news.media.DTO.MediaResponse;
import br.com.bitwise.bithealth.modules.news.media.domain.Media;
import br.com.bitwise.bithealth.modules.news.model.News;
import br.com.bitwise.bithealth.modules.news.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
@RequiredArgsConstructor
public class MediasMapperImpl implements MediasMapper {

    private final NewsRepository newsRepository;

    @Override
    public Media requestToDomain(MediaRequest mediaRequest, String noticiaId) {

        News noticia = newsRepository.findById(UUID.fromString(noticiaId))
                .orElseThrow(() -> new IllegalArgumentException("Noticia not found with id: " + noticiaId));

        return new Media(
                mediaRequest.imagem(),
                noticia
        );
    }

    @Override
    public MediaResponse domainToResponse(Media media, String tokenId) {
        return new MediaResponse(
                tokenId,
                media.getImagem(),
                media.getNoticia().getId().toString()
        );
    }
}
