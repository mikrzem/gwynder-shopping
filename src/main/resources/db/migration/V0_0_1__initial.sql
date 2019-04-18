create table spending_category
(
    id bigserial not null
        constraint spending_category_pkey
            primary key,
    owner varchar(255) not null,
    name varchar(255) not null
);

create table product
(
    id bigserial not null
        constraint product_pkey
            primary key,
    owner varchar(255) not null,
    name varchar(255) not null,
    category_id bigint not null
        constraint fkbavfai36cjj7rr1h6eraothef
            references spending_category
);

create table spending_location
(
    id bigserial not null
        constraint spending_location_pkey
            primary key,
    owner varchar(255) not null,
    name varchar(255) not null
);

create table purchase
(
    id bigserial not null
        constraint purchase_pkey
            primary key,
    owner varchar(255) not null,
    manual_total boolean not null,
    purchase_date date not null,
    total numeric(12,2) not null,
    category_id bigint not null
        constraint fksdpr3swk6nkbxg4f52faeg7av
            references spending_category,
    location_id bigint not null
        constraint fknjq61h57o9hi705gv5537xpc7
            references spending_location
);

create table purchase_product
(
    id bigserial not null
        constraint purchase_product_pkey
            primary key,
    owner varchar(255) not null,
    amount integer not null,
    price numeric(12,2) not null,
    product_id bigint not null
        constraint fkl1da8u1v57wry7sunkkgmjr8o
            references product,
    purchase_id bigint not null
        constraint fk1te3j5efipmc5c19wve8c90qd
            references purchase
);

