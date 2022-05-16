create database booksdatabase;
use booksdatabase;

create table administrator
(
    logname  char(30) not null primary key,
    password varchar(30) null
);

create table user
(
    logname  char(30) not null primary key,
    password varchar(30),
    phone    char(20),
    address  char(50),
    realname char(10)
);

create table book
(
    book_id           int auto_increment primary key,
    book_name         char(20) not null,
    book_author       char(10) not null,
    book_translator   char(10) null,
    book_press        char(10) null,
    book_price        float null,
    book_num          int null,
    book_introduction varchar(300) null
);

create table orderform
(
    order_id    int auto_increment primary key,
    logname     char(30) null,
    order_title varchar(50) null,
    order_message     varchar(500) null,
    constraint orderform_ibfk_1
        foreign key (logname) references user (logname)
);

create table shoppingform
(
    goods_id     int auto_increment
        primary key,
    logname      char(30) not null,
    book_id      int      not null,
    goods_name   varchar(20) null,
    goods_price  float null,
    goods_amount int null,
    constraint shoppingform_ibfk_1
        foreign key (logname) references user (logname),
    constraint shoppingform_ibfk_2
        foreign key (book_id) references book (book_id)
);
