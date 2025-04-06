CREATE TABLE medicos (
             id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
             nome VARCHAR(150) NOT NULL,
             crm VARCHAR(20) UNIQUE NOT NULL,
             especialidade VARCHAR(100) NOT NULL,
             unidade_saude_id UUID NOT NULL,
             data_plantao VARCHAR(50) NOT NULL,
             horario_inicio VARCHAR(5) NOT NULL,
             horario_fim VARCHAR(5) NOT NULL,
             tipo VARCHAR(50) NOT NULL,
             criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
             FOREIGN KEY (unidade_saude_id) REFERENCES unidades_saude(id) ON DELETE CASCADE
);