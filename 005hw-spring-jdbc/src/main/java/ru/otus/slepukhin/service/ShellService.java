package ru.otus.slepukhin.service;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.slepukhin.domain.Book;

import java.util.List;

@ShellComponent
@AllArgsConstructor
public class ShellService {
    private final BookService bookService;

    @ShellMethod(value = "Get book's count", key = "getCount")
    public int getCount() {
        return bookService.getCount();
    }

    @ShellMethod(value = "Get book by id", key = "getById")
    public Book getById(@ShellOption Long id) {
        return bookService.getById(id);
    }

    @ShellMethod(value = "Get all books", key = "getAll")
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @ShellMethod(value = "Insert book", key = "insert")
    public Long insert(String title, Long author_id, Long genre_id) {
        return bookService.insert(title, author_id, genre_id);
    }

    @ShellMethod(value = "Update book", key = "update")
    public void update(long id, String title, Long author_id, Long genre_id) {
        bookService.update(id, title, author_id, genre_id);
    }
}