Create table pacientes (
    id serial primary key,
    nome varchar(255) not null,
    email varchar(100) not null unique,
    cpf varchar(11) not null,
    status boolean default true,
    logradouro VARCHAR(100) NOT NULL,
    bairro VARCHAR(100) NOT NULL,
    cep VARCHAR(9) NOT NULL,
    complemento VARCHAR(100),
    numero VARCHAR(20),
    uf CHAR(2) NOT NULL,
    cidade VARCHAR(100) NOT NULL
);