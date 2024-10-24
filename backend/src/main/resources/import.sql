-- Inserindo Marcas na tabela tb_brand
INSERT INTO tb_brand (name, create_date, update_date) VALUES ('Nike', NOW(), NOW());
INSERT INTO tb_brand (name, create_date, update_date) VALUES ('Adidas', NOW(), NOW());
INSERT INTO tb_brand (name, create_date, update_date) VALUES ('Apple', NOW(), NOW());
INSERT INTO tb_brand (name, create_date, update_date) VALUES ('Samsung', NOW(), NOW());
INSERT INTO tb_brand (name, create_date, update_date) VALUES ('Sony', NOW(), NOW());
INSERT INTO tb_brand (name, create_date, update_date) VALUES ('Microsoft', NOW(), NOW());
INSERT INTO tb_brand (name, create_date, update_date) VALUES ('Puma', NOW(), NOW());
INSERT INTO tb_brand (name, create_date, update_date) VALUES ('Asus', NOW(), NOW());
INSERT INTO tb_brand (name, create_date, update_date) VALUES ('Dell', NOW(), NOW());
INSERT INTO tb_brand (name, create_date, update_date) VALUES ('HP', NOW(), NOW());

-- Inserindo cidades na tabela tb_category

INSERT INTO tb_category (name, create_date, update_date) VALUES ('Eletrônicos', NOW(), NOW());
INSERT INTO tb_category (name, create_date, update_date) VALUES ('Roupas', NOW(), NOW());
INSERT INTO tb_category (name, create_date, update_date) VALUES ('Calçados', NOW(), NOW());
INSERT INTO tb_category (name, create_date, update_date) VALUES ('Esportes', NOW(), NOW());
INSERT INTO tb_category (name, create_date, update_date) VALUES ('Computadores', NOW(), NOW());
INSERT INTO tb_category (name, create_date, update_date) VALUES ('Celulares', NOW(), NOW());
INSERT INTO tb_category (name, create_date, update_date) VALUES ('Acessórios', NOW(), NOW());
INSERT INTO tb_category (name, create_date, update_date) VALUES ('Eletrodomésticos', NOW(), NOW());
INSERT INTO tb_category (name, create_date, update_date) VALUES ('Moda', NOW(), NOW());
INSERT INTO tb_category (name, create_date, update_date) VALUES ('Saúde e Beleza', NOW(), NOW());

-- Inserindo cidades na tabela tb_product
INSERT INTO tb_product (name, short_description, description, price, sale, brand_id, create_date, update_date) VALUES ('Nike Air Max', 'Tênis confortável','Tênis da linha Air Max da Nike.', 699.99, 0.1, 1, NOW(), NOW());

INSERT INTO tb_product (name, short_description, description, price, sale, brand_id, create_date, update_date) VALUES ('Adidas Ultraboost', 'Tênis para corrida', 'Tênis de alta performance da Adidas.', 749.99, 0.15, 2, NOW(), NOW());

INSERT INTO tb_product (name, short_description, description, price, sale, brand_id, create_date, update_date) VALUES ('iPhone 13', 'Smartphone Apple', 'Última versão do iPhone com 256GB.', 9999.99, 0.05, 3, NOW(), NOW());

INSERT INTO tb_product (name, short_description, description, price, sale, brand_id, create_date, update_date) VALUES ('Samsung Galaxy S21', 'Smartphone Samsung', 'Top de linha da Samsung com 128GB.', 7999.99, 0.1, 4, NOW(), NOW());

INSERT INTO tb_product (name, short_description, description, price, sale, brand_id, create_date, update_date) VALUES ('Sony PlayStation 5', 'Console de última geração', 'Console PlayStation 5 com controle DualSense.', 4999.99, 0.2, 5, NOW(), NOW());

INSERT INTO tb_product (name, short_description, description, price, sale, brand_id, create_date, update_date) VALUES ('Xbox Series X', 'Console Microsoft', 'Console Xbox Series X com 1TB.', 4999.99, 0.2, 6, NOW(), NOW());

