package ru.otus.slepukhin.domain;

import lombok.Data;

@Data
public class Author {
    private final long id;
    private final String name;

    public Author(Long id) {
        this.name = "";
        this.id = id;
    }

    public Author(String name) {
        this.name = name;
        this.id = 0;
    }

    public Author(Long id, String name) {
        this.name = name;
        this.id = id;
    }
}
