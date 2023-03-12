create table orders_items
(
    id bigint auto_increment,
    order_id bigint not null,
    item_id bigint not null,
    created_date datetime null,
    last_modified_date datetime null,
    constraint orders_items_pk
        primary key (id),
    foreign key (order_id) references orders (id),
    foreign key (item_id) references items (id)
);