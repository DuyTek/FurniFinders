CREATE USER 'spring'@'%' IDENTIFIED BY 'spring';
GRANT ALL PRIVILEGES ON *.* TO 'spring'@'%';

CREATE DATABASE if not exists furnifinders default character set utf8mb4 collate utf8mb4_general_ci;

USE furnifinders;

CREATE TABLE if not exists user (
    id int(11) NOT NULL AUTO_INCREMENT,
    email varchar(255) NOT NULL,
    phone varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    first_name varchar(255) NOT NULL,
    last_name varchar(255) NOT NULL,
    role varchar(255) NOT NULL,
    create_date datetime NOT NULL,
    confirmed_email tinyint(1) NOT NULL DEFAULT '0',
    checkbox tinyint(1) NOT NULL DEFAULT '0',

    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO user (email, phone, password, first_name, last_name, role, create_date, confirmed_email, checkbox) VALUES
('dinhquoclam02@gmail.com', '0123456789', '$2a$10$', 'Lam', 'Dinh', 'ROLE_ADMIN', '2020-12-12 00:00:00', 1, 1);