package ru.otus.slepukhin.service;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.slepukhin.dao.BookDao;
import ru.otus.slepukhin.domain.Book;

import java.util.List;

@ShellComponent
@AllArgsConstructor
public class ShellService {
    private final BookDao bookDao;

    @ShellMethod(value = "Get book's count", key = "getCount")
    public int getCount(
    ) {
        return bookDao.count();
    }

    @ShellMethod(value = "Get book by id", key = "getById")
    public Book getById(
            @ShellOption Long id
    ) {
        return bookDao.getById(id);
    }

    @ShellMethod(value = "Get all books", key = "getAll")
    public List<Book> getAll(

    ) {
        return bookDao.getAll();
    }

    @ShellMethod(value = "Insert book", key = "insert")
    public Long insert(
            String title, Long author_id, Long genre_id
    ) {
        Book newBook = new Book(title, author_id, genre_id);
        return bookDao.insert(newBook);
    }

    @ShellMethod(value = "Update book", key = "update")
    public void update(
            long id, String title, Long author_id, Long genre_id
    ) {
        Book book = new Book(id, title, author_id, genre_id);
        bookDao.update(book);
    }
}