INSERT INTO tb_product (name, short_description, description, price, sale, brand_id, create_date, update_date) VALUES ('Puma Running Shoes', 'Tênis esportivo Puma', 'Tênis de corrida confortável e leve.', 599.99, 0.1, 7, NOW(), NOW());

INSERT INTO tb_product (name, short_description, description, price, sale, brand_id, create_date, update_date) VALUES ('Asus ZenBook', 'Ultrabook Asus', 'Notebook ultrafino com processador Intel i7.', 8499.99, 0.1, 8, NOW(), NOW());

INSERT INTO tb_product (name, short_description, description, price, sale, brand_id, create_date, update_date) VALUES ('Dell XPS 13', 'Notebook Dell', 'Laptop premium com tela InfinityEdge.', 7999.99, 0.1, 9, NOW(), NOW());

INSERT INTO tb_product (name, short_description, description, price, sale, brand_id, create_date, update_date) VALUES ('HP Spectre x360', 'Notebook conversível', 'Notebook 2 em 1 com tela touch.', 8999.99, 0.2, 10, NOW(), NOW());

-- Inserindo cidades na tabela tb_product_category

-- Associando produtos a categorias (Many-to-Many)
INSERT INTO tb_product_category (product_id, category_id) VALUES (1, 2);  -- Nike Air Max -> Roupas
INSERT INTO tb_product_category (product_id, category_id) VALUES (1, 3);  -- Nike Air Max -> Calçados
INSERT INTO tb_product_category (product_id, category_id) VALUES (1, 4);  -- Nike Air Max -> Esportes

INSERT INTO tb_product_category (product_id, category_id) VALUES (2, 2);  -- Adidas Ultraboost -> Roupas
INSERT INTO tb_product_category (product_id, category_id) VALUES (2, 3);  -- Adidas Ultraboost -> Calçados
INSERT INTO tb_product_category (product_id, category_id) VALUES (2, 4);  -- Adidas Ultraboost -> Esportes

INSERT INTO tb_product_category (product_id, category_id) VALUES (3, 1);  -- iPhone 13 -> Eletrônicos
INSERT INTO tb_product_category (product_id, category_id) VALUES (3, 6);  -- iPhone 13 -> Celulares

INSERT INTO tb_product_category (product_id, category_id) VALUES (4, 1);  -- Galaxy S21 -> Eletrônicos
INSERT INTO tb_product_category (product_id, category_id) VALUES (4, 6);  -- Galaxy S21 -> Celulares

INSERT INTO tb_product_category (product_id, category_id) VALUES (5, 1);  -- PlayStation 5 -> Eletrônicos
INSERT INTO tb_product_category (product_id, category_id) VALUES (5, 8);  -- PlayStation 5 -> Eletrodomésticos

INSERT INTO tb_product_category (product_id, category_id) VALUES (6, 1);  -- Xbox Series X -> Eletrônicos
INSERT INTO tb_product_category (product_id, category_id) VALUES (6, 8);  -- Xbox Series X -> Eletrodomésticos

INSERT INTO tb_product_category (product_id, category_id) VALUES (7, 2);  -- Puma Running Shoes -> Roupas
INSERT INTO tb_product_category (product_id, category_id) VALUES (7, 3);  -- Puma Running Shoes -> Calçados
INSERT INTO tb_product_category (product_id, category_id) VALUES (7, 4);  -- Puma Running Shoes -> Esportes

INSERT INTO tb_product_category (product_id, category_id) VALUES (8, 1);  -- Asus ZenBook -> Eletrônicos
INSERT INTO tb_product_category (product_id, category_id) VALUES (8, 5);  -- Asus ZenBook -> Computadores

INSERT INTO tb_product_category (product_id, category_id) VALUES (9, 5);  -- Dell XPS 13 -> Computadores
INSERT INTO tb_product_category (product_id, category_id) VALUES (9, 1);  -- Dell XPS 13 -> Eletrônicos

