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