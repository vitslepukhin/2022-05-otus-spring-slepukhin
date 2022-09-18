package ru.otus.slepukhin.service;

import ru.otus.slepukhin.domain.Book;

import java.util.List;

public interface BookService {
    long getCount();

    Book getById(String id) throws Exception;

    List<Book> getAll();

    String insert(Book bookForInsert);

    void update(Book bookForUpdate);

    void deleteById(String id);
}
