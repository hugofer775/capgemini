CREATE TABLE pix(
	id SERIAL PRIMARY KEY NOT NULL,
	nome_destinatario VARCHAR(50) NOT NULL,
	cpf VARCHAR(11) NOT NULL,
	instituicao_bancaria VARCHAR(50) NOT NULL,
	chave_pix VARCHAR(50) NOT NULL,
	valor NUMERIC(12,2) NOT NULL,
	data_pagamento TIMESTAMP NOT NULL,
	descricao VARCHAR(255) NOT NULL,
	cliente_id int REFERENCES cliente(id)
);

CREATE TABLE cliente(
	id SERIAL PRIMARY KEY NOT NULL,
	nome VARCHAR(50) NOT NULL,
	cpf VARCHAR(11) NOT NULL
);