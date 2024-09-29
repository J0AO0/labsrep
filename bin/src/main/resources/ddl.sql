create table categoria (id integer not null auto_increment, nome varchar(255), status bit, tenant_id integer, primary key (id)) engine=InnoDB
create table classepermissao (id integer not null auto_increment, nome varchar(255), primary key (id)) engine=InnoDB
create table cliente (id integer not null auto_increment, email varchar(255), nome varchar(255), status bit, telefone varchar(255), tenant_id integer, primary key (id)) engine=InnoDB
create table empresa (id integer not null auto_increment, bairro varchar(255), cep varchar(255), cidade varchar(255), complemento varchar(255), cpfoucnpj varchar(255), editar bit, email varchar(255), logradouro varchar(255), naturezapessoa varchar(255), nomecontato varchar(255), numero varchar(255), razaosocial varchar(255), status bit, telefone varchar(255), uf varchar(255), valor decimal(19,2), whats varchar(255), tenant_id integer, primary key (id)) engine=InnoDB
create table item (quantidade integer, status bit, pedido_id integer not null, produto_id integer not null, tenant_id integer, primary key (pedido_id, produto_id)) engine=InnoDB
create table pedido (id integer not null auto_increment, cliente_id integer, tenant_id integer, primary key (id)) engine=InnoDB
create table permissao (id integer not null auto_increment, descricao varchar(255), classepermissao_id integer, primary key (id)) engine=InnoDB
create table produto (id integer not null auto_increment, descricao varchar(255), name varchar(255), preco decimal(19,2), qr_code varchar(255), status bit, categoria_id integer, tenant_id integer, primary key (id)) engine=InnoDB
create table tenant (id integer not null auto_increment, descricao varchar(255), primary key (id)) engine=InnoDB
create table usuario (id integer not null auto_increment, email varchar(255), gtenantativo integer, login varchar(255), nome varchar(255), senha varchar(255), status bit, telefone varchar(255), tenantativo integer, primary key (id)) engine=InnoDB
create table usuario_permissao (id_usuario integer not null, id_permissao integer not null) engine=InnoDB
create table usuario_empresa (empresapadrao bit, tenant_id integer, id_empresa integer not null, id_usuario integer not null, primary key (id_empresa, id_usuario)) engine=InnoDB
alter table empresa add constraint UK_ow6ddx531a4abnwv6eah7h3k7 unique (cpfoucnpj)
alter table categoria add constraint FK5grfw6g65w8qbqiajdspkswfh foreign key (tenant_id) references tenant (id)
alter table cliente add constraint FK2cmtkorj5frxna41x37pf0uco foreign key (tenant_id) references tenant (id)
alter table empresa add constraint FKc94y9kg0tj2jd6u0jm6lfwv6h foreign key (tenant_id) references tenant (id)
alter table item add constraint FK5v3x8uwb51v7l9mk41dy2jtku foreign key (pedido_id) references pedido (id)
alter table item add constraint FKoya2x5ip1q2t3s0936vgjiyx9 foreign key (produto_id) references produto (id)
alter table item add constraint FKdrkgx9furxyv09fpsfv4wag4o foreign key (tenant_id) references tenant (id)
alter table pedido add constraint FK30s8j2ktpay6of18lbyqn3632 foreign key (cliente_id) references cliente (id)
alter table pedido add constraint FK180dwib6bmjcexaokknove0mr foreign key (tenant_id) references tenant (id)
alter table permissao add constraint FKdiyasfskbnh46lmx2ywloji0d foreign key (classepermissao_id) references classepermissao (id)
alter table produto add constraint FKopu9jggwnamfv0c8k2ri3kx0a foreign key (categoria_id) references categoria (id)
alter table produto add constraint FK9lpb3f1r5xbcc0fcex0ubs0i6 foreign key (tenant_id) references tenant (id)
alter table usuario_permissao add constraint FKjvcxjnrmdhdv6eti5d7svm5xw foreign key (id_permissao) references permissao (id)
alter table usuario_permissao add constraint FKbo8hww1whbpxq8ancjokhnfds foreign key (id_usuario) references usuario (id)
alter table usuario_empresa add constraint FKkicrvlg5cwo9w2xi83xrq4ver foreign key (id_empresa) references empresa (id)
alter table usuario_empresa add constraint FK62hh8xjf2yjsncl8ti6vxjm7h foreign key (id_usuario) references usuario (id)
create table categoria (id integer not null auto_increment, nome varchar(255), status bit, tenant_id integer, primary key (id)) engine=InnoDB
create table classepermissao (id integer not null auto_increment, nome varchar(255), primary key (id)) engine=InnoDB
create table cliente (id integer not null auto_increment, email varchar(255), nome varchar(255), status bit, telefone varchar(255), tenant_id integer, primary key (id)) engine=InnoDB
create table empresa (id integer not null auto_increment, bairro varchar(255), cep varchar(255), cidade varchar(255), complemento varchar(255), cpfoucnpj varchar(255), editar bit, email varchar(255), logradouro varchar(255), naturezapessoa varchar(255), nomecontato varchar(255), numero varchar(255), razaosocial varchar(255), status bit, telefone varchar(255), uf varchar(255), valor decimal(19,2), whats varchar(255), tenant_id integer, primary key (id)) engine=InnoDB
create table item (quantidade integer, status bit, pedido_id integer not null, produto_id integer not null, tenant_id integer, primary key (pedido_id, produto_id)) engine=InnoDB
create table pedido (id integer not null auto_increment, cliente_id integer, tenant_id integer, primary key (id)) engine=InnoDB
create table permissao (id integer not null auto_increment, descricao varchar(255), classepermissao_id integer, primary key (id)) engine=InnoDB
create table produto (id integer not null auto_increment, descricao varchar(255), name varchar(255), preco decimal(19,2), qr_code varchar(255), status bit, categoria_id integer, tenant_id integer, primary key (id)) engine=InnoDB
create table tenant (id integer not null auto_increment, descricao varchar(255), primary key (id)) engine=InnoDB
create table usuario (id integer not null auto_increment, email varchar(255), gtenantativo integer, login varchar(255), nome varchar(255), senha varchar(255), status bit, telefone varchar(255), tenantativo integer, primary key (id)) engine=InnoDB
create table usuario_permissao (id_usuario integer not null, id_permissao integer not null) engine=InnoDB
create table usuario_empresa (empresapadrao bit, tenant_id integer, id_empresa integer not null, id_usuario integer not null, primary key (id_empresa, id_usuario)) engine=InnoDB
alter table empresa add constraint UK_ow6ddx531a4abnwv6eah7h3k7 unique (cpfoucnpj)
alter table categoria add constraint FK5grfw6g65w8qbqiajdspkswfh foreign key (tenant_id) references tenant (id)
alter table cliente add constraint FK2cmtkorj5frxna41x37pf0uco foreign key (tenant_id) references tenant (id)
alter table empresa add constraint FKc94y9kg0tj2jd6u0jm6lfwv6h foreign key (tenant_id) references tenant (id)
alter table item add constraint FK5v3x8uwb51v7l9mk41dy2jtku foreign key (pedido_id) references pedido (id)
alter table item add constraint FKoya2x5ip1q2t3s0936vgjiyx9 foreign key (produto_id) references produto (id)
alter table item add constraint FKdrkgx9furxyv09fpsfv4wag4o foreign key (tenant_id) references tenant (id)
alter table pedido add constraint FK30s8j2ktpay6of18lbyqn3632 foreign key (cliente_id) references cliente (id)
alter table pedido add constraint FK180dwib6bmjcexaokknove0mr foreign key (tenant_id) references tenant (id)
alter table permissao add constraint FKdiyasfskbnh46lmx2ywloji0d foreign key (classepermissao_id) references classepermissao (id)
alter table produto add constraint FKopu9jggwnamfv0c8k2ri3kx0a foreign key (categoria_id) references categoria (id)
alter table produto add constraint FK9lpb3f1r5xbcc0fcex0ubs0i6 foreign key (tenant_id) references tenant (id)
alter table usuario_permissao add constraint FKjvcxjnrmdhdv6eti5d7svm5xw foreign key (id_permissao) references permissao (id)
alter table usuario_permissao add constraint FKbo8hww1whbpxq8ancjokhnfds foreign key (id_usuario) references usuario (id)
alter table usuario_empresa add constraint FKkicrvlg5cwo9w2xi83xrq4ver foreign key (id_empresa) references empresa (id)
alter table usuario_empresa add constraint FK62hh8xjf2yjsncl8ti6vxjm7h foreign key (id_usuario) references usuario (id)
create table categoria (id integer not null auto_increment, nome varchar(255), status bit, tenant_id integer, primary key (id)) engine=InnoDB
create table classepermissao (id integer not null auto_increment, nome varchar(255), primary key (id)) engine=InnoDB
create table cliente (id integer not null auto_increment, email varchar(255), nome varchar(255), status bit, telefone varchar(255), tenant_id integer, primary key (id)) engine=InnoDB
create table empresa (id integer not null auto_increment, bairro varchar(255), cep varchar(255), cidade varchar(255), complemento varchar(255), cpfoucnpj varchar(255), editar bit, email varchar(255), logradouro varchar(255), naturezapessoa varchar(255), nomecontato varchar(255), numero varchar(255), razaosocial varchar(255), status bit, telefone varchar(255), uf varchar(255), valor decimal(19,2), whats varchar(255), tenant_id integer, primary key (id)) engine=InnoDB
create table item (quantidade integer, status bit, pedido_id integer not null, produto_id integer not null, tenant_id integer, primary key (pedido_id, produto_id)) engine=InnoDB
create table pedido (id integer not null auto_increment, cliente_id integer, tenant_id integer, primary key (id)) engine=InnoDB
create table permissao (id integer not null auto_increment, descricao varchar(255), classepermissao_id integer, primary key (id)) engine=InnoDB
create table produto (id integer not null auto_increment, descricao varchar(255), name varchar(255), preco decimal(19,2), qr_code varchar(255), status bit, categoria_id integer, tenant_id integer, primary key (id)) engine=InnoDB
create table tenant (id integer not null auto_increment, descricao varchar(255), primary key (id)) engine=InnoDB
create table usuario (id integer not null auto_increment, email varchar(255), gtenantativo integer, login varchar(255), nome varchar(255), senha varchar(255), status bit, telefone varchar(255), tenantativo integer, primary key (id)) engine=InnoDB
create table usuario_permissao (id_usuario integer not null, id_permissao integer not null) engine=InnoDB
create table usuario_empresa (empresapadrao bit, tenant_id integer, id_empresa integer not null, id_usuario integer not null, primary key (id_empresa, id_usuario)) engine=InnoDB
alter table empresa add constraint UK_ow6ddx531a4abnwv6eah7h3k7 unique (cpfoucnpj)
alter table categoria add constraint FK5grfw6g65w8qbqiajdspkswfh foreign key (tenant_id) references tenant (id)
alter table cliente add constraint FK2cmtkorj5frxna41x37pf0uco foreign key (tenant_id) references tenant (id)
alter table empresa add constraint FKc94y9kg0tj2jd6u0jm6lfwv6h foreign key (tenant_id) references tenant (id)
alter table item add constraint FK5v3x8uwb51v7l9mk41dy2jtku foreign key (pedido_id) references pedido (id)
alter table item add constraint FKoya2x5ip1q2t3s0936vgjiyx9 foreign key (produto_id) references produto (id)
alter table item add constraint FKdrkgx9furxyv09fpsfv4wag4o foreign key (tenant_id) references tenant (id)
alter table pedido add constraint FK30s8j2ktpay6of18lbyqn3632 foreign key (cliente_id) references cliente (id)
alter table pedido add constraint FK180dwib6bmjcexaokknove0mr foreign key (tenant_id) references tenant (id)
alter table permissao add constraint FKdiyasfskbnh46lmx2ywloji0d foreign key (classepermissao_id) references classepermissao (id)
alter table produto add constraint FKopu9jggwnamfv0c8k2ri3kx0a foreign key (categoria_id) references categoria (id)
alter table produto add constraint FK9lpb3f1r5xbcc0fcex0ubs0i6 foreign key (tenant_id) references tenant (id)
alter table usuario_permissao add constraint FKjvcxjnrmdhdv6eti5d7svm5xw foreign key (id_permissao) references permissao (id)
alter table usuario_permissao add constraint FKbo8hww1whbpxq8ancjokhnfds foreign key (id_usuario) references usuario (id)
alter table usuario_empresa add constraint FKkicrvlg5cwo9w2xi83xrq4ver foreign key (id_empresa) references empresa (id)
alter table usuario_empresa add constraint FK62hh8xjf2yjsncl8ti6vxjm7h foreign key (id_usuario) references usuario (id)
create table categoria (id integer not null auto_increment, nome varchar(255), status bit, tenant_id integer, primary key (id)) engine=InnoDB
create table classepermissao (id integer not null auto_increment, nome varchar(255), primary key (id)) engine=InnoDB
create table cliente (id integer not null auto_increment, email varchar(255), nome varchar(255), status bit, telefone varchar(255), tenant_id integer, primary key (id)) engine=InnoDB
create table empresa (id integer not null auto_increment, bairro varchar(255), cep varchar(255), cidade varchar(255), complemento varchar(255), cpfoucnpj varchar(255), editar bit, email varchar(255), logradouro varchar(255), naturezapessoa varchar(255), nomecontato varchar(255), numero varchar(255), razaosocial varchar(255), status bit, telefone varchar(255), uf varchar(255), valor decimal(19,2), whats varchar(255), tenant_id integer, primary key (id)) engine=InnoDB
create table item (quantidade integer, status bit, pedido_id integer not null, produto_id integer not null, tenant_id integer, primary key (pedido_id, produto_id)) engine=InnoDB
create table pedido (id integer not null auto_increment, cliente_id integer, tenant_id integer, primary key (id)) engine=InnoDB
create table permissao (id integer not null auto_increment, descricao varchar(255), classepermissao_id integer, primary key (id)) engine=InnoDB
create table produto (id integer not null auto_increment, descricao varchar(255), name varchar(255), preco decimal(19,2), qr_code varchar(255), status bit, categoria_id integer, tenant_id integer, primary key (id)) engine=InnoDB
create table tenant (id integer not null auto_increment, descricao varchar(255), primary key (id)) engine=InnoDB
create table usuario (id integer not null auto_increment, email varchar(255), gtenantativo integer, login varchar(255), nome varchar(255), senha varchar(255), status bit, telefone varchar(255), tenantativo integer, primary key (id)) engine=InnoDB
create table usuario_permissao (id_usuario integer not null, id_permissao integer not null) engine=InnoDB
create table usuario_empresa (empresapadrao bit, tenant_id integer, id_empresa integer not null, id_usuario integer not null, primary key (id_empresa, id_usuario)) engine=InnoDB
alter table empresa add constraint UK_ow6ddx531a4abnwv6eah7h3k7 unique (cpfoucnpj)
alter table categoria add constraint FK5grfw6g65w8qbqiajdspkswfh foreign key (tenant_id) references tenant (id)
alter table cliente add constraint FK2cmtkorj5frxna41x37pf0uco foreign key (tenant_id) references tenant (id)
alter table empresa add constraint FKc94y9kg0tj2jd6u0jm6lfwv6h foreign key (tenant_id) references tenant (id)
alter table item add constraint FK5v3x8uwb51v7l9mk41dy2jtku foreign key (pedido_id) references pedido (id)
alter table item add constraint FKoya2x5ip1q2t3s0936vgjiyx9 foreign key (produto_id) references produto (id)
alter table item add constraint FKdrkgx9furxyv09fpsfv4wag4o foreign key (tenant_id) references tenant (id)
alter table pedido add constraint FK30s8j2ktpay6of18lbyqn3632 foreign key (cliente_id) references cliente (id)
alter table pedido add constraint FK180dwib6bmjcexaokknove0mr foreign key (tenant_id) references tenant (id)
alter table permissao add constraint FKdiyasfskbnh46lmx2ywloji0d foreign key (classepermissao_id) references classepermissao (id)
alter table produto add constraint FKopu9jggwnamfv0c8k2ri3kx0a foreign key (categoria_id) references categoria (id)
alter table produto add constraint FK9lpb3f1r5xbcc0fcex0ubs0i6 foreign key (tenant_id) references tenant (id)
alter table usuario_permissao add constraint FKjvcxjnrmdhdv6eti5d7svm5xw foreign key (id_permissao) references permissao (id)
alter table usuario_permissao add constraint FKbo8hww1whbpxq8ancjokhnfds foreign key (id_usuario) references usuario (id)
alter table usuario_empresa add constraint FKkicrvlg5cwo9w2xi83xrq4ver foreign key (id_empresa) references empresa (id)
alter table usuario_empresa add constraint FK62hh8xjf2yjsncl8ti6vxjm7h foreign key (id_usuario) references usuario (id)
