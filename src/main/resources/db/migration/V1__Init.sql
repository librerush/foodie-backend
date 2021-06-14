CREATE TABLE IF NOT EXISTS brand (
    id BIGSERIAL PRIMARY KEY,
    name varchar(1024) not null
);

CREATE TABLE IF NOT EXISTS category (
    id BIGSERIAL PRIMARY KEY,
    name varchar(1024) not null
);

CREATE TABLE IF NOT EXISTS product (
    id BIGSERIAL PRIMARY KEY,
    name varchar(1024) not null,
    price float,
    brand_id BIGSERIAL references brand(id),
    category_id BIGSERIAL references category(id),
    description varchar(2048),
    image varchar(2048)
);

CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    name varchar(256) not null,
    email varchar(256) not null,
    password varchar(256) not null,
    address varchar(1024)
);

CREATE TABLE IF NOT EXISTS orders (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGSERIAL references users(id),
    order_date timestamp without time zone default (now() at time zone 'utc')
);

CREATE TABLE IF NOT EXISTS orders_product (
    order_id BIGSERIAL references orders(id),
    product_id BIGSERIAL references product(id),
    quantity int not null,
    PRIMARY KEY (order_id, product_id),
    CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES orders(id),
    CONSTRAINT fk_product_id FOREIGN KEY (product_id) references product(id)
);
