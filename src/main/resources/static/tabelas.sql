create database erp_teste;
use erp_teste;

create table if not exists estados
(
    id    int auto_increment primary key,
    nome  varchar(100) not null,
    sigla char(2)      not null unique
);

insert into estados (nome, sigla)
values ('Acre', 'AC'),
       ('Alagoas', 'AL'),
       ('Amapá', 'AP'),
       ('Amazonas', 'AM'),
       ('Bahia', 'BA'),
       ('Ceará', 'CE'),
       ('Distrito Federal', 'DF'),
       ('Espírito Santo', 'ES'),
       ('Goiás', 'GO'),
       ('Maranhão', 'MA'),
       ('Mato Grosso', 'MT'),
       ('Mato Grosso do Sul', 'MS'),
       ('Minas Gerais', 'MG'),
       ('Pará', 'PA'),
       ('Paraíba', 'PB'),
       ('Paraná', 'PR'),
       ('Pernambuco', 'PE'),
       ('Piauí', 'PI'),
       ('Rio de Janeiro', 'RJ'),
       ('Rio Grande do Norte', 'RN'),
       ('Rio Grande do Sul', 'RS'),
       ('Rondônia', 'RO'),
       ('Roraima', 'RR'),
       ('Santa Catarina', 'SC'),
       ('São Paulo', 'SP'),
       ('Sergipe', 'SE'),
       ('Tocantins', 'TO');

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