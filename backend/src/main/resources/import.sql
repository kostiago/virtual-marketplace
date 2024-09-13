-- Inserindo estados na tabela tb_state
INSERT INTO tb_state (name, acronym, create_date, update_date) VALUES ('São Paulo', 'SP', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), ('Rio de Janeiro', 'RJ', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), ('Minas Gerais', 'MG', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), ('Bahia', 'BA', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), ('Paraná', 'PR', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), ('Santa Catarina', 'SC', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),  ('Rio Grande do Sul', 'RS', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), ('Goiás', 'GO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), ('Pernambuco', 'PE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), ('Ceará', 'CE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), ('Mato Grosso', 'MT', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), ('Mato Grosso do Sul', 'MS', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), ('Espírito Santo', 'ES', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), ('Amazonas', 'AM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), ('Pará', 'PA', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), ('Distrito Federal', 'DF', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), ('Maranhão', 'MA', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), ('Alagoas', 'AL', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), ('Paraíba', 'PB', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), ('Piauí', 'PI', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Inserindo cidades na tabela tb_city
INSERT INTO tb_city (name, create_date, update_date, idstate) VALUES ('São Paulo', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, (SELECT id FROM tb_state WHERE acronym = 'SP')), ('Rio de Janeiro', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, (SELECT id FROM tb_state WHERE acronym = 'RJ')), ('Belo Horizonte', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, (SELECT id FROM tb_state WHERE acronym = 'MG')), ('Salvador', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, (SELECT id FROM tb_state WHERE acronym = 'BA')), ('Curitiba', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, (SELECT id FROM tb_state WHERE acronym = 'PR')), ('Florianópolis', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, (SELECT id FROM tb_state WHERE acronym = 'SC')), ('Porto Alegre', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, (SELECT id FROM tb_state WHERE acronym = 'RS')), ('Goiânia', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, (SELECT id FROM tb_state WHERE acronym = 'GO')), ('Recife', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, (SELECT id FROM tb_state WHERE acronym = 'PE')), ('Fortaleza', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, (SELECT id FROM tb_state WHERE acronym = 'CE')), ('Cuiabá', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, (SELECT id FROM tb_state WHERE acronym = 'MT')), ('Campo Grande', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, (SELECT id FROM tb_state WHERE acronym = 'MS')), ('Vitória', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, (SELECT id FROM tb_state WHERE acronym = 'ES')), ('Manaus', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, (SELECT id FROM tb_state WHERE acronym = 'AM')), ('Belém', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, (SELECT id FROM tb_state WHERE acronym = 'PA')), ('Brasília', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, (SELECT id FROM tb_state WHERE acronym = 'DF')), ('São Luís', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, (SELECT id FROM tb_state WHERE acronym = 'MA')), ('Maceió', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, (SELECT id FROM tb_state WHERE acronym = 'AL')), ('João Pessoa', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, (SELECT id FROM tb_state WHERE acronym = 'PB')), ('Teresina', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, (SELECT id FROM tb_state WHERE acronym = 'PI'));


-- Inserindo cidades na tabela tb_brand

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
-- Associando produtos a categorias (Many-to-Many)
INSERT INTO tb_product_category (tb_product_id, tb_category_id) VALUES (1, 2);  -- Nike Air Max -> Roupas
INSERT INTO tb_product_category (tb_product_id, tb_category_id) VALUES (1, 3);  -- Nike Air Max -> Calçados
INSERT INTO tb_product_category (tb_product_id, tb_category_id) VALUES (1, 4);  -- Nike Air Max -> Esportes

INSERT INTO tb_product_category (tb_product_id, tb_category_id) VALUES (2, 2);  -- Adidas Ultraboost -> Roupas
INSERT INTO tb_product_category (tb_product_id, tb_category_id) VALUES (2, 3);  -- Adidas Ultraboost -> Calçados
INSERT INTO tb_product_category (tb_product_id, tb_category_id) VALUES (2, 4);  -- Adidas Ultraboost -> Esportes

INSERT INTO tb_product_category (tb_product_id, tb_category_id) VALUES (3, 1);  -- iPhone 13 -> Eletrônicos
INSERT INTO tb_product_category (tb_product_id, tb_category_id) VALUES (3, 6);  -- iPhone 13 -> Celulares

INSERT INTO tb_product_category (tb_product_id, tb_category_id) VALUES (4, 1);  -- Galaxy S21 -> Eletrônicos
INSERT INTO tb_product_category (tb_product_id, tb_category_id) VALUES (4, 6);  -- Galaxy S21 -> Celulares

INSERT INTO tb_product_category (tb_product_id, tb_category_id) VALUES (5, 1);  -- PlayStation 5 -> Eletrônicos
INSERT INTO tb_product_category (tb_product_id, tb_category_id) VALUES (5, 8);  -- PlayStation 5 -> Eletrodomésticos

INSERT INTO tb_product_category (tb_product_id, tb_category_id) VALUES (6, 1);  -- Xbox Series X -> Eletrônicos
INSERT INTO tb_product_category (tb_product_id, tb_category_id) VALUES (6, 8);  -- Xbox Series X -> Eletrodomésticos

INSERT INTO tb_product_category (tb_product_id, tb_category_id) VALUES (7, 2);  -- Puma Running Shoes -> Roupas
INSERT INTO tb_product_category (tb_product_id, tb_category_id) VALUES (7, 3);  -- Puma Running Shoes -> Calçados
INSERT INTO tb_product_category (tb_product_id, tb_category_id) VALUES (7, 4);  -- Puma Running Shoes -> Esportes

INSERT INTO tb_product_category (tb_product_id, tb_category_id) VALUES (8, 1);  -- Asus ZenBook -> Eletrônicos
INSERT INTO tb_product_category (tb_product_id, tb_category_id) VALUES (8, 5);  -- Asus ZenBook -> Computadores

INSERT INTO tb_product_category (tb_product_id, tb_category_id) VALUES (9, 5);  -- Dell XPS 13 -> Computadores
INSERT INTO tb_product_category (tb_product_id, tb_category_id) VALUES (9, 1);  -- Dell XPS 13 -> Eletrônicos

INSERT INTO tb_product_category (tb_product_id, tb_category_id) VALUES (10, 5); -- HP Spectre x360 -> Computadores
INSERT INTO tb_product_category (tb_product_id, tb_category_id) VALUES (10, 1); -- HP Spectre x360 -> Eletrônicos

-- Inserindo Pessoas na tabela tb_person
INSERT INTO tb_person (name, cpf, email, password, address, cep, create_date, update_date, city_id) VALUES ('João Silva', '123.456.789-00', 'joao.silva@email.com', 'senha123', 'Rua das Flores, 123', '12345-678', NOW(), NOW(), 1);

INSERT INTO tb_person (name, cpf, email, password, address, cep, create_date, update_date, city_id) VALUES ('Maria Oliveira', '987.654.321-00', 'maria.oliveira@email.com', 'senha456', 'Av. Paulista, 456', '98765-432', NOW(), NOW(), 2);

INSERT INTO tb_person (name, cpf, email, password, address, cep, create_date, update_date, city_id) VALUES ('Carlos Souza', '111.222.333-44', 'carlos.souza@email.com', 'senha789', 'Rua da Praia, 789', '12345-987', NOW(), NOW(), 3);

INSERT INTO tb_person (name, cpf, email, password, address, cep, create_date, update_date, city_id) VALUES ('Ana Costa', '555.666.777-88', 'ana.costa@email.com', 'senhaABC', 'Rua Central, 101', '67890-123', NOW(), NOW(), 4);

INSERT INTO tb_person (name, cpf, email, password, address, cep, create_date, update_date, city_id) VALUES ('Paulo Mendes', '999.888.777-66', 'paulo.mendes@email.com', 'senhaDEF', 'Rua dos Pinheiros, 202', '54321-098', NOW(), NOW(), 5);

