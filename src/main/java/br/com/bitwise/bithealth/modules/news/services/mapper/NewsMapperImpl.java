package br.com.bitwise.bithealth.modules.news.services.mapper;

import br.com.bitwise.bithealth.modules.news.dto.NewsRequest;
import br.com.bitwise.bithealth.modules.news.dto.NewsResponse;
import br.com.bitwise.bithealth.modules.news.model.News;
import br.com.bitwise.bithealth.modules.news.repository.NewsRepository;
import br.com.bitwise.bithealth.modules.user.model.ENUM.TipoUsuario;
import br.com.bitwise.bithealth.modules.user.model.Usuario;
import br.com.bitwise.bithealth.modules.user.repository.UsuarioRepository;
import br.com.bitwise.bithealth.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class NewsMapperImpl implements NewsMapper {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    @Override
    public News requestToModel(NewsRequest newsRequest, Usuario administrador) {

        if(administrador.getTipoUsuario() != TipoUsuario.ADMINISTRADOR){
            throw new RuntimeException("Usuario não é administrador");
        }

        return new News(
                newsRequest.titulo(),
                newsRequest.conteudo(),
                administrador
        );
    }

    @Override
    public NewsResponse modelToResponse(News news, String tokenId) {
        return new NewsResponse(
                tokenId,
                news.getTitulo(),
                news.getConteudo(),
                news.getDataPublicacao(),
                tokenId
        );
    }
}
