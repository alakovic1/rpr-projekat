BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `vehicle` (
	`id`	INTEGER,
	`name`	TEXT,
	`brand`	TEXT,
	`model`	TEXT,
	`nmbDoors`	INTEGER,
	`nmbSeats`	INTEGER,
	`engine`	TEXT,
	`available`	TEXT,
	`price`	INTEGER,
	PRIMARY KEY(`id`)
);
INSERT INTO `vehicle` VALUES (8,'PEUGEOT 207','PEUGEOT','207',5,5,'Benzin','yes',70);
INSERT INTO `vehicle` VALUES (9,'NISSAN JUKE','NISSAN','JUKE',5,5,'Diesel','yes',150);
INSERT INTO `vehicle` VALUES (10,'RENAULT CLIO','RENAULT','CLIO 1.5 CRDi',5,5,'Diesel','yes',65);
INSERT INTO `vehicle` VALUES (11,'CITROEN C3','CITROEN','C3 1.4',5,5,'Diesel','yes',50);
INSERT INTO `vehicle` VALUES (12,'POLO','POLO','1.4',5,5,'Benzin','yes',80);
INSERT INTO `vehicle` VALUES (13,'SKODA FABIA','SKODA','1.2 HTP',5,5,'Benzin','yes',70);
INSERT INTO `vehicle` VALUES (14,'AUDI A3','AUDI','A3 1.9 TDI',5,5,'Diesel','yes',109);
INSERT INTO `vehicle` VALUES (15,'HYUNDAI i30','HYUNDAI','i30 1.4',5,5,'Benzin','yes',85);
INSERT INTO `vehicle` VALUES (16,'RENAULT MEGANE','RENAULT','MEGANE 1.6',5,5,'Benzin','no',90);
INSERT INTO `vehicle` VALUES (17,'OPEL INSIGNIA','OPEL','1.6 CDTi',5,5,'Diesel','yes',129);
INSERT INTO `vehicle` VALUES (18,'PORSCHE CAYENNE','PORSCHE','4.5',5,5,'Benzin','yes',200);
INSERT INTO `vehicle` VALUES (19,'AUDI Q7','AUDI','Q7 3.0 TDI',5,5,'Diesel','yes',209);
INSERT INTO `vehicle` VALUES (20,'BMW 530d','BMW','530d',5,5,'Diesel','no',230);
INSERT INTO `vehicle` VALUES (21,'MERCEDES E KLASA','MERCEDES','E KLASA',5,5,'Diesel','yes',270);
INSERT INTO `vehicle` VALUES (22,'AUDI A6 KARAVAN','AUDI','A6 3.0 TDI KARAVAN',5,5,'Diesel','yes',238);
INSERT INTO `vehicle` VALUES (23,'RENAULT TRAFIC','RENAULT','1.9 TDI',5,9,'Diesel','yes',300);
INSERT INTO `vehicle` VALUES (24,'VW MULTIVAN','VW','MULTIVAN TDI',5,9,'Diesel','no',311);
CREATE TABLE IF NOT EXISTS `reservation` (
	`id`	INTEGER,
	`personID`	INTEGER,
	`vehicleID`	INTEGER,
	`pickupDate`	TEXT,
	`returnDate`	TEXT,
	`cardNumber`	INTEGER,
	`expirationDate`	TEXT,
	`securityCode`	INTEGER,
	`firstName`	TEXT,
	`lastName`	TEXT,
	FOREIGN KEY(`personID`) REFERENCES `person`(`id`),
	FOREIGN KEY(`vehicleID`) REFERENCES `vehicle`(`id`),
	PRIMARY KEY(`id`)
);
INSERT INTO `reservation` VALUES (0,1,20,'17. 09. 2019','02. 10. 2019',1234123412341234,'07/21',1234,'Amila','Lakovic');
INSERT INTO `reservation` VALUES (1,3,24,'22. 10. 2019','01. 11. 2019',8888888888888888,'08/23',8888,'firstName','lastName');
INSERT INTO `reservation` VALUES (2,5,16,'17. 12. 2019','11. 01. 2020',NULL,NULL,0,NULL,NULL);
CREATE TABLE IF NOT EXISTS `person` (
	`id`	INTEGER,
	`firstName`	TEXT,
	`lastName`	TEXT,
	`username`	TEXT,
	`adress`	TEXT,
	`email`	TEXT,
	`password`	TEXT,
	PRIMARY KEY(`id`)
);
INSERT INTO `person` VALUES (1,'Amila','Lakovic','alakovic1','adresa 13','alakovic1@etf.unsa.ba','amila');
INSERT INTO `person` VALUES (2,'firstName','lastName','username','asress','email@etf.unsa.ba','password');
INSERT INTO `person` VALUES (3,'firstName','lastName','username','asress','email@etf.unsa.ba','password');
INSERT INTO `person` VALUES (4,'1.Name','2.Name','user','adress','email@gmail.com','user');
INSERT INTO `person` VALUES (5,'1.Name','2.Name','user','adress','email@gmail.com','user');
COMMIT;
