CREATE SCHEMA sistema;
USE sistema;

create table `pessoas`(
	cod integer primary key auto_increment,
    nome varchar(30),
    idade integer
);

create table `coisas`(
	cod integer primary key auto_increment,
    nome varchar(30),
    valor integer
);

create table `admin`(
	id integer primary key auto_increment,
    nome varchar(20) not null,
    senha varchar(32) not null
);

SELECT * from pessoas;

INSERT INTO `admin`(nome,senha) VALUES("default","123");