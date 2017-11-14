CREATE TABLE TCONTA (
	codigoConta BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome varchar(90) NOT NULL,
	titular varchar(150) not null,
	agencia int not null,
	digitoAgencia int not null,
	conta int not null,
	digitoConta int not null,
	saldo DECIMAL(14,2) NOT NULL	
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
