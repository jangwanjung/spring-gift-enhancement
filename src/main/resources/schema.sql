create table products
(
    id        bigint auto_increment,
    name      varchar(100),
    price     bigint,
    image_url varchar(255),
    primary key (id)
);

create table wish_list
(
    id bigint auto_increment,
    member_id bigint,
    product_id bigint,
    quantity int,
    primary key (id)

)
