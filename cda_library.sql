-- création de la base de données -param charset global
CREATE DATABASE IF NOT EXISTS cdaLibrary CHARSET utf8mb4;
-- utiliser la base "cdaLibrary"
USE cdaLibrary;

-- création des tables - optional parem charset par table prio
CREATE TABLE IF NOT EXISTS Livre(
	id_livre INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nom_livre VARCHAR(50) NOT NULL,
    description_livre VARCHAR(255),
    date_publication_livre VARCHAR(50),
    genre_livre varchar(255)
)ENGINE=innoDB CHARSET=utf8mb4;

