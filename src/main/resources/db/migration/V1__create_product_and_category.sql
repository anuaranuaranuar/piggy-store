CREATE TABLE category(
    id BIGSERIAL PRIMARY KEY,
    name varchar(255),
    type varchar(255)
);

CREATE TABLE product(
    id BIGSERIAL PRIMARY KEY,
    name varchar(255) NOT NULL,
    price REAL NOT NULL,
    description TEXT,
    is_active BOOLEAN NOT NULL,
    quantity BIGINT,
    category_id BIGINT,
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES category(id)
);

