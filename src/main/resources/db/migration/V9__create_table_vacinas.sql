CREATE TABLE vacinas (
                                      id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                      vacina VARCHAR(100) NOT NULL,
                                      idade VARCHAR(50) NOT NULL,
                                      doses VARCHAR(50) NOT NULL,
                                      doencas_evitadas TEXT NOT NULL,
                                      faixa_etaria VARCHAR(50) NOT NULL
);