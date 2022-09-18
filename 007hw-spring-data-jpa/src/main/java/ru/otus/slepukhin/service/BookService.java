package ru.otus.slepukhin.service;

import ru.otus.slepukhin.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    long getCount();

    Book getById(long id) throws Exception;

    List<Book> getAll();

    long insert(Book bookForInsert);

    void update(Book bookForUpdate);

    void deleteById(long id);
}
