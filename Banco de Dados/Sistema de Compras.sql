##HEROADN 02/09/2018##
CREATE SCHEMA sistema;
USE sistema;

####################
#TABELA DE CLIENTES#
####################
create table `pessoa`(
	cod integer primary key auto_increment not null,
    nome varchar(30) not null,
    idade integer not null
);

#########################
#   TABELA DE PRODUTOS  #
#########################
create table `produto`(
	cod integer primary key auto_increment,
    nome varchar(30) not null,
    valor integer not null
);

########################
#   TABELA DE COMPRAS  #
########################
create table pessoas_produto(
	cod integer primary key auto_increment not null,
    id_pessoa integer not null,
    id_produto integer not null,
    FOREIGN KEY (id_pessoa )  REFERENCES pessoa(cod),
    FOREIGN KEY (id_produto)  REFERENCES produto(cod)
);

###########################
#    TABELA DE USUARIOS   #
###########################
create table `usuario`(
	id integer primary key auto_increment,
    nome varchar(20) not null,
    senha varchar(32) not null,
    tipo integer not null#0 para USER comum, 1 para ADMIN
);

#USER
INSERT INTO `usuario`(nome, senha, tipo) VALUES("default","123",0);

#ADMIN
INSERT INTO `usuario`(nome, senha, tipo) VALUES("admin","123",1);
