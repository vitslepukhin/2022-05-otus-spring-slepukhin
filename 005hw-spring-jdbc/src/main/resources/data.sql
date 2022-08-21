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
    ('Family Name');

INSERT INTO books
    (title, author_id, genre_id)
VALUES
    ('Theoretical physics', 1, 1),
    ('Folk stories', 2, 3),
    ('Popular chemistry', 3, 2),
    ('Theology', 2, 1),
    ('Russian folk stories', 1, 3);


