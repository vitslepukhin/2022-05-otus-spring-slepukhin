package ru.otus.slepukhin.domain;

import lombok.Data;

@Data
public class Book {
    private final long id;
    private final String title;
    private final Long author_id;
    private final Long genre_id;

    public Book(long id, String title, Long author_id, Long genre_id) {
        this.id = id;
        this.title = title;
        this.author_id = author_id;
        this.genre_id = genre_id;
    }

    public Book(String title, Long author_id, Long genre_id) {
        this.id = 0;
        this.title = title;
        this.author_id = author_id;
        this.genre_id = genre_id;
    }
}
