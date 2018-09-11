##HEROADN 03/09/2018##
CREATE SCHEMA sistema;
USE sistema;

#######################
#  TABELA DE CLIENTE  #
#######################
create table `cliente`(
    id    integer primary key auto_increment not null,
    nome  varchar(30) not null,
    idade integer(4) not null,
    senha varchar(64) not null,
    saldo double,
    tipo  int(1) not null#0 para USER comum, 1 para ADMIN
);

##########################
#   TABELA DE PRODUTOS   #
##########################
create table `produto`(
    id integer primary key auto_increment,
    nome varchar(30) not null,
    valor integer not null
);

#########################
#   TABELA DE COMPRAS   #
#########################
create table compra(
    id integer primary key auto_increment not null,
    id_cliente integer not null,
    id_produto integer not null,
    FOREIGN KEY (id_cliente )  REFERENCES cliente(id),
    FOREIGN KEY (id_produto)  REFERENCES  produto(id)
);

#USER COMUN
INSERT INTO `cliente`(nome, idade,senha ,tipo, saldo ) 
	VALUES("default","21","a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3",0, 2000);#senha 123

#ADMIN
INSERT INTO `cliente`(nome, idade,senha ,tipo) 
	VALUES("admin"  ,"31","a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3",1);#senha 123

#Produtos Iniciais
INSERT INTO `produto`(nome, valor) 
	VALUES("Melancia"  ,"10.0");
    
#Produtos Iniciais
INSERT INTO `produto`(nome, valor) 
	VALUES("Banana"  ,"100");
	
SELECT * FROM `cliente`;
SELECT * FROM `produto`;

#Mostrar as compras pelo nome
SELECT produto.* FROM `compra`,`produto`,`cliente` 
	where compra.id_produto = produto.id and compra.id_cliente = cliente.id and cliente.nome like 'default';
