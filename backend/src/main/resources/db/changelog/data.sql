CREATE USER 'spring'@'%' IDENTIFIED BY 'spring';
GRANT ALL PRIVILEGES ON *.* TO 'spring'@'%';

CREATE DATABASE if not exists furnifinders default character set utf8mb4 collate utf8mb4_general_ci;