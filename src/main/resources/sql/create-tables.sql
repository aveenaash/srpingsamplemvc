create schema customer_app;

use customer_app;

show tables;

create table customer(
id bigint(10) not null auto_increment,
`first_name` varchar(120) not null,
`last_name` varchar(120) not null,
`phone` varchar(10) not null,
`email` varchar(120) not null,
`address` varchar(120) not null,
created_at datetime not null,
updated_at datetime not null,
primary key (id));

create table customer_login(
id bigint(10) not null auto_increment,
`user_name` varchar(120) not null,
`password` varchar(128) not null,
last_signed_on datetime not null,
created_at datetime not null,
updated_at datetime not null,
customer_id bigint(10) not null,
primary key (id),
foreign key (customer_id) references customer(id));

