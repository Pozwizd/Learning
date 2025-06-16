CREATE SCHEMA Shop;
use shop;
CREATE TABLE User (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    phone_number VARCHAR(20) NOT NULL
);

CREATE TABLE User_Details (
    user_id INT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    gender ENUM('male', 'female') NOT NULL,
    date_of_birth DATE NOT NULL,
    address VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(id)
);

CREATE TABLE Product (
    id INT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(100) NOT NULL,
    description VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    quantity INT NOT NULL
);

CREATE TABLE Shopping_Cart (
id INT PRIMARY KEY AUTO_INCREMENT,
user_id INT NOT NULL,
product_id INT NOT NULL,
quantity INT NOT NULL,
FOREIGN KEY (user_id) REFERENCES User(id),
FOREIGN KEY (product_id) REFERENCES Product(id)
);

CREATE TABLE Orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    order_date DATE NOT NULL,
    cart_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(id),
    FOREIGN KEY (cart_id) REFERENCES Shopping_Cart(id)
);

INSERT INTO User (username, email, phone_number)
VALUES 
    ('user1', 'user1@example.com', '+380(50)111-22-33'),
    ('user2', 'user2@example.com', '+380(50)222-33-44'),
    ('user3', 'user3@example.com', '+380(50)333-44-55'),
    ('user4', 'user4@example.com', '+380(50)444-55-66'),
    ('user5', 'user5@example.com', '+380(50)555-66-77'),
    ('user6', 'user6@example.com', '+380(50)666-77-88'),
    ('user7', 'user7@example.com', '+380(50)777-88-99'),
    ('user8', 'user8@example.com', '+380(50)888-99-00'),
    ('user9', 'user9@example.com', '+380(50)999-00-11'),
    ('user10', 'user10@example.com', '+380(50)000-11-22');

INSERT INTO User_Details (user_id, first_name, last_name, gender, date_of_birth, address)
VALUES 
    (1, 'Іван', 'Петренко', 'male', '1980-01-01', 'Київ, вул. Шевченка 10'),
    (2, 'Марія', 'Ковальчук', 'female', '1990-05-06', 'Львів, вул. Франка 5'),
    (3, 'Олександр', 'Сидоренко', 'male', '1985-10-15', 'Одеса, вул. Дерибасівська 15'),
    (4, 'Оксана', 'Петрова', 'female', '1995-03-21', 'Харків, вул. Пушкінська 20'),
    (5, 'Максим', 'Коваль', 'male', '1989-07-12', 'Дніпро, вул. Гагаріна 7'),
    (6, 'Ольга', 'Сидорчук', 'female', '1983-11-30', 'Чернівці, вул. Грушевського 3'),
    (7, 'Віталій', 'Шевченко', 'male', '1978-09-05', 'Херсон, вул. Лермонтова 12'),
    (8, 'Наталія', 'Коваленко', 'female', '1991-02-17', 'Івано-Франківськ, вул. Шевченка 8'),
    (9, 'Юрій', 'Попов', 'male', '1975-12-24', 'Запоріжжя, вул. Соборна 17'),
    (10, 'Тетяна', 'Сидорова', 'female', '1987-04-08', 'Луцьк, вул. Ковельська 30');

