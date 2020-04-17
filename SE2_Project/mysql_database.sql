/* 
	You should make sure that the database doesn't exist before creating it.
*/
CREATE DATABASE se2_project;

USE se2_project;

CREATE TABLE my_user (
	id INT AUTO_INCREMENT,
	email VARCHAR(100) NOT NULL,
	username VARCHAR(100),
	password VARCHAR(100),
	PRIMARY KEY (id)
);

CREATE TABLE normal_user (
    id INT,
    email VARCHAR(100),
    PRIMARY KEY (email),
    CONSTRAINT normal_user_id_fk FOREIGN KEY (id) 
	REFERENCES my_user (id) 
	ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE shop_owner (
    id INT,
    email VARCHAR(100),
    PRIMARY KEY (email),
    CONSTRAINT shop_owner_id_fk FOREIGN KEY (id) 
	REFERENCES my_user (id) 
	ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE my_admin (
    id INT,
    email VARCHAR(100),
    PRIMARY KEY (email),
    CONSTRAINT admin_id_fk FOREIGN KEY (id) 
	REFERENCES my_user (id) 
	ON DELETE CASCADE ON UPDATE CASCADE
);

/*
	Token is (userType[delimiter]randomNumber).
*/
CREATE TABLE tokens (
	id int NOT NULL UNIQUE,
	token VARCHAR(100),
	PRIMARY KEY (token),
	CONSTRAINT token_id_fk 
	FOREIGN KEY (id)
	REFERENCES my_user (id)
	ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO my_user (email, username, password) VALUES ("admin", "admin", "admin");
INSERT INTO my_admin VALUES (1, "admin");
