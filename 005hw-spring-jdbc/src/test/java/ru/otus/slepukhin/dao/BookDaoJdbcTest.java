package ru.otus.slepukhin.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.slepukhin.domain.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Class BookDaoJdbc")
@JdbcTest
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {
    private static final int EXPECTED_BOOKS_COUNT = 3;
    private static final int EXISTING_BOOK_ID = 2;
    private static final Book EXPECTED_BOOK = new Book(2, "Folk stories", 2L, 3L);
    private static final Book BOOK_FOR_INSERT = new Book("Inserted book", 2L, 2L);
    private static final List<Book> EXPECTED_BOOKS_LIST = List.of(new Book(1, "Theoretical physics", 1L, 1L),
                                                                  new Book(2, "Folk stories", 2L, 3L),
                                                                  new Book(3, "Popular chemistry", 3L, 2L)
    );

    @Autowired
    private BookDaoJdbc bookDaoJdbc;

    @DisplayName("should correct return count")
    @Test
    void shouldReturnCorrectCount() {
        Assertions.assertEquals(bookDaoJdbc.count(), EXPECTED_BOOKS_COUNT);
    }

    @DisplayName("should correct return book by id")
    @Test
    void shouldCorrectReturnGetById() {
        Assertions.assertEquals(bookDaoJdbc.getById(EXPECTED_BOOK.getId()), EXPECTED_BOOK);
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

        var updatedBook = new Book(existingBook.getId(), "Updated title", 1L, 1L);
        bookDaoJdbc.update(updatedBook);

        assertThat(bookDaoJdbc.getById(EXISTING_BOOK_ID)).isEqualTo(updatedBook);
    }

    @DisplayName("should correct delete book by id")
    @Test
    void shouldCorrectDeleteById() {
        assertThatCode(() -> bookDaoJdbc.getById(EXISTING_BOOK_ID)).doesNotThrowAnyException();

        bookDaoJdbc.deleteById(EXISTING_BOOK_ID);

        assertThatThrownBy(() -> bookDaoJdbc.getById(EXISTING_BOOK_ID)).isInstanceOf(EmptyResultDataAccessException.class);
    }
}

