package ru.otus.slepukhin.service;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.slepukhin.domain.Author;
import ru.otus.slepukhin.domain.Book;
import ru.otus.slepukhin.domain.Genre;

import java.util.List;

@ShellComponent
@ShellCommandGroup(ShellService.groupPrefix)
@AllArgsConstructor
public class ShellService {
    public final static String groupPrefix = "book";
    private final BookService bookService;

    @ShellMethod(value = "Get book's count", key = groupPrefix + " getCount")
    public long getCount() {
        return bookService.getCount();
    }

    @ShellMethod(value = "Get book by id", key = groupPrefix + " getById")
    public Book getById(long id) {
        return bookService.getById(id);
    }

    @ShellMethod(value = "Get all books", key = groupPrefix + " getAll")
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @ShellMethod(value = "Insert book", key = groupPrefix + " insert")
    public long insert(String title, long authorId, long genreId) {
        Author author = new Author();
        author.setId(authorId);
        Genre genre =  new Genre();
        genre.setId(genreId);
        return bookService.insert(new Book(0, title, List.of(new Author(authorId)), new Genre(genreId), List.of()));
    }

    @ShellMethod(value = "Update book", key = groupPrefix + " update")
    public void update(long id, String title, long authorId, long genreId) {
        bookService.update(new Book(id, title, List.of(new Author(authorId)), new Genre(genreId), List.of()));
    }

    @ShellMethod(value = "Delete book", key = groupPrefix + " deleteById")
    public void deleteById(long id) {
        bookService.deleteById(id);
    }
}