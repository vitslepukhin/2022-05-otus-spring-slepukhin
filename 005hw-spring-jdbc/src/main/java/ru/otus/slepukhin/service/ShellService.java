package ru.otus.slepukhin.service;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.slepukhin.domain.Author;
import ru.otus.slepukhin.domain.Book;
import ru.otus.slepukhin.domain.Genre;

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
    public Book getById(@ShellOption long id) {
        return bookService.getById(id);
    }

    @ShellMethod(value = "Get all books", key = "getAll")
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @ShellMethod(value = "Insert book", key = "insert")
    public long insert(String title, long authorId, long genreId) {
        return bookService.insert(new Book(title, new Author(authorId), new Genre(genreId)));
    }

    @ShellMethod(value = "Update book", key = "update")
    public void update(long id, String title, long authorId, long genreId) {
        bookService.update(new Book(id, title, new Author(authorId), new Genre(genreId)));
    }

    @ShellMethod(value = "Delete book", key = "deleteById")
    public void deleteById(long id) {
        bookService.deleteById(id);
    }
}