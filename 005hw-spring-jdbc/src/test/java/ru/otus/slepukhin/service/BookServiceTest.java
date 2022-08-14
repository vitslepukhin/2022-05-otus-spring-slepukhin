package ru.otus.slepukhin.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.slepukhin.dao.BookDao;
import ru.otus.slepukhin.domain.Author;
import ru.otus.slepukhin.domain.Book;
import ru.otus.slepukhin.domain.Genre;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Class BookService")
class BookServiceTest {
    private BookDao mockBookDao = mock(BookDao.class);
    private static final int EXPECTED_BOOKS_COUNT = 3;
    private static final long EXISTING_BOOK_ID = 2;
    private static final long INSERTED_BOOK_ID = 4;
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

    private BookService bookService;

    @BeforeEach
    void initBookService() {
        bookService = new BookService(mockBookDao);
    }


    @DisplayName("Should correct count")
    @Test
    void shouldCorrectCount() {
        when(mockBookDao.count()).thenReturn(EXPECTED_BOOKS_COUNT);
        assertEquals(EXPECTED_BOOKS_COUNT, bookService.getCount());
    }

    @DisplayName("Should correct getById method")
    @Test
    void shouldCorrectGetById() {
        when(mockBookDao.getById(EXISTING_BOOK_ID)).thenReturn(EXPECTED_BOOK);
        assertEquals(EXPECTED_BOOK, bookService.getById(EXISTING_BOOK_ID));
    }

    @DisplayName("Should correct getAll")
    @Test
    void shouldCorrectGetAll() {
        when(mockBookDao.getAll()).thenReturn(EXPECTED_BOOKS_LIST);
        assertEquals(EXPECTED_BOOKS_LIST, bookService.getAll());
    }

    @DisplayName("Should correct insert")
    @Test
    void shouldCorrectInsert() {
        when(mockBookDao.insert(BOOK_FOR_INSERT)).thenReturn(INSERTED_BOOK_ID);
        assertEquals(INSERTED_BOOK_ID, bookService.insert(BOOK_FOR_INSERT));
    }

    @DisplayName("Should correct delete")
    @Test
    void shouldCorrectDelete() {
        bookService.deleteById(EXISTING_BOOK_ID);
        verify(mockBookDao, atLeastOnce()).deleteById(EXISTING_BOOK_ID);
    }

    @DisplayName("Should correct update")
    @Test
    void shouldCorrectUpdate() {
        var updatedBook = new Book(EXISTING_BOOK_ID, "Updated title", new Author(1L, "Pushkin Alexander"), new Genre(1L, "science"));
        bookService.update(updatedBook);

        verify(mockBookDao, atLeastOnce()).update(updatedBook);
    }
}
