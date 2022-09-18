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
    ('Book 6', 1),
    ('Book 7', 2);

INSERT INTO book_authors(book_id, author_id)
VALUES (1, 1),   (1, 2),
       (2, 1),
       (3, 2),   (3, 6),   (3, 3),
       (4, 4),
       (5, 2),   (5, 6),
       (6, 6),   (6, 1),   (6, 2),
       (7, 5);

INSERT INTO comments
    (text, book_id)
VALUES
    ('Physics is cool!', 1),
    ('Physics is bad', 1),
    ('Folk comment', 2),
    ('chemistry comment', 3),
    ('4 comment', 4),
    ('5 comment', 5),
    ('6 comment', 6),
    ('7 comment', 7);
