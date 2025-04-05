package br.com.bitwise.bithealth.modules.news.media.domain;

import br.com.bitwise.bithealth.modules.news.model.News;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "media")
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "imagem", nullable = false)
    private String imagem;

    @ManyToOne
    @JoinColumn(name = "noticia_id", nullable = false)
    private News noticia;

    public Media(String imagem, News noticia) {
        this.imagem = imagem;
        this.noticia = noticia;
    }
}
