##HEROADN 03/09/2018##
CREATE SCHEMA sistema;
USE sistema;

#######################
#  TABELA DE CLIENTE  #
#######################
create table `cliente`(
	cod integer primary key auto_increment not null,
    nome varchar(30) not null,
    idade integer not null,
	senha varchar(64) not null,
	tipo  integer not null#0 para USER comum, 1 para ADMIN
);

##########################
#   TABELA DE PRODUTOS   #
##########################
create table `produto`(
	cod integer primary key auto_increment,
    nome varchar(30) not null,
    valor integer not null
);

#########################
#   TABELA DE COMPRAS   #
#########################
create table pessoas_produto(
	cod integer primary key auto_increment not null,
    id_cliente integer not null,
    id_produto integer not null,
    FOREIGN KEY (id_cliente )  REFERENCES cliente(cod),
    FOREIGN KEY (id_produto)  REFERENCES  produto(cod)
);

#USER
INSERT INTO `cliente`(nome, idade,senha ,tipo ) VALUES("default","21","a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3",0);#senha 123

#ADMIN
INSERT INTO `cliente`(nome, idade,senha ,tipo ) VALUES("admin"  ,"31","a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3",1);#senha 123

SELECT * FROM `cliente`;
