create table t_order
(
    id             int            not null auto_increment,
    total_quantity decimal(10, 2) not null,
    unit_price     decimal(10, 2) not null,
    primary key (id)
);
