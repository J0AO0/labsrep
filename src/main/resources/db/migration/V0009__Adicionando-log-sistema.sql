ALTER TABLE log_sistema ADD COLUMN tipo_pedido_id INT;


ALTER TABLE log_sistema 
ADD CONSTRAINT fk_tipo_pedido
FOREIGN KEY (tipo_pedido_id) REFERENCES tipo_pedido(id);
