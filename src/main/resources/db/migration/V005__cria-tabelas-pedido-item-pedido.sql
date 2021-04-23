create table pedido (
    id bigserial not null,
    subtotal numeric(10, 2) not null,
    taxa_frete numeric(10, 2) not null,
    valor_total numeric(10, 2) not null,
    
    restaurante_id int8 not null,
    usuario_cliente_id int8 not null,
    forma_pagamento_id int8 not null,
    
    endereco_cidade_id int8 not null,
    endereco_cep varchar(9) not null,
    endereco_logradouro varchar(100) not null,
    endereco_numero varchar(20) not null,
    endereco_complemento varchar(60),
    endereco_bairro varchar(60) not null,
    
    status varchar(10) not null,
    data_criacao timestamp(0) not null,
    data_confirmacao timestamp(0),
    data_cancelamento timestamp(0),
    data_entrega timestamp(0),

    primary key (id),

    constraint fk_pedido_restaurante foreign key (restaurante_id) references restaurante (id),
    constraint fk_pedido_usuario_cliente foreign key (usuario_cliente_id) references usuario (id),
    constraint fk_pedido_forma_pagamento foreign key (forma_pagamento_id) references forma_pagamento (id)
);

create table item_pedido (
    id bigserial not null,
    quantidade int4 not null,
    preco_unitario numeric(10, 2) not null,
    preco_total numeric(10, 2) not null,
    observacao varchar(255),
    pedido_id int8 not null, 
    produto_id int8 not null,

    primary key (id),
    constraint uk_item_pedido_produto unique (pedido_id, produto_id),

    constraint fk_item_pedido_pedido foreign key (pedido_id) references pedido (id),
    constraint fk_item_pedido_produto foreign key (produto_id) references produto (id)
);
