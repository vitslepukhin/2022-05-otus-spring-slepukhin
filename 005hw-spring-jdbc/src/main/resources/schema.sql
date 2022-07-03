DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS authors;

CREATE TABLE genres(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    genre VARCHAR(255)
);

CREATE TABLE authors(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE books(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    author_id BIGINT,
        FOREIGN KEY (author_id) REFERENCES authors(id),
    genre_id BIGINT,
        FOREIGN KEY (genre_id) REFERENCES genres(id)
);