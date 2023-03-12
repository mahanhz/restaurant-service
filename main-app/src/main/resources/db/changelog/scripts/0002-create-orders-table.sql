create table orders
(
    id bigint auto_increment,
    order_date date not null,
    state ENUM('CREATED', 'ASSIGNED', 'REJECTED', 'COMPLETED'),
    created_date datetime null,
    last_modified_date datetime null,
    constraint orders_pk
        primary key (id)
);
