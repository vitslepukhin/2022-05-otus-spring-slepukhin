package ru.otus.slepukhin.domain;

import lombok.Data;

@Data
public class Genre {
    private final long id;
    private final String genre;

    public Genre(Long id) {
        this.genre = "";
        this.id = id;
    }

    public Genre(String genre) {
        this.genre = genre;
        this.id = 0;
    }

    public Genre(Long id, String genre) {
        this.genre = genre;
        this.id = id;
    }
}
