package ru.otus.slepukhin.dao;

import ru.otus.slepukhin.domain.Genre;

import java.util.List;

public interface GenreDao {
    void insert(Genre person);

    Genre getById(long id);

    List<Genre> getAll();

    void deleteById(long id);
}
