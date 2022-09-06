package ru.otus.slepukhin.service;

import ru.otus.slepukhin.domain.Book;

import java.util.List;

public interface BookService {
    long getCount();

    Book getById(long id);

    List<Book> getAll();

    long insert(Book bookForInsert);

    void update(Book bookForUpdate);

    void deleteById(long id);
}
