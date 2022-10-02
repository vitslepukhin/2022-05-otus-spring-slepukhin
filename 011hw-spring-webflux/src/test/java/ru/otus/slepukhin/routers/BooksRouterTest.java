package ru.otus.slepukhin.routers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.slepukhin.domain.Author;
import ru.otus.slepukhin.domain.Book;
import ru.otus.slepukhin.domain.Genre;
import ru.otus.slepukhin.handlers.BookHandler;
import ru.otus.slepukhin.repositories.BookRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Class BooksRouter")
class BooksRouterTest {
    private static final List<Book> EXPECTED_BOOKS_LIST = List.of(
            new Book("1", "Theoretical physics", List.of(new Author("1", "Pushkin Alexander")),
                     new Genre("1", "science")),
            new Book("2", "Folk stories", List.of(new Author("2", "Smith John")), new Genre("3", "folk")),
            new Book("3", "Popular chemistry", List.of(new Author("3", "Family Name")), new Genre("2", "popular")));
    private static final String EXISTING_BOOK_ID = "2";
    private static final String NOT_EXISTING_BOOK_ID = "id-not-exist";
    private static final Book EXPECTED_BOOK = new Book(EXISTING_BOOK_ID, "Folk stories",
                                                       List.of(new Author("2", "Smith John")), new Genre("3", "folk"));
    private static final Book BOOK_FOR_INSERT = new Book("", "Inserted book", List.of(new Author("2", "Smith John")),
                                                         new Genre("2", "popular"));
    private static final String INSERTED_BOOK_ID = "4";

    private WebTestClient client;
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository = mock(BookRepository.class);
        BookHandler bookHandler = new BookHandler(bookRepository);
        RouterFunction<?> routes = new BooksRouter(bookHandler).booksRoutes();

        client = WebTestClient.bindToRouterFunction(routes).build();
    }

    @DisplayName("Should correct GET all books /books")
    @Test
    void shouldCorrectGetAllBooks() {
        when(bookRepository.findAll()).thenReturn(Flux.fromIterable(EXPECTED_BOOKS_LIST));

        client.get().uri(uriBuilder -> uriBuilder.path("/books").build()).exchange().expectStatus().isOk()
              .expectBodyList(Book.class).value(books -> assertIterableEquals(EXPECTED_BOOKS_LIST, books));
    }

    @DisplayName("Should correct GET book by id /books/{id}")
    @Test
    void shouldCorrectGetBookById() {
        when(bookRepository.findById(EXISTING_BOOK_ID)).thenReturn(Mono.just(EXPECTED_BOOK));
        when(bookRepository.findById(NOT_EXISTING_BOOK_ID)).thenReturn(Mono.empty());

        client.get().uri(uriBuilder -> uriBuilder.path("/books/" + EXISTING_BOOK_ID).build()).exchange().expectStatus()
              .isOk()
              .expectBody(Book.class).value(book -> assertEquals(EXPECTED_BOOK, book));

        client.get().uri(uriBuilder -> uriBuilder.path("/books/" + NOT_EXISTING_BOOK_ID).build()).exchange()
              .expectStatus().isNotFound();
    }

    @DisplayName("Should correct POST book /books")
    @Test
    void shouldCorrectPostBook() {
        Book insertedBook = new Book(INSERTED_BOOK_ID, BOOK_FOR_INSERT.getTitle(), BOOK_FOR_INSERT.getAuthors(),
                                     BOOK_FOR_INSERT.getGenre());
        when(bookRepository.save(BOOK_FOR_INSERT)).thenReturn(Mono.just(insertedBook));

        client.post().uri(uriBuilder -> uriBuilder.path("/books").build()).bodyValue(BOOK_FOR_INSERT).exchange()
              .expectStatus()
              .isCreated()
              .expectBody(Book.class).value(book -> assertEquals(insertedBook, book));
    }

    @DisplayName("Should correct PUT book /books/{id}")
    @Test
    void shouldCorrectPutBook() {
        String newTitle = "New title";
        Book updatedBook = new Book(EXPECTED_BOOK.getId(), newTitle, EXPECTED_BOOK.getAuthors(),
                                    EXPECTED_BOOK.getGenre());
        Book existingBook = new Book(EXPECTED_BOOK.getId(), EXPECTED_BOOK.getTitle(), EXPECTED_BOOK.getAuthors(),
                                    EXPECTED_BOOK.getGenre());

        when(bookRepository.findById(EXISTING_BOOK_ID)).thenReturn(Mono.just(existingBook));
        when(bookRepository.save(updatedBook)).thenReturn(Mono.just(updatedBook));

        client.put().uri(uriBuilder -> uriBuilder.path("/books/" + EXISTING_BOOK_ID).build()).bodyValue(updatedBook)
              .exchange()
              .expectStatus()
              .isOk()
              .expectBody(Book.class).value(book -> {
                  assertEquals(updatedBook, book);
                  assertNotEquals(EXPECTED_BOOK, book);
              });
    }

    @DisplayName("Should correct DELETE book /books/{id}")
    @Test
    void shouldCorrectDeleteBook() {
        Mono<Void> monoVoid = Mono.empty().then();
        when(bookRepository.deleteById(EXISTING_BOOK_ID)).thenReturn(monoVoid);

        client.delete().uri(uriBuilder -> uriBuilder.path("/books/" + EXISTING_BOOK_ID).build()).exchange()
              .expectStatus()
              .isNoContent();
    }
}