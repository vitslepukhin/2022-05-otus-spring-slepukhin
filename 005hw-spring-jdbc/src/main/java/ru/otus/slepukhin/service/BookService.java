package ru.otus.slepukhin.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.slepukhin.dao.BookDao;
import ru.otus.slepukhin.domain.Author;
import ru.otus.slepukhin.domain.Book;
import ru.otus.slepukhin.domain.Genre;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookDao bookDao;

    public int getCount() {
        return bookDao.count();
    }

    public Book getById(Long id) {
        return bookDao.getById(id);
    }

    public List<Book> getAll() {
        return bookDao.getAll();
    }

    public Long insert(String title, Long author_id, Long genre_id) {
        Book newBook = new Book(title, new Author(author_id), new Genre(genre_id));
        return bookDao.insert(newBook);
    }

    public void update(long id, String title, Long author_id, Long genre_id) {
        Book book = new Book(id, title, new Author(author_id), new Genre(genre_id));
        bookDao.update(book);
    }
}
