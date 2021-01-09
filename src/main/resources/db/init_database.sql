drop database if exists library;
create database library;

drop table if exists library.author;
create table library.author (
   id bigint AUTO_INCREMENT PRIMARY KEY,
   first_name varchar(255) NOT NULL,
   last_name varchar(255) NOT NULL
);

create table library.book (
   id bigint AUTO_INCREMENT PRIMARY KEY,
   title varchar(255) NOT NULL,
   description varchar(255) NOT NULL,
   author_id bigint NOT NULL,
   FOREIGN KEY (author_id) REFERENCES author(id)
);

create table library.review (
   id bigint AUTO_INCREMENT PRIMARY KEY,
   review text NOT NULL,
   book_id bigint,
   score int NOT NULL,
   FOREIGN KEY (book_id) REFERENCES book(id)
);
