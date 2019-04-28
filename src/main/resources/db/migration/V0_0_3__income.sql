CREATE TABLE income (
    id          bigserial      NOT NULL,
    owner       varchar(255)   NOT NULL,
    income      numeric(12, 2) NOT NULL,
    income_date date           NOT NULL,
    source_id   int8           NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE income_source (
    id    bigserial    NOT NULL,
    owner varchar(255) NOT NULL,
    name  varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE income
    ADD CONSTRAINT FKe3iiyys1fl5s0abjbic99ugmo FOREIGN KEY (source_id) REFERENCES income_source;