ALTER TABLE defects
    ADD CONSTRAINT FK_defects_coffee_id
        FOREIGN KEY (coffee_id)
        REFERENCES coffees (id);