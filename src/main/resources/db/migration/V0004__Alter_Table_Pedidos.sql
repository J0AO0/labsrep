ALTER TABLE pedido
ADD COLUMN tipo_frete VARCHAR(255),
ADD COLUMN tipo_venda VARCHAR(255),
ADD COLUMN valor_frete DECIMAL(10,2),
ADD COLUMN cond_pagamento VARCHAR(255),
ADD COLUMN comissao DECIMAL(10,2);
