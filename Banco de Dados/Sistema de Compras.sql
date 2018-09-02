##HEROADN 02/09/2018##
CREATE SCHEMA sistema;
USE sistema;

####################
#TABELA DE CLIENTES#
####################
create table `pessoas`(
	cod integer primary key auto_increment not null,
    nome varchar(30) not null,
    idade integer not null
);

####################
#TABELA DE PRODUTOS#
####################
create table `coisas`(
	cod integer primary key auto_increment,
    nome varchar(30) not null,
    valor integer not null
);

###################
#TABELA DE COMPRAS#
###################
create table pessoas_coisas(
	cod integer primary key auto_increment not null,
    id_pessoas integer not null,
    id_coisas integer not null,
    FOREIGN KEY (id_pessoas) REFERENCES pessoas(cod),
    FOREIGN KEY (id_coisas)  REFERENCES coisas(cod)
);

###########################
#TABELA DE ADMINISTRADORES#
###########################
create table `admin`(
	id integer primary key auto_increment,
    nome varchar(20) not null,
    senha varchar(32) not null
);

#ADMIN DE TESTE
INSERT INTO `admin`(nome,senha) VALUES("default","123");
SELECT * from `admin`;
