CREATE TABLE IF NOT EXISTS brand (
    id int primary key,
    name varchar(256),
    description varchar(2048)
);

CREATE TABLE IF NOT EXISTS category (
    id int primary key,
    name varchar(256)
);

CREATE TABLE IF NOT EXISTS product (
    id int primary key,
    name varchar(256),
    price float,
    brand_id int references brand(id),
    category_id int references category(id),
    description varchar(2048),
    image varchar(1024)
);

CREATE TABLE IF NOT EXISTS users (
    id int primary key,
    name varchar(256),
    email varchar(256),
    password varchar(256),
    address varchar(1024)
);

CREATE TABLE IF NOT EXISTS orders (
    id int primary key,
    user_id int references users(id),
    order_date timestamp without time zone default (now() at time zone 'utc'),
    done bool
);

CREATE TABLE IF NOT EXISTS orders_product (
    order_id int references orders(id),
    product_id int references product(id)
);

CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START 1;