DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `dnName` varchar(255) DEFAULT NULL,
                            `dbSource` varchar(255) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


insert into department (dnName, dbSource) values ("test1","dbSource—02");
insert into department (dnName, dbSource) values ("test2","dbSource—02");
insert into department (dnName, dbSource) values ("test3","dbSource—02");
insert into department (dnName, dbSource) values ("test5","dbSource—02");
insert into department (dnName, dbSource) values ("test4","dbSource—02");
insert into department (dnName, dbSource) values ("test6","dbSource—02");