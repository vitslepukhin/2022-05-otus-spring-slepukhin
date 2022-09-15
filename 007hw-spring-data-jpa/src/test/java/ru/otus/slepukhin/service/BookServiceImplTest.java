package ru.otus.slepukhin.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.slepukhin.domain.Author;
import ru.otus.slepukhin.domain.Book;
import ru.otus.slepukhin.domain.Genre;
import ru.otus.slepukhin.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

@DisplayName("Class BookService")
class BookServiceImplTest {
    private static final long EXPECTED_BOOKS_COUNT = 3;
    private static final long EXISTING_BOOK_ID = 2;
    private static final long INSERTED_BOOK_ID = 4;
    private static final Book EXPECTED_BOOK = new Book(2, "Folk stories", List.of(new Author(2L, "Smith John")), new Genre(3L, "folk"));
    private static final Book BOOK_FOR_INSERT = new Book(0, "Inserted book", List.of(new Author(2L, "Smith John")), new Genre(2L, "popular"));
    private static final List<Book> EXPECTED_BOOKS_LIST = List.of(new Book(1, "Theoretical physics", List.of(new Author(1L, "Pushkin Alexander")), new Genre(1L, "science")), new Book(2, "Folk stories", List.of(new Author(2L, "Smith John")), new Genre(3L, "folk")), new Book(3, "Popular chemistry", List.of(new Author(3L, "Family Name")), new Genre(2L, "popular")));
    private final BookRepository mockBookRepository = mock(BookRepository.class);
    private BookServiceImpl bookServiceImpl;

    @BeforeEach
    void initBookService() {
        bookServiceImpl = new BookServiceImpl(mockBookRepository);
    }


    @DisplayName("Should correct count")
    @Test
    void shouldCorrectCount() {
        when(mockBookRepository.count()).thenReturn(EXPECTED_BOOKS_COUNT);
        assertEquals(EXPECTED_BOOKS_COUNT, bookServiceImpl.getCount());
    }

    @DisplayName("Should correct getById method")
    @Test
    void shouldCorrectGetById() {
        when(mockBookRepository.findById(EXISTING_BOOK_ID)).thenReturn(Optional.of(EXPECTED_BOOK));
        try {
            assertEquals(EXPECTED_BOOK, bookServiceImpl.getById(EXISTING_BOOK_ID));
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }

    @DisplayName("Should correct getAll")
    @Test
    void shouldCorrectGetAll() {
        when(mockBookRepository.findAll()).thenReturn(EXPECTED_BOOKS_LIST);
        assertEquals(EXPECTED_BOOKS_LIST, bookServiceImpl.getAll());
    }

    @DisplayName("Should correct insert")
    @Test
    void shouldCorrectInsert() {
        when(mockBookRepository.save(BOOK_FOR_INSERT)).thenReturn(new Book(INSERTED_BOOK_ID, BOOK_FOR_INSERT.getTitle(), BOOK_FOR_INSERT.getAuthors(), BOOK_FOR_INSERT.getGenre()));
        assertEquals(INSERTED_BOOK_ID, bookServiceImpl.insert(BOOK_FOR_INSERT));
    }

    @DisplayName("Should correct delete")
    @Test
    void shouldCorrectDelete() {
        bookServiceImpl.deleteById(EXISTING_BOOK_ID);
        verify(mockBookRepository, atLeastOnce()).deleteById(EXISTING_BOOK_ID);
    }

    @DisplayName("Should correct update")
    @Test
    void shouldCorrectUpdate() {
        var updatedBook = new Book(EXISTING_BOOK_ID, "Updated title", List.of(new Author(1L, "Pushkin Alexander")), new Genre(1L, "science"));
        bookServiceImpl.update(updatedBook);

        verify(mockBookRepository, atLeastOnce()).save(updatedBook);
    }
}
