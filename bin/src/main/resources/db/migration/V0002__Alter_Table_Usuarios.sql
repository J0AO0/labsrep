ALTER TABLE usuario ADD COLUMN tenant_id INTEGER;
alter table pedido add constraint FK180dwib6bmjcexaokknove0rt foreign key (tenant_id) references tenant (id);