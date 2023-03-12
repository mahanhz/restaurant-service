create table items
(
    id bigint auto_increment,
    name varchar(255) not null,
    quantity int not null,
    created_date datetime null,
    last_modified_date datetime null,
    constraint items_pk
        primary key (id)
);