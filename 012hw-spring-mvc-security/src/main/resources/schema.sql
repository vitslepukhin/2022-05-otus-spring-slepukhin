DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS book_authors;

CREATE TABLE users(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    login VARCHAR(255),
    password VARCHAR(255)
);

CREATE TABLE genres(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE authors(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE books(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    genre_id BIGINT,
        FOREIGN KEY (genre_id) REFERENCES genres(id)
);

CREATE TABLE comments(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    book_id BIGINT REFERENCES books(id) ON DELETE CASCADE,
    text VARCHAR(255)
);

CREATE TABLE book_authors(
    book_id BIGINT REFERENCES books(id) ON DELETE CASCADE,
    author_id BIGINT REFERENCES authors(id),
    PRIMARY KEY (book_id, author_id)
);