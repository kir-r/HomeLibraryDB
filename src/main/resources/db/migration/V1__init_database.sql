CREATE TABLE Visitor
(
id integer PRIMARY KEY AUTO_INCREMENT,
name varchar(255),
login varchar(255),
password varchar(255),
isAdmin bit(1),
blocked bit(0)
);

CREATE TABLE Book
(
id integer PRIMARY KEY AUTO_INCREMENT,
name varchar(255),
author varchar(255),
year integer,
ISBN long,
pages integer
);

CREATE TABLE Bookmark
(
id integer PRIMARY KEY AUTO_INCREMENT,
page integer,
visitor_id integer,
FOREIGN KEY(visitor_id) REFERENCES Visitor(id),
book_id integer,
FOREIGN KEY(book_id) REFERENCES Book(id)
);

CREATE TABLE Whatever
(
id integer PRIMARY KEY AUTO_INCREMENT,
name varchar(255)
);