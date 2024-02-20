CREATE TABLE IF NOT EXISTS clientes (
                                        uuid VARCHAR(36) PRIMARY KEY,
                                        nome VARCHAR(255) NOT NULL,
                                        cpf VARCHAR(14) NOT NULL,
                                        email VARCHAR(255) NOT NULL
);

INSERT INTO clientes (uuid, nome, cpf, email) VALUES
                                                  ('123e4567-e89b-12d3-a456-426614174000', 'Cliente 1', '88256176660', 'cliente1@email.com'),
                                                  ('123e4567-e89b-12d3-a456-426614174001', 'Cliente 2', '87223666447', 'cliente2@email.com'),
                                                  ('123e4567-e89b-12d3-a456-426614174002', 'Cliente 3', '32267558386', 'cliente3@email.com'),
                                                  ('123e4567-e89b-12d3-a456-426614174003', 'Cliente 4', '52846686858', 'cliente4@email.com'),
                                                  ('123e4567-e89b-12d3-a456-426614174004', 'Cliente 5', '28482722956', 'cliente5@email.com');

CREATE TABLE IF NOT EXISTS produtos (
                                        uuid VARCHAR(36) PRIMARY KEY,
                                        nome VARCHAR(255) NOT NULL,
                                        valor NUMERIC(10, 2) NOT NULL,
                                        descricao TEXT NOT NULL,
                                        categoria VARCHAR(255) NOT NULL,
                                        quantidade INT NOT NULL
);

INSERT INTO produtos (uuid, nome, valor, descricao, categoria, quantidade) VALUES
                                                                               ('123e4567-e89b-12d3-a456-426614174005', 'Produto 1', 10, 'Descricao 1', 'LANCHE', 100),
                                                                               ('123e4567-e89b-12d3-a456-426614174006', 'Produto 2', 20, 'Descricao 2', 'BEBIDA', 100),
                                                                               ('123e4567-e89b-12d3-a456-426614174007', 'Produto 3', 30, 'Descricao 3', 'ACOMPANHAMENTO', 100),
                                                                               ('123e4567-e89b-12d3-a456-426614174008', 'Produto 4', 40, 'Descricao 4', 'SOBREMESA', 100),
                                                                               ('123e4567-e89b-12d3-a456-426614174009', 'Produto 5', 50, 'Descricao 5', 'SOBREMESA', 100);

CREATE TABLE IF NOT EXISTS pedidos (
                                       uuid VARCHAR(36) PRIMARY KEY,
                                       numeroPedido BIGINT,
                                       clienteid VARCHAR(36) NOT NULL,
                                       dataCriacao TIMESTAMP NOT NULL DEFAULT NOW(),
                                       statusPedido VARCHAR(255) NOT NULL,
                                       statusPagamento VARCHAR(255) NOT NULL,
                                       valorTotal NUMERIC(10, 2) NOT NULL,
                                       FOREIGN KEY (clienteid) REFERENCES clientes(uuid)
);

INSERT INTO pedidos (uuid, numeroPedido, clienteid, statusPedido, statusPagamento, valorTotal, dataCriacao) VALUES
                                                                                                                ('123e4567-e89b-12d3-a456-426614174010', 1, '123e4567-e89b-12d3-a456-426614174000', 'PRONTO', 'PAGO', 10, '2022-12-21 00:00:00'),
                                                                                                                ('123e4567-e89b-12d3-a456-426614174011', 2, '123e4567-e89b-12d3-a456-426614174001', 'EM_PREPARACAO', 'PAGO', 20, '2022-12-21 00:00:00'),
                                                                                                                ('123e4567-e89b-12d3-a456-426614174012', 3, '123e4567-e89b-12d3-a456-426614174002', 'RECEBIDO', 'PAGO', 30, '2022-12-21 00:00:00'),
                                                                                                                ('123e4567-e89b-12d3-a456-426614174013', 4, '123e4567-e89b-12d3-a456-426614174003', 'EM_PREPARACAO', 'PAGO', 40, '2022-12-21 00:00:00'),
                                                                                                                ('123e4567-e89b-12d3-a456-426614174014', 5, '123e4567-e89b-12d3-a456-426614174004', 'FINALIZADO', 'PAGO', 50, '2022-12-21 00:00:00');

CREATE TABLE IF NOT EXISTS pedido_produtos (
                                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                               pedido_uuid VARCHAR(36) NOT NULL,
                                               produto_uuid VARCHAR(36) NOT NULL,
                                               valor NUMERIC(10, 2) NOT NULL,
                                               quantidade INT NOT NULL,
                                               categoria VARCHAR(255) NOT NULL,
                                               numeroPedido BIGINT,
                                               FOREIGN KEY (pedido_uuid) REFERENCES pedidos(uuid),
                                               FOREIGN KEY (produto_uuid) REFERENCES produtos(uuid)
);

INSERT INTO pedido_produtos (pedido_uuid, produto_uuid, valor, quantidade, categoria, numeroPedido) VALUES
                                                                                                        ('123e4567-e89b-12d3-a456-426614174010', '123e4567-e89b-12d3-a456-426614174005', 10, 1, 'LANCHE', 1),
                                                                                                        ('123e4567-e89b-12d3-a456-426614174011', '123e4567-e89b-12d3-a456-426614174006', 20, 1, 'BEBIDA', 2),
                                                                                                        ('123e4567-e89b-12d3-a456-426614174012', '123e4567-e89b-12d3-a456-426614174007', 30, 1, 'ACOMPANHAMENTO', 3),
                                                                                                        ('123e4567-e89b-12d3-a456-426614174013', '123e4567-e89b-12d3-a456-426614174008', 40, 1, 'SOBREMESA', 4),
                                                                                                        ('123e4567-e89b-12d3-a456-426614174014', '123e4567-e89b-12d3-a456-426614174009', 50, 1, 'SOBREMESA', 5);