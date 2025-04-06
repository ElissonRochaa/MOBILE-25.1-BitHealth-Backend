CREATE TABLE noticias (
          id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
          titulo VARCHAR(255) NOT NULL,
          conteudo TEXT NOT NULL,
          data_publicacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
          administrador_id UUID NOT NULL,
          FOREIGN KEY (administrador_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

CREATE TABLE media (
           id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
           imagem TEXT NOT NULL,
           noticia_id UUID NOT NULL UNIQUE,
           FOREIGN KEY (noticia_id) REFERENCES noticias(id) ON DELETE CASCADE
);