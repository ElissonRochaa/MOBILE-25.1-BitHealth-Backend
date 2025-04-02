package br.com.bitwise.bithealth.modules.news.controller;

import br.com.bitwise.bithealth.modules.news.dto.MessageResponse;
import br.com.bitwise.bithealth.modules.news.dto.NewsRequest;
import br.com.bitwise.bithealth.modules.news.dto.NewsResponse;
import br.com.bitwise.bithealth.modules.news.services.NewsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/news")
@RequiredArgsConstructor
@Tag(name = "Notícias")
public class NewsController {

    private final NewsService newsService;

    @GetMapping("/")
    @SecurityRequirement(name = "JWTAuth")
    @PreAuthorize("hasRole('ADMINISTRADOR') or hasRole('CIDADAO')")
    public ResponseEntity<List<NewsResponse>> getAllNews() {
        List<NewsResponse> newsList = newsService.getAllNews();
        return ResponseEntity.ok().body(newsList);
    }

    @PostMapping("/")
    @SecurityRequirement(name = "JWTAuth")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<NewsResponse> createNews(@RequestBody @Valid NewsRequest newsRequest, HttpServletRequest request) {
        // Passa o HttpServletRequest para o serviço
        NewsResponse newsResponse = newsService.createNews(newsRequest, request);
        URI uri = URI.create("/news/" + newsResponse.tokenId());
        return ResponseEntity.created(uri).body(newsResponse);
    }

    @DeleteMapping("/{tokenId}")
    @SecurityRequirement(name = "JWTAuth")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<MessageResponse> deleteNewsById(@PathVariable String tokenId) {
        newsService.deleteNewsById(tokenId);
        return ResponseEntity.ok().body(new MessageResponse("Notícia deletada com sucesso"));
    }
}
