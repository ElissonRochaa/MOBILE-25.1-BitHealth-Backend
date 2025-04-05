package br.com.bitwise.bithealth.modules.news.media.repository;

import br.com.bitwise.bithealth.modules.news.media.domain.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MediaRepository extends JpaRepository<Media, UUID> {
    Media findAllByNoticiaId(UUID noticiaId);
}