INSERT INTO Product (product_name, description, price, quantity)
VALUES 
    ('AMD Ryzen 9 5950X', '16-Core 3.4 GHz CPU', 999.99, 10),
    ('Intel Core i9-11900K', '8-Core 3.5 GHz CPU', 699.99, 12),
    ('ASUS ROG Strix Z590-E Gaming WiFi 6E', 'ATX Motherboard', 449.99, 15),
    ('GIGABYTE AORUS Z590 MASTER', 'ATX Motherboard', 499.99, 8),
    ('Corsair Vengeance RGB Pro 32GB (2 x 16GB) DDR4 3200', 'Desktop Memory', 199.99, 20),
    ('G.SKILL Ripjaws V Series 64GB (2 x 32GB) DDR4 3600', 'Desktop Memory', 299.99, 10),
    ('Samsung 980 PRO 1TB PCIe 4.0 NVMe SSD', 'Internal SSD', 229.99, 15),
    ('Western Digital WD_BLACK SN850 2TB PCIe 4.0 NVMe SSD', 'Internal SSD', 399.99, 8),
    ('Seagate BarraCuda 4TB Internal Hard Drive HDD', 'Internal Hard Drive', 94.99, 20),
    ('Corsair RM850x 850 Watt 80+ Gold Certified Fully Modular PSU', 'Power Supply Unit', 149.99, 12),
    ('EVGA SuperNOVA 1000 G5 1000 Watt 80+ Gold Certified Fully Modular PSU', 'Power Supply Unit', 199.99, 8),
    ('ASUS NVIDIA GeForce RTX 3080 Ti 12GB GDDR6X Graphics Card', 'Graphics Card', 1499.99, 5),
    ('GIGABYTE NVIDIA GeForce RTX 3090 24GB GDDR6X Graphics Card', 'Graphics Card', 1999.99, 3),
    ('NZXT Kraken X73 360mm AIO Cooler', 'CPU Cooler', 179.99, 15),
    ('Corsair H150i Elite Capellix 360mm AIO Cooler', 'CPU Cooler', 199.99, 10),
    ('Phanteks Eclipse P500A High Airflow Full-metal Mesh Design', 'ATX Mid Tower Computer Case', 149.99, 20),
    ('Lian Li PC-O11 Dynamic XL ROG Certified (Black)', 'ATX Mid Tower Computer Case', 249.99, 8),
    ('Logitech G915 LIGHTSPEED Wireless Mechanical Gaming Keyboard', 'Wireless Keyboard', 229.99, 12),
    ('Corsair K70 RGB MK.2 SE Mechanical Gaming Keyboard', 'Wired Keyboard', 179.99, 15),
    ('Logitech G Pro Wireless Gaming Mouse', 'Wireless Mouse', 129.99, 10),
    ('Razer DeathAdder V2 Gaming Mouse', 'Wired Mouse', 69.99, 20),
    ('ASUS VG279QM 27" Full HD 1080p 280Hz IPS Gaming Monitor', 'Gaming Monitor', 469.99, 5),
    ('LG 27GL83A-B 27" Quad HD 1440p IPS Gaming Monitor', 'Gaming Monitor', 379.99, 8),
    ('Audio-Technica ATH-M50x Professional Studio Monitor Headphones', 'Headphones', 149.99, 15),
    ('Sennheiser HD 660 S HiRes Audiophile Open Back Headphones', 'Headphones', 499.99, 5),
    ('Blue Yeti USB Microphone', 'USB Microphone', 129.99, 10),
    ('Shure SM7B Cardioid Dynamic Microphone', 'Dynamic Microphone', 399.99, 5),
    ('Elgato Stream Deck XL - Advanced Stream Control with 32 customizable LCD keys', 'Stream Deck', 249.99, 8),
    ('Logitech C920 HD Pro Webcam', 'Webcam', 79.99, 20),
    ('Rode PSA1 Swivel Mount Studio Microphone Boom Arm', 'Boom Arm', 99.99, 10),
    ('Samson SR850 Semi-Open-Back Studio Reference Headphones', 'Headphones', 49.99, 20),
    ('AKG Pro Audio K712 PRO Over-Ear, Open-Back, Flat-Wire, Reference Studio Headphones', 'Headphones', 499.99, 5),
    ('Antlion Audio ModMic Wireless Attachable Boom Microphone', 'Wireless Microphone', 119.99, 10),
    ('HyperX Cloud II Wireless 7.1 Surround Sound Gaming Headset', 'Wireless Headset', 149.99, 15),
    ('Beyerdynamic DT 990 PRO Over-Ear Studio Headphones', 'Headphones', 179.99, 15),
    ('Audio-Technica ATR2100x-USB Cardioid Dynamic Microphone', 'Dynamic Microphone', 119.99, 10),
    ('Blue Snowball iCE USB Mic for Recording and Streaming on PC and Mac', 'USB Microphone', 49.99, 20),
    ('Fifine K669B USB Microphone with Volume Dial', 'USB Microphone', 29.99, 30),
    ('EVGA GeForce GTX 1660 Super SC Ultra Gaming Graphics Card', 'Graphics Card', 399.99, 8),
    ('MSI Gaming GeForce RTX 3060 Ti 8GB GDDR6 Graphics Card', 'Graphics Card', 699.99, 5),
    ('ASUS ROG Strix 650W Gold PSU', 'Power Supply Unit', 129.99, 12),
    ('EVGA Supernova 650W Fully Modular PSU', 'Power Supply Unit', 89.99, 20),
    ('Crucial Ballistix 16GB DDR4 3200MHz Desktop Memory', 'Desktop Memory', 99.99, 20),
    ('G.SKILL Trident Z RGB Series 32GB DDR4 3200MHz Desktop Memory', 'Desktop Memory', 179.99, 10),
    ('Samsung 970 EVO Plus 500GB NVMe SSD', 'Internal SSD', 99.99, 20),
    ('Seagate IronWolf 8TB NAS Internal Hard Drive HDD', 'Internal Hard Drive', 219.99, 5),
    ('Western Digital WD Blue 2TB Internal Hard Drive HDD', 'Internal Hard Drive', 54.99, 30),
    ('Noctua NH-D15 chromax.black CPU Cooler', 'CPU Cooler', 99.99, 10),
    ('be quiet! Dark Rock Pro 4 CPU Cooler', 'CPU Cooler', 89.99, 15),
    ('Fractal Design Meshify C ATX Mid Tower Computer Case', 'ATX Mid Tower Computer Case', 99.99, 20),
    ('Phanteks Enthoo Pro 2 Full Tower Computer Case', 'Full Tower Computer Case', 189.99, 8),
    ('Logitech G Pro X Mechanical Gaming Keyboard', 'Wired Keyboard', 149.99, 12),
    ('HyperX Alloy Origins Core Mechanical Gaming Keyboard', 'Wired Keyboard', 89.99, 20),
    ('Logitech G703 LIGHTSPEED Wireless Gaming Mouse', 'Wireless Mouse', 99.99, 15),
    ('Razer Basilisk Ultimate HyperSpeed Wireless Gaming Mouse', 'Wireless Mouse', 129.99, 10),
    ('ASUS TUF Gaming VG289Q 28" 4K UHD IPS Gaming Monitor', 'Gaming Monitor', 349.99, 8),
    ('AOC C27G2 27" Curved Frameless Gaming Monitor', 'Gaming Monitor', 229.99, 15),
    ('Blue Yeti Nano USB Microphone', 'USB Microphone', 99.99, 12),
    ('Rode NT1-A Anniversary Vocal Cardioid Condenser Microphone', 'Condenser Microphone', 229.99, 5);


