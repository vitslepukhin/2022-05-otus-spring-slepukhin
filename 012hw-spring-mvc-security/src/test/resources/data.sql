INSERT INTO users
    (name, login, password)
VALUES
    ('Ivan Ivanov', 'uswer', '$2a$12$hW2.plUiT6gcY0Uoqwpc.eeAwf1oHouIxUMG8ZjTy4jVpZMSnloly'), -- password
    ('Petr Petrov', 'admin', '$2a$12$5ksHLzDcgHQp.nFrAFL4mumvZVCmJIa35pua8gKp9v7zZCuVwR2pO'); -- admin

INSERT INTO genres
    (name)
VALUES
    ('science'),
    ('popular'),
    ('folk');

INSERT INTO authors
    (name)
VALUES
    ('Pushkin Alexander'),
    ('Smith John'),
    ('Family Name'),
    ('Author 4'),
    ('Author 5'),
    ('Author 6');

INSERT INTO books
    (title, genre_id)
VALUES
    ('Theoretical physics', 1),
    ('Folk stories', 3),
    ('Popular chemistry', 2),
    ('Theology', 1),
    ('Russian folk stories', 3),
    ('Book 6', 1);

INSERT INTO book_authors(book_id, author_id)
VALUES (1, 1),   (1, 2),
       (2, 1),
       (3, 2),   (3, 6),   (3, 3),
       (4, 4),
       (5, 2),   (5, 6),
       (6, 6),   (6, 1),   (6, 2);