CREATE DATABASE medicinenotedb;

USE medicinenotedb;

CREATE TABLE medicine_list (
	id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	medicine_name VARCHAR(255) NOT NULL,
	amount INT(11) NOT NULL,
	medicine_time VARCHAR(255) NOT NULL,
	created_at DateTime NOT NULL default now(),
	updated_at DateTime NOT NULL default now()
	);
	
INSERT INTO medicine_list
(medicine_name,amount,medicine_time)
VALUES ('サンプルネーム', 2, '朝');