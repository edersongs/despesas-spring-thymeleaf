ALTER TABLE ttipodespesa AUTO_INCREMENT = 1;
ALTER TABLE tcategoriadespesa AUTO_INCREMENT = 1;

insert into ttipodespesa values(1,'Habitação');
insert into ttipodespesa values(2,'Transporte');
insert into ttipodespesa values(3,'Educação');
insert into ttipodespesa values(4,'Saúde');
insert into ttipodespesa values(5,'Pessoal');
insert into ttipodespesa values(6,'Diversos');
insert into ttipodespesa values(7,'Lazer');
insert into ttipodespesa values(8,'Serviços');

insert into tcategoriadespesa values(1,'Aluguel/Prestação', '', 1);
insert into tcategoriadespesa values(2,'Condomínio', '', 1);
insert into tcategoriadespesa values(3,'Energia', '', 1);
insert into tcategoriadespesa values(4,'Água', '', 1);
insert into tcategoriadespesa values(5,'Gás', '', 1);
insert into tcategoriadespesa values(6,'Telefone Fixo', '', 1);
insert into tcategoriadespesa values(7,'Celuar', '', 1);
insert into tcategoriadespesa values(8,'Internet', '', 1);
insert into tcategoriadespesa values(9,'TV por assinatura', '', 1);
insert into tcategoriadespesa values(10,'Pacote TV / Internet / Fone', '', 1);
insert into tcategoriadespesa values(11,'Supermercado', '', 1);
insert into tcategoriadespesa values(12,'Feira', '', 1);
insert into tcategoriadespesa values(13,'Sacolão', '', 1);
insert into tcategoriadespesa values(14,'Outros', '', 1);

insert into tcategoriadespesa values(15,'Prestação', '', 2);
insert into tcategoriadespesa values(16,'IPVA / Seguros Obrigatórios ', '', 2);
insert into tcategoriadespesa values(17,'Seguro', '', 2);
insert into tcategoriadespesa values(18,'Manutenção', '', 2);
insert into tcategoriadespesa values(19,'Multas', '', 2);
insert into tcategoriadespesa values(20,'Combustível', '', 2);
insert into tcategoriadespesa values(21,'Ônibus', '', 2);
insert into tcategoriadespesa values(22,'Moto Táxi / Táxi / Uber ', '', 2);
insert into tcategoriadespesa values(23,'Outros', '', 2);

insert into tcategoriadespesa values(24,'Escola / Faculdade', '', 3);
insert into tcategoriadespesa values(25,'Cursos', '', 3);
insert into tcategoriadespesa values(26,'Material Escolar', '', 3);
insert into tcategoriadespesa values(27,'Uniformes', '', 3);
insert into tcategoriadespesa values(28,'Outros', '', 3);

insert into tcategoriadespesa values(29,'Medicamentos', '', 4);
insert into tcategoriadespesa values(30,'Dentista', '', 4);
insert into tcategoriadespesa values(31,'Médicos', '', 4);
insert into tcategoriadespesa values(32,'Consulta', '', 4);
insert into tcategoriadespesa values(33,'Exames', '', 4);
insert into tcategoriadespesa values(34,'Internação', '', 4);
insert into tcategoriadespesa values(35,'Outros', '', 4);

insert into tcategoriadespesa values(36,'Academia', '', 5);
insert into tcategoriadespesa values(37,'Higiene Pessoal (unhas, depilação etc...)', '', 5);
insert into tcategoriadespesa values(38,'Cosméticos', '', 5);
insert into tcategoriadespesa values(39,'Cabelereiro', '', 5);
insert into tcategoriadespesa values(40,'Vestuário', '', 5);
insert into tcategoriadespesa values(41,'Esporte', '', 5);
insert into tcategoriadespesa values(42,'Outros', '', 5);

insert into tcategoriadespesa values(43,'Padaria', '', 6);
insert into tcategoriadespesa values(44,'Lanchonete', '', 6);
insert into tcategoriadespesa values(45,'Sorveteria', '', 6);
insert into tcategoriadespesa values(46,'Presentes', '', 6);
insert into tcategoriadespesa values(47,'Doações', '', 6);
insert into tcategoriadespesa values(48,'Outros', '', 6);

insert into tcategoriadespesa values(49,'Bares / Restaurantes', '', 7);
insert into tcategoriadespesa values(50,'Shows / Boates', '', 7);
insert into tcategoriadespesa values(51,'Viagens / Pacote', '', 7);
insert into tcategoriadespesa values(52,'Passagens', '', 7);
insert into tcategoriadespesa values(53,'Clube', '', 7);
insert into tcategoriadespesa values(54,'Passeio', '', 7);
insert into tcategoriadespesa values(55,'Outros', '', 7);

insert into tcategoriadespesa values(56,'Veterinário', '', 8);
insert into tcategoriadespesa values(57,'PetShop', '', 8);
insert into tcategoriadespesa values(58,'Outros', '', 8);
