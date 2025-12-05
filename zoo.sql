create database if not exists test;

use test;

create table if not exists GiftShopItem (
    gift_shop_item_id int not null auto_increment,
    name varchar(50) not null,
    price int not null,
    description varchar(200) not null,
    image mediumblob,

    constraint gift_shop_item_pk primary key (gift_shop_item_id)
);

create database if not exists zoo;

use zoo;

create table if not exists GiftShopItem (
    gift_shop_item_id int not null auto_increment,
    name varchar(50) not null,
    price int not null,
    description varchar(200) not null,
    image mediumblob,

    constraint gift_shop_item_pk primary key (gift_shop_item_id)
);