INSERT INTO Shopping_Cart (user_id, product_id, quantity) VALUES
    (1, 1, 2),
    (2, 2, 1),
    (1, 3, 3),
    (3, 4, 4),
    (2, 5, 2),
    (1, 6, 1),
    (4, 7, 2),
    (3, 8, 3),
    (4, 9, 5),
    (1, 10, 4),
    (2, 11, 3),
    (3, 12, 2),
    (4, 13, 1),
    (1, 14, 1),
    (3, 15, 2),
    (2, 16, 3),
    (4, 17, 4),
    (1, 18, 5),
    (3, 19, 2),
    (2, 20, 3);

INSERT INTO Orders (user_id, order_date, cart_id) 
VALUES 
    (1, '2023-07-07', 1),
    (1, '2023-07-07', 3),
    (1, '2023-07-07', 6),
    (1, '2023-07-07', 10),
    (1, '2023-07-07', 14),
    (2, '2023-07-07', 2),
    (2, '2023-07-07', 5),
    (2, '2023-07-07', 11),
    (2, '2023-07-07', 16),
    (2, '2023-07-07', 20),
    (3, '2023-07-07', 4),
    (3, '2023-07-07', 8),
    (3, '2023-07-07', 12),
    (3, '2023-07-07', 15),
    (3, '2023-07-07', 19),
    (4, '2023-07-07', 7),
    (4, '2023-07-07', 9),
    (4, '2023-07-07', 13),
    (4, '2023-07-07', 17),
    (4, '2023-07-07', 18);