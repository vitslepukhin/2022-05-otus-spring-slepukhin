package ru.otus.slepukhin.handlers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.slepukhin.domain.Book;
import ru.otus.slepukhin.repositories.BookRepository;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.*;


@Component
@AllArgsConstructor
public class BookHandler {

    private final BookRepository repository;

    public Mono<ServerResponse> findAll(ServerRequest request) {
        Flux<Book> bookFlux = repository.findAll();
        return ok()
                .contentType(APPLICATION_JSON)
                .body(bookFlux, Book.class);
    }


    public Mono<ServerResponse> findById(ServerRequest request) {
        String bookId = request.pathVariable("id");
        return repository
                .findById(bookId)
                .flatMap(book -> ok()
                        .contentType(APPLICATION_JSON)
                        .bodyValue(book))
                .switchIfEmpty(notFound().build());
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        return request
                .bodyToMono(Book.class)
                .flatMap(repository::save)
                .flatMap(newBook -> created(URI.create("/books/" + newBook.getId()))
                        .contentType(APPLICATION_JSON)
                        .bodyValue(newBook));
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        String bookId = request.pathVariable("id");

        return Mono
                .zip(
                        request.bodyToMono(Book.class),
                        repository
                                .findById(bookId)
                                .defaultIfEmpty(new Book()),
                        (bookToUpdate, existingBook) -> {
                            if (!bookId.equals(existingBook.getId())) {
                                bookToUpdate.setId(bookId);
                                return bookToUpdate;
                            }
                            existingBook.setTitle(bookToUpdate.getTitle());
                            existingBook.setAuthors(bookToUpdate.getAuthors());
                            existingBook.setGenre(bookToUpdate.getGenre());
                            return existingBook;
                        })
                .flatMap(bookToUpdate -> ok()
                        .contentType(APPLICATION_JSON)
                        .body(repository.save(bookToUpdate), Book.class));
    }

    public Mono<ServerResponse> deleteById(ServerRequest request) {
        String bookId = request.pathVariable("id");
        return repository
                .deleteById(bookId).thenReturn(bookId)
                .flatMap((empty) -> noContent().build());
    }
}
