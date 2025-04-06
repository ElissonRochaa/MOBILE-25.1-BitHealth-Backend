CREATE TABLE unidades_saude (
                id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                nome VARCHAR(150) NOT NULL,
                tipo VARCHAR(50) NOT NULL,
                horario_inicio_atendimento VARCHAR(5) NOT NULL,
                horario_fim_atendimento VARCHAR(5) NOT NULL,
                criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE enderecos_unidades (
                id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                unidade_id UUID NOT NULL,
                logradouro VARCHAR(255) NOT NULL,
                numero VARCHAR(10) NOT NULL,
                complemento VARCHAR(100) NULL,
                bairro VARCHAR(100) NOT NULL,
                cidade VARCHAR(100) NOT NULL,
                estado VARCHAR(2) NOT NULL,
                latitude DECIMAL(9,6) NULL,
                longitude DECIMAL(9,6) NULL,
                cep VARCHAR(10) NOT NULL,
                FOREIGN KEY (unidade_id) REFERENCES unidades_saude(id) ON DELETE CASCADE
);

CREATE TABLE telefones_unidades (
                id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                unidade_id UUID NOT NULL,
                numero VARCHAR(20) NOT NULL,
                tipo VARCHAR(50) NOT NULL,
                FOREIGN KEY (unidade_id) REFERENCES unidades_saude(id) ON DELETE CASCADE
);