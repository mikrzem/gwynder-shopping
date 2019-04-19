ALTER TABLE spending_category
    ADD COLUMN parent_id bigint;

ALTER TABLE spending_category
    ADD CONSTRAINT FKn0tbdijwktuwm6ckbmsd23s5n FOREIGN KEY (parent_id) REFERENCES spending_category;