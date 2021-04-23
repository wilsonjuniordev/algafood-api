create table forma_pagamento (
    id  bigserial not null,
    descricao varchar(60) not null,

    primary key (id)
);

create table grupo (
    id  bigserial not null,
    nome varchar(60) not null,

    primary key (id)
);

create table grupo_permissao (
    grupo_id int8 not null,
    permissao_id int8 not null,

    primary key (grupo_id, permissao_id)
);

create table permissao (
    id  bigserial not null,
    descricao varchar(60) not null,
    nome varchar(100) not null,

    primary key (id)
);

create table produto (
    id  bigserial not null,
    restaurante_id int8 not null, 
    nome varchar(80) not null,
    descricao text not null,
    preco numeric(10, 2) not null,
    ativo boolean not null,

    primary key (id)
);

create table restaurante (
    id  bigserial not null,
    cozinha_id int8 not null,
    nome varchar(80) not null,
    taxa_frete numeric(10, 2) not null,
    data_atualizacao timestamp(0) not null,
    data_cadastro timestamp(0) not null,

    endereco_cidade_id int8,
    endereco_cep varchar(9),
    endereco_logradouro varchar(100),
    endereco_numero varchar(20),
    endereco_complemento varchar(60),
    endereco_bairro varchar(60),
    
    primary key (id)
);

create table restaurante_forma_pagamento (
    restaurante_id int8 not null,
    forma_pagamento_id int8 not null,

    primary key (restaurante_id, forma_pagamento_id)
);

create table usuario (
    id  bigserial not null,
    nome varchar(80) not null,
    email varchar(255) not null,
    senha varchar(255) not null,
    data_cadastro timestamp(0) not null,

    primary key (id)
);

create table usuario_grupo (
    usuario_id int8 not null,
    grupo_id int8 not null,

    primary key (usuario_id, grupo_id)
);

alter table if exists grupo_permissao add constraint fk_grupo_permissao_permissao foreign key (permissao_id) references permissao (id);

alter table if exists grupo_permissao add constraint fk_grupo_permissao_grupo foreign key (grupo_id) references grupo (id);

alter table if exists produto add constraint fk_produto_restaurante foreign key (restaurante_id) references restaurante (id);

alter table if exists restaurante add constraint fk_restaurante_cozinha foreign key (cozinha_id) references cozinha (id);

alter table if exists restaurante add constraint fk_restaurante_cidade foreign key (endereco_cidade_id) references cidade (id);

alter table if exists restaurante_forma_pagamento add constraint fk_rest_forma_pagto_forma_pagto foreign key (forma_pagamento_id) references forma_pagamento (id);

alter table if exists restaurante_forma_pagamento add constraint fk_rest_forma_pagto_restaurante foreign key (restaurante_id) references restaurante (id);

alter table if exists usuario_grupo add constraint fk_usuario_grupo_grupo foreign key (grupo_id) references grupo (id);

alter table if exists usuario_grupo add constraint fk_usuario_grupo_usuario foreign key (usuario_id) references usuario (id);