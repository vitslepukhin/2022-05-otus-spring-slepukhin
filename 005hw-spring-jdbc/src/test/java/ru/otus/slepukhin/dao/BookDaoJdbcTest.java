package ru.otus.slepukhin.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.slepukhin.domain.Author;
import ru.otus.slepukhin.domain.Book;
import ru.otus.slepukhin.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Class BookDaoJdbc")
@JdbcTest
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {
    private static final int EXPECTED_BOOKS_COUNT = 3;
    private static final int EXISTING_BOOK_ID = 2;
    private static final Book EXPECTED_BOOK = new Book(2,
            "Folk stories",
            new Author(2L, "Smith John"),
            new Genre(3L, "folk"));
    private static final Book BOOK_FOR_INSERT = new Book("Inserted book",
            new Author(2L, "Smith John"),
            new Genre(2L, "popular"));
    private static final List<Book> EXPECTED_BOOKS_LIST = List.of(new Book(1,
                    "Theoretical physics",
                    new Author(1L, "Pushkin Alexander"),
                    new Genre(1L, "science")),
            new Book(2,
                    "Folk stories",
                    new Author(2L, "Smith John"),
                    new Genre(3L, "folk")),
            new Book(3,
                    "Popular chemistry",
                    new Author(3L, "Family Name"),
                    new Genre(2L, "popular")));

    @Autowired
    private BookDaoJdbc bookDaoJdbc;

    @DisplayName("should correct return count")
    @Test
    void shouldReturnCorrectCount() {
        Assertions.assertEquals(EXPECTED_BOOKS_COUNT, bookDaoJdbc.count());
    }

    @DisplayName("should correct return book by id")
    @Test
    void shouldCorrectReturnGetById() {
        Assertions.assertEquals(EXPECTED_BOOK, bookDaoJdbc.getById(EXPECTED_BOOK.getId()));
    }

    @DisplayName("should correct return all books")
    @Test
    void shouldCorrectReturnGetAll() {
        var booksList = bookDaoJdbc.getAll();
        assertThat(booksList).containsExactlyInAnyOrderElementsOf(EXPECTED_BOOKS_LIST);
    }

    @DisplayName("should correct insert book")
    @Test
    void shouldCorrectInsert() {
        var bookId = bookDaoJdbc.insert(BOOK_FOR_INSERT);
        var insertedBook = bookDaoJdbc.getById(bookId);
        assertThat(insertedBook.getId()).isEqualTo(bookId);
    }

    @DisplayName("should correct update book")
    @Test
    void shouldCorrectUpdate() {
        var existingBook = bookDaoJdbc.getById(EXISTING_BOOK_ID);

        var updatedBook = new Book(existingBook.getId(), "Updated title", new Author(1L, "Pushkin Alexander"), new Genre(1L, "science"));
        bookDaoJdbc.update(updatedBook);

        assertThat(bookDaoJdbc.getById(EXISTING_BOOK_ID)).usingRecursiveComparison().isEqualTo(updatedBook);
    }

    @DisplayName("should correct delete book by id")
    @Test
    void shouldCorrectDeleteById() {
        assertThatCode(() -> bookDaoJdbc.getById(EXISTING_BOOK_ID)).doesNotThrowAnyException();

        bookDaoJdbc.deleteById(EXISTING_BOOK_ID);

        assertThatThrownBy(() -> bookDaoJdbc.getById(EXISTING_BOOK_ID)).isInstanceOf(EmptyResultDataAccessException.class);
    }
}

