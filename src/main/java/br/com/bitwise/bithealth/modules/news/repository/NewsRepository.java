package br.com.bitwise.bithealth.modules.news.repository;

import br.com.bitwise.bithealth.modules.news.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NewsRepository extends JpaRepository<News, UUID> {

    boolean existsByTitulo(String titulo);

}
