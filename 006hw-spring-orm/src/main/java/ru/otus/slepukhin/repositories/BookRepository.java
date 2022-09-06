package ru.otus.slepukhin.repositories;

import ru.otus.slepukhin.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);
    Optional<Book> findById(long id);

    List<Book> findAll();

    void deleteById(long id);

    long count();
}
