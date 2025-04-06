CREATE TABLE servicos_saude (
            id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
            nome VARCHAR(150) NOT NULL,
            descricao TEXT NOT NULL,
            horario_inicio VARCHAR(5) NOT NULL,
            horario_fim VARCHAR(5) NOT NULL,
            unidade_saude_id UUID NOT NULL,
            FOREIGN KEY (unidade_saude_id) REFERENCES unidades_saude(id) ON DELETE CASCADE
);