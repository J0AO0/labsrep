ALTER TABLE usuario_empresa
    MODIFY COLUMN empresapadrao INT;

ALTER TABLE usuario
DROP COLUMN login;

ALTER TABLE usuario
DROP COLUMN telefone;