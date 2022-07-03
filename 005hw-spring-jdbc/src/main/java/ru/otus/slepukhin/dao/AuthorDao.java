package ru.otus.slepukhin.dao;

import ru.otus.slepukhin.domain.Author;

import java.util.List;

public interface AuthorDao {
    void insert(Author person);

    Author getById(long id);

    List<Author> getAll();

    void deleteById(long id);
}
