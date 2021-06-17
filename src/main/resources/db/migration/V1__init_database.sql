CREATE TABLE Books
(
id integer PRIMARY KEY AUTO_INCREMENT,
name varchar(255),
author varchar(255),
year integer,
ISBN long,
pages integer
);

CREATE TABLE Bookmarks
(
id integer PRIMARY KEY AUTO_INCREMENT,
page integer,
book_id integer,
FOREIGN KEY(book_id) REFERENCES Books(id)
);