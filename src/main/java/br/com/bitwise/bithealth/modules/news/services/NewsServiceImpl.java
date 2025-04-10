package br.com.bitwise.bithealth.modules.news.services;

import br.com.bitwise.bithealth.modules.news.dto.NewsRequest;
import br.com.bitwise.bithealth.modules.news.dto.NewsResponse;
import br.com.bitwise.bithealth.modules.news.media.DTO.MediaRequest;
import br.com.bitwise.bithealth.modules.news.media.DTO.MediaResponse;
import br.com.bitwise.bithealth.modules.news.media.Services.MediaServices;
import br.com.bitwise.bithealth.modules.news.media.Services.MediaServicesImpl;
import br.com.bitwise.bithealth.modules.news.media.domain.Media;
import br.com.bitwise.bithealth.modules.news.media.repository.MediaRepository;
import br.com.bitwise.bithealth.modules.news.model.News;
import br.com.bitwise.bithealth.modules.news.repository.NewsRepository;
import br.com.bitwise.bithealth.modules.news.services.mapper.NewsMapper;
import br.com.bitwise.bithealth.modules.user.model.Usuario;
import br.com.bitwise.bithealth.modules.user.repository.UsuarioRepository;
import br.com.bitwise.bithealth.security.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;
    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;
    private final MediaServices mediaServices;


    public NewsResponse createNews(NewsRequest newsRequest) {

        if (newsRepository.existsByTitulo(newsRequest.titulo())) {
            throw new RuntimeException("Já existe uma notícia com o título informado");
        }

        UUID admId = UUID.fromString(newsRequest.administradorId());


        Usuario administrador = usuarioRepository.getUsuarioById(admId);

        News news = newsMapper.requestToModel(newsRequest, administrador);

        news = newsRepository.save(news);
        String tokenId = tokenService.generateTokenId(news.getId().toString());

        mediaServices.saveMedia(newsRequest.mediaRequest(), news.getId().toString());

        return newsMapper.modelToResponse(news, tokenId, tokenService.generateTokenId(newsRequest.administradorId()));
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

    @Override
    public News getNewsById(String tokenId) {
        String id = tokenService.decodeToken(tokenId);

        return newsRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("Notícia não encontrada"));
    }

    @Override
    public List<NewsResponse> getAllNews() {

        List<News> newsList = newsRepository.findAll();

        return newsList.stream()
                .map(news -> newsMapper.modelToResponse(news, tokenService.generateTokenId(news.getId().toString()),tokenService.generateTokenId(news.getAdministrador().getId().toString())))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteNewsById(String tokenId) {

        String id = tokenService.decodeToken(tokenId);

        newsRepository.deleteById(UUID.fromString(id));
    }
}
