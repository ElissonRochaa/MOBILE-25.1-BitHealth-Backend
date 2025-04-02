package br.com.bitwise.bithealth.modules.news.services.mapper;


import br.com.bitwise.bithealth.modules.news.dto.NewsRequest;
import br.com.bitwise.bithealth.modules.news.dto.NewsResponse;
import br.com.bitwise.bithealth.modules.news.model.News;
import br.com.bitwise.bithealth.modules.user.model.Usuario;

public interface NewsMapper {

    News requestToModel(NewsRequest newsRequest, Usuario administrador);

    NewsResponse modelToResponse(News news, String tokenId);
}
