-- CREATE TABELAS
CREATE TABLE cond_pagamento (
                                id INTEGER PRIMARY KEY,
                                descricao VARCHAR(255) NOT NULL,
                                tenant_id INTEGER,
                                status BOOLEAN,
                                FOREIGN KEY (tenant_id) REFERENCES Tenant(id)
);
CREATE TABLE forma_pagamento (
                                 id INTEGER PRIMARY KEY,
                                 descricao VARCHAR(255) NOT NULL,
                                 tenant_id INTEGER,
                                 status BOOLEAN,
                                 FOREIGN KEY (tenant_id) REFERENCES Tenant(id)
);
CREATE TABLE tipo_frete (
                            id INTEGER PRIMARY KEY,
                            descricao VARCHAR(255) NOT NULL,
                            tenant_id INTEGER,
                            status BOOLEAN,
                            FOREIGN KEY (tenant_id) REFERENCES Tenant(id)
);


ALTER TABLE pedido
DROP COLUMN  tipo_frete;
ALTER TABLE pedido
DROP COLUMN  tipo_venda;
ALTER TABLE pedido
DROP COLUMN  cond_pagamento;

ALTER TABLE pedido
    ADD COLUMN tipo_frete_id INTEGER,
    ADD CONSTRAINT fk_tipo_frete FOREIGN KEY (tipo_frete_id) REFERENCES tipo_frete(id);

ALTER TABLE pedido
    ADD COLUMN forma_pagamento_id INTEGER,
    ADD CONSTRAINT fk_forma_pagamento FOREIGN KEY (forma_pagamento_id) REFERENCES forma_pagamento(id);

ALTER TABLE pedido
    ADD COLUMN cond_pagamento_id INTEGER,
    ADD CONSTRAINT fk_cond_pagamento FOREIGN KEY (cond_pagamento_id) REFERENCES cond_pagamento(id);



ALTER TABLE log_sistema
    ADD COLUMN cond_pagamento_id INTEGER,
    ADD CONSTRAINT fk_cond_pagamento1 FOREIGN KEY (cond_pagamento_id) REFERENCES cond_pagamento(id);

ALTER TABLE log_sistema
    ADD COLUMN forma_pagamento_id INTEGER,
    ADD CONSTRAINT fk_forma_pagamento1 FOREIGN KEY (forma_pagamento_id) REFERENCES forma_pagamento(id);

ALTER TABLE log_sistema
    ADD COLUMN tipo_frete_id INTEGER,
    ADD CONSTRAINT fk_tipo_frete1 FOREIGN KEY (tipo_frete_id) REFERENCES tipo_frete(id);


