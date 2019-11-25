CREATE TABLE products(
id int auto_increment primary key,
product_name varchar(255) not null
);

CREATE TABLE reviews(
id int auto_increment primary key,
review varchar(10000) not null,
created_time timestamp default CURRENT_TIMESTAMP not null,
product_id int not null,
FOREIGN KEY(product_id) REFERENCES products(id)
);

CREATE TABLE comments(
id int auto_increment primary key,
comment varchar(10000) not null,
created_time timestamp default CURRENT_TIMESTAMP not null,
review_id int not null,
FOREIGN KEY (review_id) REFERENCES reviews(id)
);