
create table wish_list
(
    id bigint auto_increment,
    member_id bigint,
    product_id bigint,
    quantity int,
    primary key (id)

);
