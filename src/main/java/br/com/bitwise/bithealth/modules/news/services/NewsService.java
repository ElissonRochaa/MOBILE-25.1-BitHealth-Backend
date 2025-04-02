package br.com.bitwise.bithealth.modules.news.services;

import br.com.bitwise.bithealth.modules.news.dto.NewsRequest;
import br.com.bitwise.bithealth.modules.news.dto.NewsResponse;
import br.com.bitwise.bithealth.modules.news.model.News;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface NewsService {

    NewsResponse createNews(NewsRequest newsRequest, HttpServletRequest httpServletRequest);
    News getNewsById(String tokenId);
    List<NewsResponse> getAllNews();
    void deleteNewsById(String tokenId);
}
