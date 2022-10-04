package ru.otus.slepukhin.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import ru.otus.slepukhin.domain.Author;
import ru.otus.slepukhin.domain.Book;
import ru.otus.slepukhin.domain.Genre;
import ru.otus.slepukhin.repositories.BookRepository;

import java.util.List;

@ChangeLog(order = "003")
public class BookChangeLog {


    @ChangeSet(order = "002", id = "addBooksCollection", author = "slepukhin", runAlways = true)
    public void addBooks(BookRepository bookRepository) {
        List<Book> books = List.of(
                createNewBook("1", "Theoretical physics", "1", "1"),
                createNewBook("2", "Folk stories", "2", "3"),
                createNewBook("3", "Popular chemistry", "3", "2"),
                createNewBook("4", "Theology", "4", "1"),
                createNewBook("5", "Russian folk stories", "1", "3"),
                createNewBook("6", "Book 6", "3", "1")
        );

        bookRepository
                .saveAll(books)
                .subscribe();
    }

    private Book createNewBook(String id, String title, String authorId, String genreId) {
        return new Book(id, title, List.of(new Author(authorId)), new Genre(genreId));
    }
}
