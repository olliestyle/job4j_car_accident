CREATE TABLE accident (
    id serial primary key,
    name varchar(2000),
    text varchar(256),
    address varchar(256),
    accident_type_id int references accident(id)
);

CREATE TABLE rule (
    id serial primary key,
    name varchar(256)
);

CREATE TABLE accident_rule (
    id serial primary key,
    accident_id int references accident(id),
    rule_id int references rule(id)
);

CREATE TABLE accident_type (
    id serial primary key,
    name varchar(256)
);