INSERT INTO tb_product_category (product_id, category_id) VALUES (10, 5); -- HP Spectre x360 -> Computadores
INSERT INTO tb_product_category (product_id, category_id) VALUES (10, 1); -- HP Spectre x360 -> Eletrônicos




-- Inserindo Pessoas na tabela tb_person
INSERT INTO tb_user (name, cpf, email, password, address, cep, logradouro, complemento, bairro, localidade, uf, create_date, update_date, city_id) VALUES ('Tiago Costa', '123.456.789-00', 'csostatiago055@gmail.com', '$2a$10$qKQsYQCzOoLDNAnKpRrcu.nhu6ixbt0sbPoF99Ati8hR2rW143A02', 'Rua das Flores, 123', '12345-678', NOW(), NOW(), 1);

INSERT INTO tb_user (name, cpf, email, password, address, cep, create_date, update_date, city_id) VALUES ('Maria Oliveira', '987.654.321-00', 'maria.oliveira@email.com', '$2a$10$qKQsYQCzOoLDNAnKpRrcu.nhu6ixbt0sbPoF99Ati8hR2rW143A02', 'Av. Paulista, 456', '98765-432', NOW(), NOW(), 2);

INSERT INTO tb_user (name, cpf, email, password, address, cep, create_date, update_date, city_id) VALUES ('Carlos Souza', '111.222.333-44', 'carlos.souza@email.com', '$2a$10$qKQsYQCzOoLDNAnKpRrcu.nhu6ixbt0sbPoF99Ati8hR2rW143A02', 'Rua da Praia, 789', '12345-987', NOW(), NOW(), 3);

INSERT INTO tb_user (name, cpf, email, password, address, cep, create_date, update_date, city_id) VALUES ('Ana Costa', '555.666.777-88', 'ana.costa@email.com', '$2a$10$qKQsYQCzOoLDNAnKpRrcu.nhu6ixbt0sbPoF99Ati8hR2rW143A02', 'Rua Central, 101', '67890-123', NOW(), NOW(), 4);

INSERT INTO tb_user (name, cpf, email, password, address, cep, create_date, update_date, city_id) VALUES ('Paulo Mendes', '999.888.777-66', 'paulo.mendes@email.com', '$2a$10$qKQsYQCzOoLDNAnKpRrcu.nhu6ixbt0sbPoF99Ati8hR2rW143A02', 'Rua dos Pinheiros, 202', '54321-098', NOW(), NOW(), 5);

-- Inserindo Pedido na tabela tb_order
INSERT INTO tb_order(moment, status, client_id) VALUES (NOW(), 1,1);

INSERT INTO tb_order(moment, status, client_id) VALUES (NOW(), 3,2);

INSERT INTO tb_order(moment, status, client_id) VALUES (NOW(), 0,1);

-- Inserindo Item do Pedido na tabela tb_order_item
INSERT INTO tb_order_item(order_id, product_id, quantity, price) VALUES (1, 1, 2, 90.5);

-- Inserindo Pagamentos na tabela tb_payment
INSERT INTO tb_payment (order_id, moment) VALUES (1, TIMESTAMP WITH TIME ZONE '2024-07-25T15:00:00Z');

-- Inserindo Permissões na tabela tb_permission
INSERT INTO tb_permission (name, create_date, update_date) VALUES ('ROLE_USER', NOW(), NOW());
INSERT INTO tb_permission (name, create_date, update_date) VALUES ('ROLE_OPERATOR', NOW(), NOW());
INSERT INTO tb_permission (name, create_date, update_date) VALUES ('ROLE_ADMIN', NOW(), NOW());

-- -- Inserindo Permissões para as Person na tabela tb_person_permission
INSERT INTO tb_person_permission (person_id, permission_id) VALUES (1, 1), (1, 2);

INSERT INTO tb_person_permission (person_id, permission_id) VALUES (2, 1);

INSERT INTO tb_person_permission (person_id, permission_id) VALUES (3, 1), (3, 2);

INSERT INTO tb_person_permission (person_id, permission_id) VALUES (4, 1), (4, 3);

INSERT INTO tb_person_permission (person_id, permission_id) VALUES (5, 1);