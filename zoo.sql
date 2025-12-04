create database zoo;

use zoo;

create table GiftShopItem (
    gift_shop_item_id int not null auto_increment,
    name varchar(50) not null,
    price real not null,
    description varchar(200) not null,
    image blob,

    constraint gift_shop_item_pk primary key (gift_shop_item_id)
);
