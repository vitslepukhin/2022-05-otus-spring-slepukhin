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
@ShellCommandGroup("book")
@AllArgsConstructor
public class ShellService {
    private final BookService bookService;

    @ShellMethod(value = "Get book's count", key = "book getCount")
    public long getCount() {
        return bookService.getCount();
    }

    @ShellMethod(value = "Get book by id", key = "book getById")
    public Book getById(long id) throws Exception {
        return bookService.getById(id);
    }

    @ShellMethod(value = "Get all books", key = "book getAll")
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @ShellMethod(value = "Insert book", key = "book insert")
    public long insert(String title, long authorId, long genreId) {
        Author author = new Author();
        author.setId(authorId);
        Genre genre =  new Genre();
        genre.setId(genreId);
        return bookService.insert(new Book(0, title, List.of(new Author(authorId)), new Genre(genreId)));
    }

    @ShellMethod(value = "Update book", key = "book update")
    public void update(long id, String title, long authorId, long genreId) {
        bookService.update(new Book(id, title, List.of(new Author(authorId)), new Genre(genreId)));
    }

    @ShellMethod(value = "Delete book", key = "book deleteById")
    public void deleteById(long id) {
        bookService.deleteById(id);
    }
}