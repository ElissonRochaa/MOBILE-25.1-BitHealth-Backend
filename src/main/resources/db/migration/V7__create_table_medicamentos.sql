CREATE TABLE medicamentos (
          id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
          nome VARCHAR(150) NOT NULL,
          descricao TEXT NOT NULL,
          quantidade INT NOT NULL,
          tipo_medicamento VARCHAR(50) NOT NULL,
          unidade_saude_id UUID NOT NULL,
          FOREIGN KEY (unidade_saude_id) REFERENCES unidades_saude(id) ON DELETE CASCADE
);