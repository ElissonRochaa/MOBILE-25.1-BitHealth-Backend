CREATE TABLE calendario_vacinacao (
              id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
              vacina VARCHAR(150) NOT NULL,
              idade_minima INT NOT NULL,
              idade_maxima INT NOT NULL,
              descricao TEXT NOT NULL,
              data_inicio DATE NOT NULL,
              data_fim DATE NOT NULL,
              status VARCHAR(50) NOT NULL
);