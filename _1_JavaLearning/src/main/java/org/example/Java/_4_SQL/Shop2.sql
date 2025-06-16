CREATE SCHEMA shop2;
USE shop2;

CREATE TABLE users
(
    id           BIGINT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username     VARCHAR(50)  NOT NULL,
    password     VARCHAR(128) NOT NULL,
    email        VARCHAR(50)  NOT NULL,
    phone_number VARCHAR(20)  NOT NULL
);

CREATE TABLE user_details
(
    user_id BIGINT PRIMARY KEY REFERENCES users (id) ON DELETE CASCADE,
    first_name    VARCHAR(50)             NOT NULL,
    last_name     VARCHAR(50)             NOT NULL,
    gender        ENUM ('MALE', 'FEMALE') NOT NULL,
    date_of_birth DATE                    NOT NULL,
    address       VARCHAR(255)            NOT NULL
);

CREATE TABLE orders
(
    id          BIGINT           NOT NULL AUTO_INCREMENT,
    user_id     BIGINT            NOT NULL,
    order_list  TINYTEXT       NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE product
(
    id           BIGINT           NOT NULL AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(100)   NOT NULL,
    description  VARCHAR(255)   NOT NULL,
    price        DECIMAL(10, 2) NOT NULL,
    quantity     INT            NOT NULL
);

CREATE TABLE IF NOT EXISTS shopping_cart
(
	id BIGINT PRIMARY KEY auto_increment,
    user_id    BIGINT REFERENCES users(id) ON DELETE CASCADE,
    product_id BIGINT REFERENCES product(id) ON DELETE CASCADE,
    quantity   INT  NOT NULL
);