create database erp_teste;
use erp_teste;

create table if not exists clientes
(
    id             int auto_increment primary key,
    is_cpf         boolean      not null,
    nome           varchar(100) not null,
    cpf_or_cnpj    varchar(20)  not null,
    numero_contato varchar(15),
    cep            varchar(10),
    endereco       varchar(255),
    bairro         varchar(100),
    cidade         varchar(100),
    uf             char(2),
    numero_casa    int

);