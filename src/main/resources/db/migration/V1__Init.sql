CREATE TABLE brand (
    id int primary key,
    name varchar(256),
    description varchar(2048)
);

CREATE TABLE category (
    id int primary key,
    name varchar(256)
);

CREATE TABLE product (
    id int primary key,
    name varchar(256),
    price float,
    brand_id int references brand(id),
    category_id int references category(id),
    description varchar(2048),
    image varchar(1024)
);

CREATE TABLE users (
    id int primary key,
    name varchar(256),
    email varchar(256),
    password varchar(256)
);

CREATE TABLE orders (
    id int primary key,
    user_id int references users(id),
    order_date date
);

CREATE TABLE orders_product (
    order_id int references orders(id),
    product_id int references product(id)
);