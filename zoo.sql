create table TourGuide (
    tour_guide_id int not null auto_increment,
    name varchar(50) not null,
    email varchar(50) not null,
    phone varchar(10) not null,

    constraint tour_guide_pk primary key (tour_guide_id)
);

create table Tour (
    tour_id int not null auto_increment,
    tour_guide_id int not null,
    start_time datetime not null,
    end_time datetime not null,

    constraint tour_pk primary key (tour_id),
    constraint tour_tour_guide_fk foreign key (tour_guide_id) references TourGuide(tour_guide_id)
);

create table Visitor (
    visitor_id int not null auto_increment,
    name varchar(50) not null,
    email varchar(50) not null,
    phone varchar(10) not null,

    constraint visitor_pk primary key (visitor_id)
);

create table TourVisitor (
    tour_id int not null,
    visitor_id int not null,

    constraint tour_visitor_pk primary key (tour_id, visitor_id),
    constraint tour_visitor_tour_fk foreign key (tour_id) references Tour(tour_id),
    constraint tour_visitor_visitor_fk foreign key (visitor_id) references Visitor(visitor_id)
);

create table Transaction (
    transaction_id int not null auto_increment,
    money_charged real not null,
    card_number varchar(16) not null,
    security_code varchar(3) not null,
    expiration_date date not null,

    constraint transaction_pk primary key (transaction_id)
);

create table VisitorTransaction (
    visitor_id int not null,
    transaction_id int not null,

    constraint visitor_transaction_pk primary key (visitor_id, transaction_id),
    constraint visitor_transaction_visitor_fk foreign key (visitor_id) references Visitor(visitor_id),
    constraint visitor_transaction_transaction_fk foreign key (transaction_id) references Transaction(transaction_id)
);

create table GiftShopItem (
    gift_shop_item_id int not null auto_increment,
    name varchar(50) not null,
    price real not null,
    description varchar(200) not null,
    image blob,

    constraint gift_shop_item_pk primary key (gift_shop_item_id)
);

create table ItemTransaction (
    item_id int not null,
    transaction_id int not null,

    constraint item_transaction_pk primary key (item_id, transaction_id),
    constraint item_transaction_item_fk foreign key (item_id) references GiftShopItem(gift_shop_item_id),
    constraint item_transaction_transaction_fk foreign key (transaction_id) references Transaction(transaction_id)
);


create table Tram (
    tram_id int not null auto_increment,
    name varchar(50) not null,
    color varchar(20) not null,
    start_time time not null,
    end_time time not null,

    constraint tram_pk primary key (tram_id)
);

create table VisitorTram (
    visitor_id int not null,
    tram_id int not null,

    constraint visitor_tram_pk primary key (visitor_id, tram_id),
    constraint visitor_tram_visitor_fk foreign key (visitor_id) references Visitor(visitor_id),
    constraint visitor_tram_tram_fk foreign key (tram_id) references Tram(tram_id)
);

create table Section (
    section_id int not null auto_increment,
    name varchar(50) not null,

    constraint section_pk primary key (section_id)
);

create table TramSection (
    tram_id int not null,
    section_id int not null,

    constraint tram_section_pk primary key (tram_id, section_id),
    constraint tram_section_tram_fk foreign key (tram_id) references Tram(tram_id),
    constraint tram_section_section_fk foreign key (section_id) references Section(section_id)
);

create table Enclosure (
    enclosure_id int not null auto_increment,
    name varchar(50) not null,
    area varchar(50) not null,
    section_id int not null,

    constraint enclosure_pk primary key (enclosure_id),
    constraint enclosure_section_fk foreign key (section_id) references Section(section_id)
);

create table Animal (
    animal_id int not null auto_increment,
    name varchar(50) not null,
    species varchar(50) not null,
    enclosure_id int not null,

    constraint animal_pk primary key (animal_id),
    constraint animal_enclosure_fk foreign key (enclosure_id) references Enclosure(enclosure_id)
);

create table Zookeeper (
    zookeeper_id int not null auto_increment,
    name varchar(50) not null,
    email varchar(50) not null,
    phone varchar(10) not null,

    constraint zookeeper_pk primary key (zookeeper_id)
);

create table EnclosureZookeeper (
    enclosure_id int not null,
    zookeeper_id int not null,

    constraint enclosure_zookeeper_pk primary key (enclosure_id, zookeeper_id),
    constraint enclosure_zookeeper_enclosure_fk foreign key (enclosure_id) references Enclosure(enclosure_id),
    constraint enclosure_zookeeper_zookeeper_fk foreign key (zookeeper_id) references Zookeeper(zookeeper_id)
);

insert into TourGuide (name, email, phone) values
    ("John Doe", "johndoe@website.com", "1234567890"),
    ("Jane Doe", "janedoe@website.com", "0987654321");

insert into Tour (tour_guide_id, start_time, end_time) values
    (1, "2025-10-21 9:00:00", "2025-10-21 10:00:00"),
    (2, "2025-10-21 10:00:00", "2025-10-21 11:00:00");

insert into Visitor (name, email, phone) values
    ("John Doe", "johndoe@website.com", "1234567890"),
    ("Jane Doe", "janedoe@website.com", "0987654321");

insert into TourVisitor (tour_id, visitor_id) values
    (1, 1),
    (2, 2);

insert into Transaction (money_charged, card_number, security_code, expiration_date) values
    (44.99, "1234567890123456", "123", "2025-04-23"),
    (12.99, "1234561234567890", "321", "2026-06-06");

insert into VisitorTransaction (visitor_id, transaction_id) values
    (1, 1),
    (2, 2);

insert into GiftShopItem (name, price, description) values
    ("stuffed animal", 15.99, "a stuffed lion"),
    ("t-shirt", 19.99, "a souvenir t-shirt");
    
insert into ItemTransaction (item_id, transaction_id) values
    (1, 1),
    (2, 2);

insert into Tram (name, color, start_time, end_time) values
    ("Tram A", "blue", "9:00:00", "13:00:00"),
    ("Tram B", "green", "13:00:00", "17:00:00");

insert into VisitorTram (visitor_id, tram_id) values
    (1, 1),
    (2, 2);

insert into Section (name) values
    ("mammals"),
    ("reptiles");

insert into TramSection (tram_id, section_id) values
    (1, 1),
    (2, 2);

insert into Enclosure (name, area, section_id) values
    ("Lions enclosure", "west side", 1),
    ("Crocodiles enclosure", "east side", 2);

insert into Animal (name, species, enclosure_id) values
    ("Henry", "Lion", 1),
    ("Abby", "Crocodile", 2);

insert into Zookeeper (name, email, phone) values
    ("John Doe", "johndoe@website.com", "1234567890"),
    ("Jane Doe", "janedoe@website.com", "0987654321");

insert into EnclosureZookeeper (enclosure_id, zookeeper_id) values
    (1, 1),
    (2, 2);
