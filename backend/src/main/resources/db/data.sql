CREATE USER 'spring'@'%' IDENTIFIED BY 'spring';
GRANT ALL PRIVILEGES ON *.* TO 'spring'@'%';

CREATE DATABASE if not exists furnifinders default character set utf8mb4 collate utf8mb4_general_ci;

USE furnifinders;

CREATE TABLE if not exists user (
    user_id int(11) NOT NULL AUTO_INCREMENT,
    user_email varchar(255) NOT NULL,
    user_password varchar(255) NOT NULL,
    user_first_name varchar(255) NOT NULL,
    user_last_name varchar(255) NOT NULL,
    user_phone varchar(255) NOT NULL,
    user_role varchar(255) NOT NULL,
    PRIMARY KEY (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE if not exists product (
    product_id int(11) NOT NULL AUTO_INCREMENT,
    product_name varchar(255) NOT NULL,
    product_description varchar(255) NOT NULL,
    product_percentage int(11) NOT NULL,
    product_price double NOT NULL,
    product_post_status varchar(255) NOT NULL,
    product_status varchar(255) NOT NULL,
    product_image varchar(255)  NULL,
    product_quantity int(11) NOT NULL,
    PRIMARY KEY (product_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE if not exists product_user_link(
    product_user_link_id int(11) NOT NULL AUTO_INCREMENT,
    product_user_link_user_type varchar(255) NULL,
    product_user_link_user_id int(11) NOT NULL,
    product_user_link_product_id int(11) NOT NULL,
    PRIMARY KEY (product_user_link_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE if not exists cart(
    cart_id int(11) NOT NULL AUTO_INCREMENT,
    cart_user_id int(11) NOT NULL,
    cart_status varchar(255) NOT NULL,
    cart_order_id int(11) NOT NULL,
    PRIMARY KEY (cart_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE if not exists cart_detail(
    cart_detail_id int(11) NOT NULL AUTO_INCREMENT,
    cart_detail_product_id int(11) NOT NULL,
    cart_detail_quantity int(11) NOT NULL,
    cart_detail_total_price int(11) NOT NULL,
    cart_detail_cart_id int(11) NOT NULL,
    PRIMARY KEY (cart_detail_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE if not exists `order`(
    order_id int(11) NOT NULL AUTO_INCREMENT,
    order_created_date datetime NOT NULL,
    order_delivery_address varchar(255) NOT NULL,
    order_delivery_phone varchar(255) NOT NULL,
    order_delivery_note varchar(255) NOT NULL,
    order_payment_method varchar(255) NOT NULL,
    order_delivery_status varchar(255) NOT NULL,
    order_payment_status varchar(255) NOT NULL,
    order_total_price int(11) NOT NULL,
    order_total_quantity int(11) NOT NULL,
    PRIMARY KEY (order_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
