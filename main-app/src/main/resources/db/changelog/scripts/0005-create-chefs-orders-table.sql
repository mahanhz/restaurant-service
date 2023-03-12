create table chefs_orders
(
    id bigint auto_increment,
    chef_id bigint not null,
    order_id bigint not null,
    created_date datetime null,
    last_modified_date datetime null,
    constraint orders_items_pk
        primary key (id),
    foreign key (chef_id) references chefs (id),
    foreign key (order_id) references orders (id)
);