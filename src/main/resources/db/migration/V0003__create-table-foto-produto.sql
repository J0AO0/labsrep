CREATE TABLE foto_produto (
    id int PRIMARY KEY AUTO_INCREMENT,
    produto_id INT,
    nome_arquivo VARCHAR(255),
    descricao VARCHAR(255),
    content_type VARCHAR(100),
    tamanho BIGINT,
    CONSTRAINT fk_produto FOREIGN KEY (produto_id) REFERENCES produto(id));