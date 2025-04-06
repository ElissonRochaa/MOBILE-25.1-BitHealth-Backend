package br.com.bitwise.bithealth.modules.news.model;

import br.com.bitwise.bithealth.modules.user.model.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "noticias")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "titulo", nullable = false, length = 255)
    private String titulo;

    @Column(name = "conteudo", nullable = false, columnDefinition = "TEXT")
    private String conteudo;

    @Column(name = "data_publicacao", nullable = false, updatable = false)
    private LocalDateTime dataPublicacao;

    @ManyToOne
    @JoinColumn(name = "administrador_id",nullable = false)
    private Usuario administrador;

    @PrePersist
    protected void onCreate() {
        dataPublicacao = LocalDateTime.now();
    }

    public News (String titulo, String conteudo, Usuario administrador) {
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.administrador = administrador;
    }


}
