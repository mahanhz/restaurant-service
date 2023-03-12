create table chefs
(
    id bigint auto_increment,
    name varchar(255) not null,
    created_date datetime null,
    last_modified_date datetime null,
    constraint chefs_pk
        primary key (id)
);