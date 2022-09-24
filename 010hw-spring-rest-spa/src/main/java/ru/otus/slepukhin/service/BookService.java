package ru.otus.slepukhin.service;

import ru.otus.slepukhin.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    long getCount();

    Optional<Book> getById(long id);

    List<Book> getAll();

    Book save(Book bookForInsert);

    void update(Book bookForUpdate);

    void deleteById(long id);
}
